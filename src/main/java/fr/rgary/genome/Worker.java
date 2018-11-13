/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

/**
 * Class Worker.
 */
public class Worker {
    public final String name;
    public final char idx;
    public int remainingHours;
    public int baseHourTarget;

    public Worker(final String pName, final char idx, final int pRemainingHours) {
        this.name = pName;
        this.idx = idx;
        remainingHours = pRemainingHours;
        baseHourTarget = pRemainingHours;
    }

    public Worker(final Worker that) {
        this.name = that.name;
        this.idx = that.idx;
        this.remainingHours = that.remainingHours;
        this.baseHourTarget = that.baseHourTarget;
    }


    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", remainingHours=" + remainingHours +
                '}';
    }

    public boolean equals(final Worker pO) {
        if (pO == null) {
            return false;
        }
        return name != null ? name.equals(pO.name) : pO.name == null;
    }
}
