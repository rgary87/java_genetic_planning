/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary.genome;

import fr.rgary.genome.enums.Origin;

/**
 * Class Generator.
 */
public class Generator {

    public static DNA generate(final DNA dna) {
        dna.origin = Origin.RE_GENERATION;
        dna.realOrigin = Origin.RE_GENERATION;
        for (final Week week : dna.weeks) {
            for (final Day day : week.days) {
                for ()
            }
        }
    }
    /*
        @staticmethod
    def generate(dna: DNA) -> DNA:
        dna.origin = GenomeOrigin.RE_GENERATION
        dna.real_origin = GenomeOrigin.RE_GENERATION
        for week in dna.weeks:
            for day in week.days:
                # print(f"Gen a new Day !")
                for h in range(0, day.hours_count):
                    for slot in day.hours[h]:
                        worker = week.workers.get_random_worker()
                        day.recurs_set_worker(h, worker, min_continuous_hours=4)
        # self.fill_holes(min_continuous_hours=3)
        # self.fill_holes(min_continuous_hours=2)
        DNAGenerator.fill_holes(dna, min_continuous_hours=1)
        return dna

    @staticmethod
    def generate_full() -> DNA:
        dna = DNA()
        for week in dna.weeks:
            for day in week.days:
                # print(f"Gen a new Day !")
                for h in range(0, day.hours_count):
                    for slot in day.hours[h]:
                        worker = week.workers.get_random_worker()
                        day.recurs_set_worker(h, worker, min_continuous_hours=4)
        # self.fill_holes(min_continuous_hours=3)
        # self.fill_holes(min_continuous_hours=2)
        DNAGenerator.fill_holes(dna, min_continuous_hours=1)
        return dna


    @staticmethod
    def fill_holes(dna: DNA, min_continuous_hours: int):
        for week in dna.weeks:
            worker = week.workers.get_worker_with_most_remaining_hours()
            day, hour = week.has_any_day_any_hour_slot_remaining()
            exception_workers = []
            while worker is not None and worker.remaining_hours >= min_continuous_hours and day is not None:
                # print(f"Worker is {worker.name:10} with {worker.remaining_hours} hours to place")
                if not day.recurs_set_worker(hour, worker, min_continuous_hours):
                    exception_workers.append(worker)
                    worker = week.workers.get_worker_with_most_remaining_hours_except(exception_workers)
                else:
                    worker = week.workers.get_worker_with_most_remaining_hours()
                day, hour = week.has_any_day_any_hour_slot_remaining()


     */
}
