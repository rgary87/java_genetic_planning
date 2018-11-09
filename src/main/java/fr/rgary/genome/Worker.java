/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

/**
 * Class Worker.
 */
public class Worker {
    public String name;
    public int remainingHours;
    public int baseHourTarget;

    public Worker(final String pName, final int pRemainingHours) {
        name = pName;
        remainingHours = pRemainingHours;
        baseHourTarget = pRemainingHours;
    }

    public Worker(final Worker that) {
        if (that != null) {
            this.name = that.name;
            this.remainingHours = that.remainingHours;
            this.baseHourTarget = that.baseHourTarget;
        }
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
