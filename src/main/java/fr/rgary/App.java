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


import java.awt.dnd.DnDConstants;
import java.util.ArrayList;
import java.util.List;

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
        List<DNA> dnaList;
        for (int j = 0; j < 100; j++) {
            Long startTime = System.currentTimeMillis();
            dnaList = new ArrayList<>(100_000);
            for (int i = 0; i < 100_000; i++) {
                DNA dna = null;
                dna = Generator.generate();
                dnaList.add(dna);
            }
            System.out.printf("Took: %dms%n", System.currentTimeMillis() - startTime);
            for (DNA dna : dnaList) {
                printPhenotype(dna);
            }
        }
    }

}
