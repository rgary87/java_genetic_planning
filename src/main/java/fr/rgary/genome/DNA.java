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
            Week week = new Week(this);
            weeks.add(week);
            for (final Day day : week.days) {
                for (final Hour hour : day.hours) {
                    this.slots.addAll(hour.slots);
                }
            }
        }
    }

    public DNA(final DNA that) {
        this.idx = that.idx;
        this.weeks = new ArrayList<>(that.weeks.size());
        for (final Week week : that.weeks) {
            this.weeks.add(new Week(week, this));
        }
        this.weekCount = this.weeks.size();
        this.slots = new ArrayList<>(that.slots.size());
        for (final Week week : this.weeks) {
            for (final Day day : week.days) {
                for (final Hour hour : day.hours) {
                    this.slots.addAll(hour.slots);
                }
            }
        }
        this.origin = Origin.MUTATION;
        this.realOrigin = Origin.MUTATION;
    }

    public DNA(final List<Week> pWeeks) {
        this.weeks = new ArrayList<>(pWeeks.size());
        for (final Week week : pWeeks) {
            this.weeks.add(new Week(week, this));
        }
//        this.weeks = pWeeks;
        this.weekCount = pWeeks.size();
        for (final Week week : this.weeks) {
            for (final Day day : week.days) {
                for (final Hour hour : day.hours) {
                    this.slots.addAll(hour.slots);
                }
            }
        }
    }

    public static void checkIfViableInterday(final DNA pDNA) {
        Week prevWeek = null;
        for (final Week week : pDNA.weeks) {
            if (!week.checkWorkerEveningMorningRule(prevWeek)) {
                return;
            }
            prevWeek = week;
        }
        pDNA.validInterday = true;
    }

    public static void checkIfViableForWorkTimePerDay(final DNA pDNA) {
        for (final Week week : pDNA.weeks) {
            for (final Day day : week.days) {
                if (!day.checkHourCountPerWorker(week.workers)) {
                    pDNA.validWorkerTimePerDay = false;
                    return;
                }
            }
        }
        pDNA.validWorkerTimePerDay = true;
    }

    public synchronized void addScore(int pScore) {
        this.score += pScore;
    }

    public synchronized void resetScore() {
        this.score = 0;
    }

    public String getRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (final Slot slot : this.slots) {
            if (slot.worker == null) {
                sb.append("null");
            } else {
                sb.append(slot.worker.name);
            }
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        for (final Slot slot : this.slots) {
            sb.append(slot.worker.name);
        }
        return sb.hashCode();
    }

    @Override
    public boolean equals(final Object pO) {
        if (this == pO) {
            return true;
        }
        if (pO == null || getClass() != pO.getClass()) {
            return false;
        }

        final DNA dna = (DNA) pO;

        return hashCode() == dna.hashCode();
    }

}
