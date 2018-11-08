/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

import fr.rgary.genome.enums.Origin;

import java.util.ArrayList;
import java.util.List;

/**
 * Class DNA.
 */
public class DNA {

    public int idx = 0;
    public List<Week> weeks;
    public int score = 0;
    public Origin origin = Origin.RE_GENERATION;
    public Origin realOrigin = Origin.RE_GENERATION;
    public int builtAtGen = 0;
    public boolean validInterday = false;
    public boolean validWorkerTimePerDay = false;
    public String representation;
    public int weekCount = 1;
    public List<Slot> slots = new ArrayList<>();

    public DNA() {
        weeks = new ArrayList<>();
        for (int i = 0; i < this.weekCount; i++) {
            Week week = new Week();
            weeks.add(week);
            for (Day day : week.days) {
                for (Hour hour : day.hours) {
                    this.slots.addAll(hour.slots);
                }
            }
        }
    }

    public static DNA checkIfViableInterday(final DNA pDNA) {
        Week prevWeek = null;
        for (final Week week : pDNA.weeks) {
            if (!week.checkWorkerEveningMorningRule(prevWeek)) {
                return pDNA;
            }
            prevWeek = week;
        }
        pDNA.validInterday = true;
        return pDNA;
    }

    public static DNA checkIfViableForWorkTimePerDay(final DNA pDNA) {
        for (final Week week : pDNA.weeks) {
            for (final Day day : week.days) {
                if (!day.checkHourCountPerWorker(week.workers)) {
                    return pDNA;
                }
            }
        }
        pDNA.validWorkerTimePerDay = true;
        return pDNA;
    }

    public boolean equals(final DNA pO) {
        if (this.slots.size() != pO.slots.size()) {
            return false;
        }
        for (int i = 0; i < this.slots.size(); i++) {
            if (!this.slots.get(i).equals(pO.slots.get(i))) {
                return false;
            }
        }
        return true;
    }
}
