/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

/**
 * Class Slot.
 */
public class Slot {
    public Worker worker = null;

    public Slot() {
    }

    public Slot(final Slot that) {
        this.worker = that.worker;
    }

    public boolean equals(final Slot that) {
        return this.worker.name.equals(that.worker.name);
    }
}
