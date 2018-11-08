/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome.fitness;

/**
 * Class FitnessValues.
 */
public class FitnessValues {

    public static int fitnessContinuousHoursValues(final int hourCount) {
        int diff = Math.abs(8 - hourCount);
        if (diff == 0 || diff == 8) {
            return 0;
        }
        int res = -100;
        for (int i = 1; i < diff; i++) {
            res *= 2;
        }
        return res;
    }

    public static int fitnessTotalHoursPerDay(final int hourCount) {
        int diff = Math.abs(8 - hourCount);
        int result;
        switch (diff) {
            case 2:
                result = -100;
                break;
            case 3:
                result = -200;
                break;
            case 4:
                result = -400;
                break;
            case 5:
                result = -800;
                break;
            case 6:
                result = -1600;
                break;
            case 7:
                result = -3200;
                break;
            default:
                result = 0;
        }
        return result;
    }

    public static int fitnessTotalHoursPerWeek(final int hourCount, final int workerHourCount) {
        int difference = Math.abs(workerHourCount - hourCount);
        if (difference < 3) {
            return -50;
        } else if (difference < 6) {
            return -200;
        } else if (difference < 9) {
            return -800;
        } else if (difference < 12) {
            return -2400;
        }
        return -10000;
    }
}
