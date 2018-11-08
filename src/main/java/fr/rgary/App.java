/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary;

import fr.rgary.genome.DNA;
import fr.rgary.genome.Generator;


import java.util.ArrayList;
import java.util.List;

/**
 * Class App.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World");
        DNA dna = new DNA();
        List<DNA> dnaList = new ArrayList<>(100_000_000);
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100_000_000; i++) {
            dnaList.add(Generator.generate());
        }
        System.out.printf("Took: %dms%n", System.currentTimeMillis() - startTime);
    }

}
