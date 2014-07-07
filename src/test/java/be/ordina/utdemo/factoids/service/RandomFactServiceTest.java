/**
 *
 */
package be.ordina.utdemo.factoids.service;

import java.util.Random;

import junit.framework.Assert;
import lombok.SneakyThrows;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;

/**
 * Test random fact
 *
 * @author ppoissinger
 *
 */
@RunWith(JMockit.class)
public class RandomFactServiceTest {
    final int SIZE = 10;
    /**
     * Tested object
     *
     */
    private RandomFactService service;

    @Mocked
    FactProvider provider;

    @Mocked
    Random random;

    /**
     * init
     */
    @Before
    @SneakyThrows
    public final void init() {
        new NonStrictExpectations() {
            {
                provider.size();
                result = SIZE;

                for (int factIndex = 0; factIndex < SIZE; ++factIndex) {
                    int leftToSelect = factIndex + 1;
                    random.nextInt(leftToSelect);
                    result = factIndex;

                    provider.getFact(factIndex);
                    Fact fact = new Fact("Fact" + leftToSelect);
                    result = fact;
                }
            }
        };
        service = new RandomFactService(provider, random);
    }

    /**
     * Test method for
     * {@link be.ordina.utdemo.factoids.service.RandomFactService#getAFact()}.
     * We expect random order, but all item should appear once per run !
     */
    @Test
    public final void testGetAFact() {
        for (int runIndex = SIZE; runIndex != 0; runIndex--) {
            String actual = service.getAFact().getContent();
            String expected = "Fact" + runIndex;
            Assert.assertEquals(expected, actual);
        }
    }

}
