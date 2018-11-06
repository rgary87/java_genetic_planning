/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class Day.
 */
public abstract class Day {
    protected List<Hour> hours;
    protected int dayIdx;
    protected int hoursCount;

    public Day(final Day that) {
        this.hours = new ArrayList<Hour>(15);
        for (Hour hour : that.hours) {
            Hour nhour = new Hour(hour.getSlots().size());

        }
    }

    public Day() {
    }

    public class Monday extends Day {
        public Monday() {
            this.hours = new ArrayList<Hour>(Arrays.asList(
                    new Hour(1),
                    new Hour(1),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2)
            ));
        }
    }
    public class Tuesday extends Day {
        public Tuesday() {
            this.hours = new ArrayList<Hour>(Arrays.asList(
                    new Hour(1),
                    new Hour(1),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2)
            ));
        }
    }
    public class Wednesday extends Day {
        public Wednesday() {
            this.hours = new ArrayList<Hour>(Arrays.asList(
                    new Hour(1),
                    new Hour(1),
                    new Hour(2),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2)
            ));
        }
    }
    public class Thursday extends Day {
        public Thursday() {
            this.hours = new ArrayList<Hour>(Arrays.asList(
                    new Hour(1),
                    new Hour(1),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(2),
                    new Hour(2)
            ));
        }
    }
    public class Friday extends Day {
        public Friday() {
            this.hours = new ArrayList<Hour>(Arrays.asList(
                    new Hour(1),
                    new Hour(1),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(2),
                    new Hour(2)
            ));
        }
    }
    public class Saturday extends Day {
        public Saturday() {
            this.hours = new ArrayList<Hour>(Arrays.asList(
                    new Hour(1),
                    new Hour(2),
                    new Hour(3),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3)
            ));
        }
    }
    public class Sunday extends Day {
        public Sunday() {
            this.hours = new ArrayList<Hour>(Arrays.asList(
                    new Hour(1),
                    new Hour(2),
                    new Hour(3),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(4),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(3),
                    new Hour(2),
                    new Hour(2),
                    new Hour(2)
            ));
        }
    }
}
