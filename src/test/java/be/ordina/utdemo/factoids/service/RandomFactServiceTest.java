/**
 * 
 */
package be.ordina.utdemo.factoids.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.Random;

import junit.framework.Assert;
import lombok.SneakyThrows;

import org.easymock.EasyMockSupport;
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
public class RandomFactServiceTest extends EasyMockSupport {
    private final int SIZE = 10;

    /**
     * Tested object
     * 
     */
    private RandomFactService service;

    private Random random;
    private FactProvider provider;

    /**
     * init
     */
    @Before
    @SneakyThrows
    public final void init() {
        random = createMock(Random.class);
        provider = createMock(FactProvider.class);
        service = new RandomFactService(provider, random);
    }

    /**
     * Test method for
     * {@link be.ordina.utdemo.factoids.service.RandomFactService#getAFact()}.
     */
    @Test
    public final void testGetAFact() {
        // Fact: easyMock showed that size() was called SIZE time in
        // original code => updated code
        expect(provider.size()).andReturn(SIZE);
        for (int i = 0; i < SIZE; ++i) {
            expect(random.nextInt(i+1)).andReturn(i);
            expect(provider.getFact(i)).andReturn(
                    new Fact(String.format("Fact%d", i)));

        }
        replay(random);
        replay(provider);

        for (int i = 0; i < SIZE; ++i) {
            String aFact = service.getAFact().getContent();
            String eFact = String.format("Fact%d", SIZE - i - 1);

            Assert.assertEquals(eFact, aFact);
        }

        verify(random);
        verify(provider);
    }

}
