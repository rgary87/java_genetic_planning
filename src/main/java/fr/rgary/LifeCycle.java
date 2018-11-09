/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary;

import com.google.common.collect.Lists;
import fr.rgary.genome.DNA;
import fr.rgary.genome.Generator;
import fr.rgary.genome.Mutator;
import fr.rgary.genome.Slot;
import fr.rgary.genome.enums.Origin;
import fr.rgary.genome.fitness.Fitness;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Class LifeCycle.
 */
@Component
public class LifeCycle {

    public static void checkDNAs(List<DNA> list, String there) {
        for (DNA dna : list) {
            if (dna == null) {
                continue;
            }
            for (Slot slot : dna.slots) {
                if (slot.worker == null) {
                    return;
                }
            }
        }
        System.out.printf("DNA WAS FINE IN %s%n", there);
    }

    public static List<DNA> removeDuplicate(final List<DNA> pDNAList) {
        Set<String> seen = new HashSet<>( pDNAList.size() );
        List<DNA> remains = new ArrayList<>( pDNAList.size() );
        for (final DNA dna : pDNAList) {
            if (!seen.contains(dna.getRepresentation())) {
                seen.add(dna.getRepresentation());
                remains.add(dna);
            }
        }
//        System.out.printf("BaseListSize:%-5d | RemainListSize:%-5d%n", pDNAList.size(), remains.size());
        return remains;
    }

    public static List<DNA> lifeCycle(final List<DNA> pDNAList, final int generation, final int popSize) {
//        checkDNAs(pDNAList, "LIFE CYCLE START");
        int splittedListSize = pDNAList.size() / 10;
        final List<List<DNA>> splittedDNA = Lists.partition(pDNAList, splittedListSize);
        final List<List<DNA>> regenDNAList = new ArrayList<>(10);
        List<DNA> result = new ArrayList<>();

        // REBUILD
        regenDNAList.add(new ArrayList<>(splittedDNA.get(0)));
        for (final DNA dna : regenDNAList.get(0)) {
            dna.origin = Origin.PREV_BEST;
        }

        //INHERITANCE
        regenDNAList.add(inheritance(splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4)), splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4))));
        regenDNAList.add(inheritance(splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4)), splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4))));

        //MUTATION
        regenDNAList.add(massMutate(splittedDNA.get(0), 3));
        regenDNAList.add(massMutate(splittedDNA.get(1), 3));
        regenDNAList.add(massMutate(regenDNAList.get(0), 3));
        regenDNAList.add(massMutate(regenDNAList.get(1), 3));

        //INHERITANCE
        regenDNAList.add(inheritance(regenDNAList.get(1), regenDNAList.get(3)));
        regenDNAList.add(inheritance(regenDNAList.get(2), regenDNAList.get(4)));
        regenDNAList.add(inheritance(splittedDNA.get(0), splittedDNA.get(0)));

        //REBUILD SINGLE LIST
        for (final List<DNA> l : regenDNAList) {
            result.addAll(l);
        }
//        result.removeAll(Collections.singleton(null));

        //CHECK DNA VIABILITY
        result.parallelStream().forEach(DNA::checkIfViableInterday);
//        for (DNA dna : result) {
//            DNA.checkIfViableInterday(dna);
//        }
        result.parallelStream().forEach(DNA::checkIfViableForWorkTimePerDay);
//        for (DNA dna : result) {
//            DNA.checkIfViableForWorkTimePerDay(dna);
//        }

        //REMOVE DUPLICATES
//        result.removeAll(Collections.singleton(null));
//        Set<DNA> set = new HashSet<>(result);
//        result.clear();
//        result.addAll(set);

        //REGENERATION
//        IntStream.range(0, popSize - result.size()).parallel().forEach(i -> result.add(Generator.generate()));
        for (int i = 0; i < popSize - result.size(); i++) {
            result.add(Generator.generate());
        }

//        result.removeAll(Collections.singleton(null));
        for (final DNA dna : result) {
            if (dna.builtAtGen == 0) {
                dna.builtAtGen = generation;
            }
        }
        result = removeDuplicate(result);
        return result;
    }

    public static List<DNA> massMutate(final List<DNA> pDNAList, final int mutationRate) {
        List<DNA> result = new ArrayList<>();
//        pDNAList.parallelStream().forEach(dna -> Mutator.mutate(dna, mutationRate));
        for (final DNA dna : pDNAList) {
            result.add(Mutator.mutate(new DNA(dna), mutationRate));
        }
        result.removeAll(Collections.singleton(null));
        return result;
    }

    public static List<DNA> inheritance(final List<DNA> one, final List<DNA> two) {
        int minListSize = Math.min(one.size(), two.size());
        List<DNA> result = new ArrayList<>(minListSize);
//        IntStream.range(0, minListSize).parallel().forEach(i -> result.add(Fitness.inheritance(one.get(i), two.get(i))));
        for (int i = 0; i < minListSize; i++) {
            result.add(Fitness.inheritance(one.get(i), two.get(i)));
        }
//        result.removeAll(Collections.singleton(null));
        return result;
    }


}
