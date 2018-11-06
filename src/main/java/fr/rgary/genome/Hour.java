/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Hour.
 */
public class Hour {
    private List<String> slots;

    public Hour(int slotCount) {
        this.slots = new ArrayList<String>();
    }

    /**
     * slots Getter.
     *
     * @return slots Its value
     */
    public final List<String> getSlots() {
        return this.slots;
    }

    /**
     * slots setter.
     *
     * @param pSlots new value
     */
    public final void setSlots(final List<String> pSlots) {
        this.slots = pSlots;
    }
}

