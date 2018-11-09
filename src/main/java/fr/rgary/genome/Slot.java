/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

/**
 * Class Slot.
 */
public class Slot {
    public Worker worker = null;
    public Hour hour = null;

    public Slot(Hour hour) {
        this.hour = hour;
    }

    public Slot(final Slot that) {
        this.worker = new Worker(that.worker);
        this.hour = that.hour;
    }

    public Slot(final Slot that, final Hour thatHour) {
        this.worker = new Worker(that.worker);
        this.hour = thatHour;
    }

    public boolean equals(final Slot that) {
        if (that == null) {
            return false;
        }
        if (that.worker == null && this.worker != null) {
            return false;
        }
        if (this.worker == null && that.worker != null) {
            return false;
        }
        if (this.worker == null && that.worker  == null) {
            return true;
        }
        return this.worker.name.equals(that.worker.name);
    }

    @Override
    public String toString() {
        return worker!= null ? worker.name : "NULL";
    }
}
