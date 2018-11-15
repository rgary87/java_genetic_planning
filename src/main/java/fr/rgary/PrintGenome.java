/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary;

import fr.rgary.genome.DNA;
import fr.rgary.genome.Day;
import fr.rgary.genome.Hour;
import fr.rgary.genome.Slot;
import fr.rgary.genome.Week;
import fr.rgary.genome.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Class PrintGenome.
 */
public class PrintGenome {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(PrintGenome.class);

    public static String printWholeGenome(final DNA pDNA) {
//        LOGGER.info("Best Result: ");
        StringBuilder sb = new StringBuilder();
        sb.append("Best Result is a: ").append(pDNA.realOrigin.name()).append('\n');
        for (final Week week : pDNA.weeks) {
//            LOGGER.info("Week: ");
            sb.append("Week: ").append('\n');
            for (final Day day : week.days) {
//                LOGGER.info("\tDay: ");
                sb.append("\tDay: ").append('\n');
                Map<String, Integer> workerHourCount = new HashMap<>();
                for (final Worker worker : week.workers) {
                    workerHourCount.put(worker.name, 0);
                }
                for (final Hour hour : day.hours) {
//                    LOGGER.info("\t\t%s", hour);
                    sb.append("\t\t").append(hour.toString()).append('\n');
                    for (final Slot slot : hour.slots) {
                        if (slot.worker != null) {
                            int count = workerHourCount.get(slot.worker.name) + 1;
                            workerHourCount.replace(slot.worker.name, count);
                        }
                    }
                }
                for (Map.Entry<String, Integer> entry : workerHourCount.entrySet()) {
                    sb.append("\t\tWorker: ").append(entry.getKey()).append(" work ").append(entry.getValue()).append(" hour\n");
                }
            }
            for (final Worker worker : week.workers) {
                sb.append("Worker: ").append(worker.name).append(" has ").append(worker.remainingHours).append(" hours remaining\n");
            }
        }
        System.out.printf("%s", sb.toString());
        return sb.toString();
    }

    public static String printObjectID(final Object o) {
        return o.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(o));
    }
}
