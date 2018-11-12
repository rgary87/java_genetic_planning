/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

import java.util.ArrayList;

/**
 * Class Hour.
 */
public class Hour {
    public ArrayList<Slot> slots;
    public Hour nextHour = null;
    public Hour prevHour = null;
    public int idx = 0;
    public Day day;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(9 + this.idx).append(": ");
        for (final Slot slot : this.slots) {
            sb.append('[').append(slot.toString()).append(']');
        }
        return sb.toString();
    }

    public Hour(Day pDay, int idx, int slotCount, Hour pPrevHour) {
        this.slots = new ArrayList<>(slotCount);
        for (int i = 0; i < slotCount; i++) {
            this.slots.add(new Slot(this));
        }
        if (pPrevHour != null) {
            pPrevHour.nextHour = this;
        }
        this.prevHour = pPrevHour;
        this.day = pDay;
        this.idx = idx;
    }

    public Hour(Day pDay, int idx, Hour pPrevHour) {
        if (pPrevHour != null) {
            pPrevHour.nextHour = this;
        }
        this.prevHour = pPrevHour;
        this.day = pDay;
        this.idx = idx;
    }

    public Hour(final Hour that, final Hour thatPrevHour, final Day thatDay) {
        this.prevHour = thatPrevHour;
        if (prevHour != null) {
            prevHour.nextHour = this;
        }
        this.idx = that.idx;
        this.day = thatDay;
        this.slots = new ArrayList<>(that.slots.size());
        for (final Slot slot : that.slots) {
            this.slots.add(new Slot(slot, this));
        }
    }

    public void initSlots(int slotCount) {
        this.slots = new ArrayList<>(slotCount);
        for (int i = 0; i < slotCount; i++) {
            this.slots.add(new Slot(this));
        }
    }

    public boolean hasOpenSlot() {
        for (final Slot slot : this.slots) {
            if (slot.worker == null) {
                return true;
            }
        }
        return false;
    }

}

