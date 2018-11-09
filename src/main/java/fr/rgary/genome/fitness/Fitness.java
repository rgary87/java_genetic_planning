/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome.fitness;

import fr.rgary.genome.DNA;
import fr.rgary.genome.Day;
import fr.rgary.genome.Hour;
import fr.rgary.genome.Slot;
import fr.rgary.genome.Week;
import fr.rgary.genome.Worker;
import fr.rgary.genome.enums.Origin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class Fitness.
 */
public class Fitness {

    public static List<DNA> sortDnaPerScore(final List<DNA> pDNAList) {
//        pDNAList.parallelStream().forEach(dna -> Fitness.calcFitness(dna));
        for (DNA dna : pDNAList) {
            calcFitness(dna);
        }
        pDNAList.sort(new Comparator<DNA>() {
            @Override
            public int compare(final DNA o1, final DNA o2) {
                return o2.score - o1.score;
            }
        });
        return pDNAList;
    }

    public static void calcFitness(final DNA pDNA) {
        pDNA.score = findEmptyWorkerSlot(pDNA);
        for (final Week week : pDNA.weeks) {
            pDNA.score = getScorePerWorkerPerWeek(week);
        }
        pDNA.score += countUndertimePerWeek(pDNA);
        if (pDNA.validInterday) {
            pDNA.score += (20000 * pDNA.weekCount);
        }
        if (pDNA.validWorkerTimePerDay) {
            pDNA.score += (500 * pDNA.weekCount * pDNA.weeks.get(0).days.size());
        }
    }

    public static int countUndertimePerWeek(final DNA pDNA) {
        int count = 0;
        for (final Week week : pDNA.weeks) {
            for (final Worker worker : week.workers) {
                count += (worker.remainingHours * FitnessValues.FITNESS_UNDERHOUR_PER_WEEK);
            }
        }
        return count;
    }

    public static int findEmptyWorkerSlot(final DNA pDNA) {
        int count = 0;
        for (final Slot slot: pDNA.slots) {
            if (slot.worker == null) {
                count += FitnessValues.FITNESS_EMPTY_WORKER_SLOT;
            }
        }
        return count;
    }

    public static int getScorePerWorkerPerWeek(final Week pWeek) {
        int score = 0;
        for (final Worker worker : pWeek.workers) {
            int totalWeekHourCount = 0;
            for (final Day day : pWeek.days) {
                int totalDayHourCount = 0;
                int continuousHourCount = 0;
                for (final Hour hour : day.hours) {
                    if (day.workerIsAssignedToHour(worker, hour)) {
                        totalDayHourCount += 1;
                        continuousHourCount += 1;
                    } else if (continuousHourCount != 0) {
                        score += FitnessValues.fitnessContinuousHoursValues(continuousHourCount);
                        continuousHourCount = 0;
                    }
                }
                score += FitnessValues.fitnessTotalHoursPerDay(totalDayHourCount);
                totalWeekHourCount += totalDayHourCount;
            }
            score += FitnessValues.fitnessTotalHoursPerWeek(totalWeekHourCount, worker.baseHourTarget);
        }
        return score;
    }

    public static DNA inheritance(final DNA one, final DNA two) {
        List<Week> weekList = new ArrayList<>(one.weekCount);
        for (int i = 0; i < Math.min(one.weeks.size(), two.weeks.size()); i++) {
            final Week weekOne = one.weeks.get(i);
            final Week weekTwo = two.weeks.get(i);
            List<Day> dayList = new ArrayList<>(7);
            for (int j = 0; j < weekOne.days.size(); j++) {
                if (j % 2 == 0) {
                    dayList.add(weekOne.days.get(j));
                } else {
                    dayList.add(weekTwo.days.get(j));
                }
            }
            weekList.add(new Week(dayList, null));
        }
        DNA renewDna = new DNA(weekList);
        renewDna.origin = Origin.INHERITANCE;
        renewDna.realOrigin = Origin.INHERITANCE;
        return renewDna;
    }
}
