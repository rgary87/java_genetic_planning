/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary;

import fr.rgary.genome.DNA;
import fr.rgary.genome.Day;
import fr.rgary.genome.Generator;
import fr.rgary.genome.Hour;
import fr.rgary.genome.Week;
import fr.rgary.genome.fitness.Fitness;
import fr.rgary.tools.DebugTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Class App.
 */
public class App {

    public static void printPhenotype(final DNA pDNA) {
//        System.out.printf("DNA: %n");
        for (final Week week : pDNA.weeks) {
//            System.out.printf("Week: %n");
            for (final Day day : week.days) {
//                System.out.printf("\tDay:%n");
                for (final Hour hour : day.hours) {
//                    System.out.printf("\t\t%s%n", hour.slots);
                    System.out.printf("");
                }
            }
        }
    }

    public static List<DNA> dnaList;

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                PrintGenome.printWholeGenome(App.dnaList.get(0));
            }
        });

        int popSize = 10000;
        List<DNA> dnaList;
        dnaList = new ArrayList<>(popSize);
        for (int i = 0; i < popSize; i++) {
            dnaList.add(Generator.generate());
        }
        App.dnaList = dnaList;
        long startTime = System.currentTimeMillis();
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
            if (j % 1 == 0) {
                for (int i = 0; i < 75; i++) {
                    DNA dna = dnaList.get(i);
                    System.out.printf("DNA[%-2d]: %-7d | %-14s | %-14s | Built at %-3d | Is inter-day valid %-5b | Is per-day work time valid %-5b %n", i, dna.score, dna.origin, dna.realOrigin, dna.builtAtGen, dna.validInterday, dna.validWorkerTimePerDay);
                }
                System.out.printf("1 generations took: %dms | Score at %d: %d | With average duplicate removal: %2f%n", System.currentTimeMillis() - startTime, j, dnaList.get(0).score, DebugTool.getAverageRemovedDuplicate());
                System.out.printf("%1$s%1$s%1$s%1$s%1$s%1$s%1$s%1$s%1$s%1$s%1$s%1$s%1$s%1$s%n", "======");
                startTime = System.currentTimeMillis();
//                App.dnaList = dnaList;
//                PrintGenome.printWholeGenome(App.dnaList.get(0));
            }
            App.dnaList = dnaList;
//            Runtime.getRuntime().exit(0);
        }
    }

}
