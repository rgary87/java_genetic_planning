/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary;

import fr.rgary.exception.WorkerException;
import fr.rgary.genome.DNA;
import fr.rgary.genome.Day;
import fr.rgary.genome.Generator;
import fr.rgary.genome.Hour;
import fr.rgary.genome.Week;
import fr.rgary.genome.fitness.Fitness;


import java.awt.dnd.DnDConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class App.
 */
public class App {

    public static void printPhenotype(final DNA pDNA) {
//        System.out.printf("DNA: %n");
        for (Week week : pDNA.weeks) {
//            System.out.printf("Week: %n");
            for (Day day : week.days) {
//                System.out.printf("\tDay:%n");
                for (Hour hour : day.hours) {
//                    System.out.printf("\t\t%s%n", hour.slots);
                    System.out.printf("");
                }
            }
        }
    }


    public static void main(String[] args) {
        int popSize = 5000;
        List<DNA> dnaList;
        dnaList = new ArrayList<>(popSize);
        for (int i = 0; i < popSize ; i++ ) {
            dnaList.add(Generator.generate());
        }
        Long startTime = System.currentTimeMillis();
        for (int j = 1; j < 10000; j++) {
//            for (int i = 0; i < 100_000; i++) {
//                DNA dna = null;
//                dna = Generator.generate();
//                dnaList.add(dna);
//            }
//            for (DNA dna : dnaList) {
//                printPhenotype(dna);
//            }
            dnaList = LifeCycle.lifeCycle(dnaList, j, popSize);
            dnaList = Fitness.sortDnaPerScore(dnaList);
//            System.out.printf("Score: %d%n", dnaList.get(0).score);
//            if (j % 100 == 0) {
            for (int i = 0; i < 15; i++) {
                DNA dna = dnaList.get(i);
                System.out.printf("DNA[%-2d]: %-7d | %-14s " +
                        "| %-14s | Built at %-3d " +
                        "| Is inter-day valid %-5b " +
                        "| Is per-day work time valid %-5b %n", i, dna.score, dna.origin, dna.realOrigin, dna.builtAtGen, dna.validInterday, dna.validWorkerTimePerDay);
            }
            System.out.printf("%1$s%1$s%1$s%1$s%1$s%1$s%1$s%1$s%n", "======");
//            System.out.printf("1 generations took: %dms | Score at %d: %d%n", System.currentTimeMillis() - startTime, j, dnaList.get(0).score);
            startTime = System.currentTimeMillis();
//            }
//            for (final DNA dna : dnaList) {
//            }
        }
    }

}
