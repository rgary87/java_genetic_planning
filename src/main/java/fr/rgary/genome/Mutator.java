/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

import fr.rgary.genome.enums.Origin;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class Mutator.
 */
public class Mutator {

    public static DNA mutate(final DNA pDNA, final int change_percentage) {
        for (Slot slot : pDNA.slots) {
            if (ThreadLocalRandom.current().nextInt(0, 100) < change_percentage) {
                Worker originalWorker = slot.worker;
                if (originalWorker != null) {
                    originalWorker.remainingHours++;
                }
                Day day = slot.hour.day;
                Worker replacementWorker = day.week.getBestWorkerByFitness(day, slot.hour);
                replacementWorker.remainingHours--;
                slot.worker = replacementWorker;
            }
        }
        pDNA.origin = Origin.MUTATION;
        pDNA.realOrigin = Origin.MUTATION;
        return pDNA;
    }
}
