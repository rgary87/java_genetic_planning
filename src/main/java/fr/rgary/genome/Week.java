/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

import fr.rgary.genome.fitness.FitnessValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static fr.rgary.genome.enums.DaysOfWeek.FRIDAY;
import static fr.rgary.genome.enums.DaysOfWeek.MONDAY;
import static fr.rgary.genome.enums.DaysOfWeek.SATURDAY;
import static fr.rgary.genome.enums.DaysOfWeek.SUNDAY;
import static fr.rgary.genome.enums.DaysOfWeek.THURSDAY;
import static fr.rgary.genome.enums.DaysOfWeek.TUESDAY;
import static fr.rgary.genome.enums.DaysOfWeek.WEDNESDAY;

/**
 * Class Week.
 */
public class Week {

    public ArrayList<Day> days;
    public ArrayList<Worker> workers;

    public Week() {
        this.workers = new ArrayList<>(Arrays.asList(
                new Worker("Jean", 39),
                new Worker("Francis", 39),
                new Worker("Michel", 39),
                new Worker("Paul", 39),
                new Worker("Alice", 39),
                new Worker("Ginette", 39),
                new Worker("Gwenaelle", 39),
                new Worker("Julie", 25)
        ));
        this.days = new ArrayList<>(Arrays.asList(
                new Day(MONDAY),
                new Day(TUESDAY),
                new Day(WEDNESDAY),
                new Day(THURSDAY),
                new Day(FRIDAY),
                new Day(SATURDAY),
                new Day(SUNDAY)
        ));
    }

    public Week(final Week that) {
        this.workers = new ArrayList<>(8);
        for (final Worker worker : that.workers) {
            this.workers.add(new Worker(worker));
        }

        this.days = new ArrayList<>(7);
        for (final Day day : that.days) {
            this.days.add(day.copyMe(day));
        }
    }

    public Slot hasAnyDayAnyHourSlotRemaining() {
        Slot openSlot = null;
        for (final Day day : this.days) {
            openSlot = day.hasAnyHourSlotRemaining();
            if (openSlot != null) {
                return openSlot;
            }
        }
        return null;
    }

    public void countWorkerRemainingHourPerWorker() {
        for (final Worker worker : this.workers) {
            for (final Day day : this.days) {
                for (final Hour hour : day.hours) {
                    for (final Slot slot : hour.slots) {
                        if (slot.worker.equals(worker)) {
                            worker.remainingHours -= 1;
                        }
                    }
                }
            }
        }
    }

    public boolean  checkWorkerEveningMorningRule(final Week prevWeek) {
        for (final Worker worker : this.workers) {
            Day prevDay = null;
            if (prevWeek != null) {
                prevDay = prevWeek.days.get(6);
            }
            for (final Day day :this.days) {
                if (prevDay == null) {
                    prevDay = day;
                    continue;
                }
                Hour currentDayFirstHour = Day.getFirstHourForWorker(day, worker);
                Hour prevDayLastHour = Day.getLastHourForWorker(prevDay, worker);
                //print(f"Worker {worker.name} ended last day at index {prev_day_last_hour} and start current day at index {current_day_first_hour}")
                int count = Day.getHoursCountBetweenDays(currentDayFirstHour, prevDayLastHour);
                //print(f"Hour count between days: {count}")
                if (count < 12) {
                    return false;
                }
                prevDay = day;
            }
        }
        return true;
    }


    /* ******************** */
    /* ** HANDLE WORKERS ** */
    /* ******************** */

    public Worker getWorkerPerName(final String name) {
        for (final Worker worker : this.workers) {
            if (worker.name.equals(name)) {
                return worker;
            }
        }
        return null;
    }

    public Worker getRandomWorker() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, this.workers.size());
        return this.workers.get(randomNum);
    }

    public Worker getBestWorkerByFitness(Day day, int hourIdx) {
        ArrayList<WorkerFitnessResult> results = new ArrayList<>(this.workers.size());
        for (final Worker worker : this.workers) {
            WorkerFitnessResult workerResult = new WorkerFitnessResult(worker);
            results.add(workerResult);
            if (Day.workerIsAssignedToHour(worker, (Hour) day.hours.get(hourIdx))) {
                continue;
            }
            boolean stopCountBefore = false;
            for (int i = hourIdx - 1; i > 0; i--) {
                if (Day.workerIsAssignedToHour(worker, (Hour) day.hours.get(i))) {
                    if (!stopCountBefore) {
                        workerResult.countinuousHour++;
                    }
                    workerResult.totalDayHourCount++;
                } else {
                    stopCountBefore = true;
                }
            }
            boolean stopCountAfter = false;
            for (int i = hourIdx; i < day.hours.size(); i++) {
                if (Day.workerIsAssignedToHour(worker, (Hour) day.hours.get(i))) {
                    if (!stopCountAfter) {
                        workerResult.countinuousHour++;
                    }
                    workerResult.totalDayHourCount++;
                } else {
                    stopCountAfter = true;
                }
            }
            workerResult.value = FitnessValues.fitnessContinuousHoursValues(workerResult.countinuousHour);
            workerResult.value += FitnessValues.fitnessTotalHoursPerDay(workerResult.totalDayHourCount);
        }
        WorkerFitnessResult bestWorkerResult = results.get(0);
        for (final WorkerFitnessResult workerResult : results) {
            if (workerResult.value < bestWorkerResult.value
                    || (workerResult.value == bestWorkerResult.value && ThreadLocalRandom.current().nextInt() % 2 == 0)) {
                bestWorkerResult = workerResult;
            }
        }
        return bestWorkerResult.worker;
    }

    public Worker getWorkerWithMostRemainingHours() {
        Worker result = null;
        for (final Worker worker : this.workers) {
            if (worker.remainingHours == 0) {
                continue;
            }
            if (result == null || result.remainingHours < worker.remainingHours
                || (result.remainingHours == worker.remainingHours && ThreadLocalRandom.current().nextInt() % 2 == 0)) {
                result = worker;
            }
        }
        return result;
    }

    public Worker getWorkerWithMostRemainingHoursExcept(final List<String> exceptions) {
        Worker result = null;
        for (final Worker worker : this.workers) {
            if (exceptions.contains(worker.name) || worker.remainingHours == 0) {
                continue;
            }
            if (result == null || result.remainingHours < worker.remainingHours
                    || (result.remainingHours == worker.remainingHours && ThreadLocalRandom.current().nextInt() % 2 == 0)) {
                result = worker;
            }
        }
        return result;
    }

    private class WorkerFitnessResult {
        public Worker worker;
        public int totalDayHourCount = 0;
        public int countinuousHour = 0;
        public int value = Integer.MIN_VALUE;

        public WorkerFitnessResult(final Worker pWorker) {
            this.worker = pWorker;
        }
    }


}
