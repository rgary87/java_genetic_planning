/*
 * Copyright (C) 2018 Eir.
 */
package fr.rgary;

import com.google.common.collect.Lists;
import fr.rgary.genome.DNA;
import fr.rgary.genome.Generator;
import fr.rgary.genome.Mutator;
import fr.rgary.genome.enums.Origin;
import fr.rgary.genome.fitness.Fitness;
import fr.rgary.tools.DebugTool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Class LifeCycle.
 */
@Component
public class LifeCycle {

    public static List<DNA> removeDuplicate(final List<DNA> pDNAList) {
        List<String> seen = new ArrayList<>(pDNAList.size());
        List<DNA> remains = new ArrayList<>(pDNAList.size());
        pDNAList.parallelStream().forEach(DNA::buildRepresentation);
        int i = 0;
        for (final DNA dna : pDNAList) {
            if (!seen.contains(dna.representation)) {
                seen.add(dna.representation);
                remains.add(dna);
            } else {
                i++;
            }
        }
        DebugTool.removedDuplicateCountList.add(i);
        return remains;
    }

    public static List<DNA> lifeCycle(final List<DNA> pDNAList, final int generation, final int popSize) {
        int splittedListSize = pDNAList.size() / 10;
        final List<List<DNA>> splittedDNA = Lists.partition(pDNAList, splittedListSize);
        final List<List<DNA>> regenDNAList = new ArrayList<>(10);
        List<DNA> result = new ArrayList<>();

        /* REBUILD */
        regenDNAList.add(new ArrayList<>(splittedDNA.get(0)));
        for (final DNA dna : regenDNAList.get(0)) {
            dna.origin = Origin.PREV_BEST;
        }

        /* INHERITANCE */
        regenDNAList.add(inheritance(splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4)), splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4))));
        regenDNAList.add(inheritance(splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4)), splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4))));
        regenDNAList.add(inheritance(regenDNAList.get(1), splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4))));
        regenDNAList.add(inheritance(regenDNAList.get(2), splittedDNA.get(ThreadLocalRandom.current().nextInt(0, 4))));
        regenDNAList.add(inheritance(splittedDNA.get(0), splittedDNA.get(0)));

        /*MUTATION */
        regenDNAList.add(massMutate(regenDNAList.get(0), ThreadLocalRandom.current().nextInt(1, 5)));
        regenDNAList.add(massMutate(regenDNAList.get(1), ThreadLocalRandom.current().nextInt(1, 5)));
        regenDNAList.add(massMutate(regenDNAList.get(2), ThreadLocalRandom.current().nextInt(1, 5)));
        regenDNAList.add(massMutate(regenDNAList.get(3), ThreadLocalRandom.current().nextInt(1, 5)));

        /* REBUILD SINGLE LIST */
        for (final List<DNA> l : regenDNAList) {
            result.addAll(l);
        }

        /* CHECK DNA VIABILITY */
        result.parallelStream().forEach(DNA::checkIfViableInterday);
        result.parallelStream().forEach(DNA::checkIfViableForWorkTimePerDay);

        //REGENERATION
        result = removeDuplicate(result);
        final List<DNA> regenerated = new ArrayList<>(popSize - result.size());
        IntStream.range(0, popSize - result.size()).forEach(i -> regenerated.add(Generator.generate()));
        result.addAll(regenerated);

        for (final DNA dna : result) {
            if (dna.builtAtGen == 0) {
                dna.builtAtGen = generation;
            }
        }
        return result;
    }

    public static List<DNA> massMutate(final List<DNA> pDNAList, final int mutationRate) {
        List<DNA> result = new ArrayList<>(pDNAList.size());
        pDNAList.forEach(dna -> result.add(new DNA(dna)));
        result.parallelStream().forEach(dna -> Mutator.mutate(dna, mutationRate));
        return result;
    }

    public static List<DNA> inheritance(final List<DNA> one, final List<DNA> two) {
        int minListSize = Math.min(one.size(), two.size());
        List<DNA> result = new ArrayList<>(minListSize);
        IntStream.range(0, minListSize).forEach(i -> result.add(Fitness.inheritance(one.get(i), two.get(i))));
        return result;
    }

}
