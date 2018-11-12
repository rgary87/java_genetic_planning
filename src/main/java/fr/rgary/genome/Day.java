/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

import fr.rgary.genome.enums.DaysOfWeek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Class Day.
 */
public class Day {

    public int hoursCount;
    public DaysOfWeek dayOfWeek;
    public List<Hour> hours = new ArrayList<>();
    public Week week;

    public Day(final DaysOfWeek dayOfWeek, final Week pWeek) {
        this.week = pWeek;
        this.dayOfWeek = dayOfWeek;
        Hour hour0 = new Hour(this, 0, null);
        Hour hour1 = new Hour(this, 1, hour0);
        Hour hour2 = new Hour(this, 2, hour1);
        Hour hour3 = new Hour(this, 3, hour2);
        Hour hour4 = new Hour(this, 4, hour3);
        Hour hour5 = new Hour(this, 5, hour4);
        Hour hour6 = new Hour(this, 6, hour5);
        Hour hour7 = new Hour(this, 7, hour6);
        Hour hour8 = new Hour(this, 8, hour7);
        Hour hour9 = new Hour(this, 9, hour8);
        Hour hour10 = new Hour(this, 10, hour9);
        Hour hour11 = new Hour(this, 11, hour10);
        Hour hour12 = new Hour(this, 12, hour11);
        Hour hour13 = new Hour(this, 13, hour12);
        Hour hour14 = new Hour(this, 14, hour13);
        Hour hour15 = new Hour(this, 15, hour14);
        Hour hour16 = new Hour(this, 16, hour15);
        switch (dayOfWeek) {
            case MONDAY:
                hour0.initSlots(1);
                hour1.initSlots(1);
                hour2.initSlots(2);
                hour3.initSlots(2);
                hour4.initSlots(2);
                hour5.initSlots(2);
                hour6.initSlots(2);
                hour7.initSlots(2);
                hour8.initSlots(2);
                hour9.initSlots(2);
                hour10.initSlots(2);
                hour11.initSlots(3);
                hour12.initSlots(3);
                hour13.initSlots(3);
                hour14.initSlots(2);
                hour15.initSlots(2);
                hour16.initSlots(2);
                this.hours = new ArrayList<>(Arrays.asList(hour0, hour1, hour2, hour3, hour4, hour5, hour6, hour7, hour8, hour9, hour10, hour11, hour12, hour13, hour14, hour15, hour16));
                break;
            case TUESDAY:
                hour0.initSlots(1);
                hour1.initSlots(1);
                hour2.initSlots(2);
                hour3.initSlots(2);
                hour4.initSlots(2);
                hour5.initSlots(2);
                hour6.initSlots(2);
                hour7.initSlots(2);
                hour8.initSlots(2);
                hour9.initSlots(2);
                hour10.initSlots(2);
                hour11.initSlots(2);
                hour12.initSlots(2);
                hour13.initSlots(2);
                hour14.initSlots(2);
                hour15.initSlots(2);
                hour16.initSlots(2);
                break;
            case WEDNESDAY:
                hour0.initSlots(1);
                hour1.initSlots(1);
                hour2.initSlots(2);
                hour3.initSlots(3);
                hour4.initSlots(3);
                hour5.initSlots(3);
                hour6.initSlots(2);
                hour7.initSlots(2);
                hour8.initSlots(2);
                hour9.initSlots(2);
                hour10.initSlots(2);
                hour11.initSlots(2);
                hour12.initSlots(2);
                hour13.initSlots(2);
                hour14.initSlots(2);
                hour15.initSlots(2);
                hour16.initSlots(2);
                break;
            case THURSDAY:
                hour0.initSlots(1);
                hour1.initSlots(1);
                hour2.initSlots(2);
                hour3.initSlots(2);
                hour4.initSlots(2);
                hour5.initSlots(2);
                hour6.initSlots(2);
                hour7.initSlots(2);
                hour8.initSlots(2);
                hour9.initSlots(3);
                hour10.initSlots(3);
                hour11.initSlots(3);
                hour12.initSlots(3);
                hour13.initSlots(3);
                hour14.initSlots(3);
                hour15.initSlots(2);
                hour16.initSlots(2);
                break;
            case FRIDAY:
                hour0.initSlots(1);
                hour1.initSlots(1);
                hour2.initSlots(2);
                hour3.initSlots(2);
                hour4.initSlots(2);
                hour5.initSlots(2);
                hour6.initSlots(2);
                hour7.initSlots(2);
                hour8.initSlots(2);
                hour9.initSlots(3);
                hour10.initSlots(3);
                hour11.initSlots(3);
                hour12.initSlots(3);
                hour13.initSlots(3);
                hour14.initSlots(3);
                hour15.initSlots(2);
                hour16.initSlots(2);
                break;
            case SATURDAY:
                hour0.initSlots(1);
                hour1.initSlots(2);
                hour2.initSlots(3);
                hour3.initSlots(4);
                hour4.initSlots(4);
                hour5.initSlots(4);
                hour6.initSlots(4);
                hour7.initSlots(4);
                hour8.initSlots(4);
                hour9.initSlots(4);
                hour10.initSlots(4);
                hour11.initSlots(4);
                hour12.initSlots(4);
                hour13.initSlots(4);
                hour14.initSlots(3);
                hour15.initSlots(3);
                hour16.initSlots(3);
                break;
            case SUNDAY:
            default:
                hour0.initSlots(1);
                hour1.initSlots(2);
                hour2.initSlots(3);
                hour3.initSlots(4);
                hour4.initSlots(4);
                hour5.initSlots(4);
                hour6.initSlots(4);
                hour7.initSlots(4);
                hour8.initSlots(4);
                hour9.initSlots(3);
                hour10.initSlots(3);
                hour11.initSlots(3);
                hour12.initSlots(3);
                hour13.initSlots(3);
                hour14.initSlots(2);
                hour15.initSlots(2);
                hour16.initSlots(2);
        }
        this.hours = new ArrayList<>(Arrays.asList(hour0, hour1, hour2, hour3, hour4, hour5, hour6, hour7, hour8, hour9, hour10, hour11, hour12, hour13, hour14, hour15, hour16));
    }

    public Day(final Day that, final Week pWeek) {
        this.dayOfWeek = that.dayOfWeek;
        this.week = pWeek;
        Hour prevHour = null;
        for (final Hour hour : that.hours) {
            final Hour tmp = new Hour(hour, prevHour, this);
            this.hours.add(tmp);
            prevHour = tmp;
        }
    }

    public static int getHoursCountBetweenDays(final Hour currentDayHour, final Hour prevDayHour) {
        if (prevDayHour == null || currentDayHour == null) {
            return Integer.MAX_VALUE;
        }
        return currentDayHour.idx + 7 + (16 - prevDayHour.idx);
    }

    public static Hour getFirstHourForWorker(final Day pDay, final Worker pWorker) {
        for (final Hour hour : pDay.hours) {
            for (final Slot slot : hour.slots) {
                if (slot.worker == null) {
                    continue;
                }
                if (slot.worker.equals(pWorker)) {
                    return hour;
                }
            }
        }
        return null;
    }

    public static Hour getLastHourForWorker(final Day pDay, final Worker pWorker) {
        ListIterator li = pDay.hours.listIterator(pDay.hours.size());
        while (li.hasPrevious()) {
            Hour hour = (Hour) li.previous();
            for (final Slot slot : hour.slots) {
                if (slot.worker == null) {
                    continue;
                }
                if (slot.worker.equals(pWorker)) {
                    return hour;
                }
            }
        }
        return null;
    }

    public static boolean workerIsAssignedToHour(Worker pWorker, Hour pHour) {
        for (final Slot slot : pHour.slots) {
            if (slot.worker == null) {
                continue;
            }
            if (slot.worker.equals(pWorker)) {
                return true;
            }
        }
        return false;
    }

    public Day copyMe(final Day that) {
        Day day = new Day(that.dayOfWeek, that.week);
        day.hours = new ArrayList<>(15);
        Hour prevHour = null;
        for (final Hour hour : that.hours) {
            Hour nhour = new Hour(day, hour.idx, hour.slots.size(), prevHour);
            prevHour = nhour;
            for (final Slot slot : hour.slots) {
                nhour.slots.add(new Slot(slot));
            }
        }
        day.hoursCount = that.hoursCount;
        return day;
    }

    public Slot hasAnyHourSlotRemaining() {
        for (final Hour hour : this.hours) {
            for (final Slot slot : hour.slots) {
                if (slot.worker == null) {
                    return slot;
                }
            }
        }
        return null;
    }

    public boolean checkHourCountPerWorker(final List<Worker> pWorkers) {
        Map<String, Integer> workerHourCount = new HashMap<>();
        for (final Worker worker : pWorkers) {
            workerHourCount.put(worker.name, 0);
        }
        for (final Hour hour : this.hours) {
            for (final Slot slot : hour.slots) {
                if (slot.worker != null) {
                    int count = workerHourCount.get(slot.worker.name) + 1;
                    workerHourCount.replace(slot.worker.name, count);
                }
            }
        }
        for (final Map.Entry<String, Integer> entries : workerHourCount.entrySet()) {
            if (entries.getValue() > 12) {
                return false;
            }
        }
        return true;
    }

    public Slot hourHasOpenSlotForWorker(final Hour hour, final Worker worker) {
        if (workerIsAssignedToHour(worker, hour)) {
            return null;
        }
        for (final Slot slot : hour.slots) {
            if (slot.worker == null) {
                return slot;
            } else if (slot.worker.equals(worker)) {
                return null;
            }
        }
        return null;
    }

    public boolean setWorkerRecursively(final Hour startHour, final Worker pWorker, int minContinuousHours) {
        return this.setWorkerRecursively(startHour, pWorker, minContinuousHours, 9, 0);
    }

    public boolean setWorkerRecursively(final Hour startHour, final Worker worker, int minContinuousHours, int maxContinuousHours, int recursed) {
        if (recursed == maxContinuousHours) {
            return true;
        }
        if (startHour == null) {
            return minContinuousHours < 1;
        }
        if (minContinuousHours < 1 && worker.remainingHours < 1) {
            return true;
        }

        Slot slot = this.hourHasOpenSlotForWorker(startHour, worker);
        if (slot != null) {
            slot.worker = worker;
            worker.remainingHours--;
            if (!setWorkerRecursively(startHour.nextHour, worker, minContinuousHours - 1, maxContinuousHours, recursed + 1)) {
                slot.worker = null;
                worker.remainingHours++;
                return false;
            } else {
                return true;
            }
        } else if (minContinuousHours < 1) {
            return true;
        } else {
            return false;
        }
    }

    /*
    def recurs_set_worker(self, hour_start_idx: int, worker: Worker, min_continuous_hours: int, recursed: int = 0, max_continuous_hours: int = 9):
        if recursed == max_continuous_hours:
            return True
        if worker.remaining_hours < 1 and min_continuous_hours < 1:
            return True
        elif worker.remaining_hours < 1:
            return False

        slot_idx = self.hour_has_open_slot_for_worker(hour_start_idx, worker)
        if slot_idx != -1:
            self.hours[hour_start_idx][slot_idx] = worker.name
            worker.remaining_hours -= 1
            if not self.recurs_set_worker(hour_start_idx + 1, worker, min_continuous_hours - 1, recursed=recursed+1):
                # print(f"Reset hours slot {hour_start_idx:3} | {slot_idx} to None")
                self.hours[hour_start_idx][slot_idx] = None
                worker.remaining_hours += 1
                return False
            else:
                # print(f"{hour_start_idx:3} | {slot_idx} | {worker.name}")
                return True
        elif min_continuous_hours < 1:
            return True
        else:
            return False
     */
}
