/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome.fitness;

/**
 * Class FitnessValues.
 */
public class FitnessValues {

    public static final int VALID_INTERDAY = 10000;
    public static final int VALID_PER_DAY = 500;
    public static final int FITNESS_EMPTY_WORKER_SLOT = -50;
    public static final int FITNESS_UNDERHOUR_PER_WEEK = -3;

    public static int fitnessContinuousHoursValues(final int hourCount) {
        int diff = Math.abs(8 - hourCount);
        if (diff == 0) {
            return 200;
        } else if (diff == 8) {
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
            case 0:
                result = 200;
                break;
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
        if (difference == 0) {
            return 0;
        } else if (difference < 2) {
            return -100;
        } else if (difference < 4) {
            return -200;
        } else if (difference < 6) {
            return -400;
        } else if (difference < 8) {
            return -800;
        } else if (difference < 10) {
            return -1600;
        } else if (difference < 12) {
            return -3200;
        }
        return -10000;
    }
}
