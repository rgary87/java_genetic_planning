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
    public int idx;

    public Hour(int idx, int slotCount, Hour pPrevHour) {
        this.slots = new ArrayList<>(slotCount);
        for (int i = 0; i < slotCount; i++) {
            this.slots.add(new Slot());
        }
        if (pPrevHour != null) {
            pPrevHour.nextHour = this;
        }
        this.prevHour = pPrevHour;
    }

    public Hour(int idx, Hour pPrevHour) {
        if (pPrevHour != null) {
            pPrevHour.nextHour = this;
        }
        this.prevHour = pPrevHour;
        this.idx = idx;
    }

    public void initSlots(int slotCount) {
        this.slots = new ArrayList<>(slotCount);
        for (int i = 0; i < slotCount; i++) {
            this.slots.add(new Slot());
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

