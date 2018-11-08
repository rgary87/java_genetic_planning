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
        this.worker = that.worker;
        this.hour = that.hour;
    }

    public boolean equals(final Slot that) {
        return this.worker.name.equals(that.worker.name);
    }
}
