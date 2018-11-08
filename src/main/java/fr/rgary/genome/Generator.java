/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

import fr.rgary.genome.enums.Origin;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Generator.
 */
public class Generator {

    public static DNA generate() {
        DNA dna = new DNA();
        dna.origin = Origin.RE_GENERATION;
        dna.realOrigin = Origin.RE_GENERATION;
        for (final Week week : dna.weeks) {
            for (final Day day : week.days) {
                for (final Hour hour : day.hours) {
                    for (final Slot slot : hour.slots) {
                        final Worker worker = week.getRandomWorker();
                        day.setWorkerRecursively(hour, worker, 4);
                    }
                }
            }
        }
        Generator.fillHoles(dna, 1);
        return dna;
    }

    public static void fillHoles(final DNA dna, final int minContinuousHours) {
        for (final Week week : dna.weeks) {
            Worker worker = week.getWorkerWithMostRemainingHours();
            Slot openSlot = week.hasAnyDayAnyHourSlotRemaining();
            List<Worker> exceptionWorker = new ArrayList<>();
            Slot prevSlot = openSlot;
            while (worker != null && worker.remainingHours >= minContinuousHours && openSlot != null) {
                if (!openSlot.hour.day.setWorkerRecursively(openSlot.hour, worker, minContinuousHours)) {
                    exceptionWorker.add(worker);
                    worker = week.getWorkerWithMostRemainingHoursExcept(exceptionWorker);
                } else {
                    worker = week.getWorkerWithMostRemainingHours();
                }
                openSlot = week.hasAnyDayAnyHourSlotRemaining();
                if (!prevSlot.equals(openSlot)) {
                    exceptionWorker = new ArrayList<>();
                    prevSlot = openSlot;
                }
            }
        }
    }

    /*

    @staticmethod
    def generate_full() -> DNA:
        dna = DNA()
        for week in dna.weeks:
            for day in week.days:
                # print(f"Gen a new Day !")
                for h in range(0, day.hours_count):
                    for slot in day.hours[h]:
                        worker = week.workers.get_random_worker()
                        day.recurs_set_worker(h, worker, min_continuous_hours=4)
        # self.fill_holes(min_continuous_hours=3)
        # self.fill_holes(min_continuous_hours=2)
        DNAGenerator.fill_holes(dna, min_continuous_hours=1)
        return dna




     */
}
