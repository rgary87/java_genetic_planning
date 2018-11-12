package fr.rgary.genome;

import fr.rgary.LifeCycle;
import fr.rgary.PrintGenome;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class MutatorTest.
 */
public class MutatorTest {

    @Test
    public void lol() {
        List<DNA> dnas = new ArrayList<>(10);
        int popSize = 100;
        for (int i = 0; i < popSize; i++) {
            dnas.add(Generator.generate());
        }
        for (int i = 0; i < 10; i++) {
            dnas = LifeCycle.lifeCycle(dnas, 1, popSize);
        }
    }

    @Test
    public void mutate() {
        DNA dna = Generator.generate();
        PrintGenome.printWholeGenome(dna);
        dna = Mutator.mutate(dna, 15);
        PrintGenome.printWholeGenome(dna);
    }
}