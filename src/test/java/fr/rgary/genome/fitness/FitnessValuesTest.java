package fr.rgary.genome.fitness;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Class FitnessValuesTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:service-application-context.xml"})
public class FitnessValuesTest {

    @Test
    public void fitnessContinuousHoursValues() {
        Assert.assertEquals( 0,     FitnessValues.fitnessContinuousHoursValues(8));
        Assert.assertEquals( -100,  FitnessValues.fitnessContinuousHoursValues(7));
        Assert.assertEquals( -200,  FitnessValues.fitnessContinuousHoursValues(6));
        Assert.assertEquals( -400,  FitnessValues.fitnessContinuousHoursValues(5));
        Assert.assertEquals( -800,  FitnessValues.fitnessContinuousHoursValues(4));
        Assert.assertEquals( -1600,  FitnessValues.fitnessContinuousHoursValues(3));
        Assert.assertEquals( -3200,  FitnessValues.fitnessContinuousHoursValues(2));
        Assert.assertEquals( -6400,  FitnessValues.fitnessContinuousHoursValues(1));
        Assert.assertEquals( 0,     FitnessValues.fitnessContinuousHoursValues(0));

    }
}