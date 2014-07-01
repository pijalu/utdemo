/**
 * 
 */
package be.ordina.utdemo.factoids.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import junit.framework.Assert;
import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;

/**
 * Test random fact
 * 
 * @author ppoissinger
 * 
 */
public class RandomFactServiceTest {
    final int SIZE = 10;
    /**
     * Tested object
     * 
     */
    private RandomFactService service;

    /**
     * inits
     */
    @Before
    @SneakyThrows
    public final void init() {
        // Build a file provider, based on a known file
        FactProvider provider = mock(FactProvider.class);
        // Random
        Random random = mock(Random.class);

        when(provider.size()).thenReturn(SIZE);
        for (int i = 0; i < SIZE; ++i) {
            when(provider.getFact(i)).thenReturn(new Fact("Fact" + i));
            when(random.nextInt(i + 1)).thenReturn(i);
        }

        // Build service
        service = new RandomFactService(provider, random);
    }

    /**
     * Test method for
     * {@link be.ordina.utdemo.factoids.service.RandomFactService#getAFact()}.
     * We expect 'mocked' random order
     */
    @Test
    public final void testGetAFact() {
        for (int i = SIZE; i > 0; --i) {
            String actual = service.getAFact().getContent();
            String expected = "Fact" + (i - 1);
            Assert.assertEquals(expected, actual);
        }
    }

}
