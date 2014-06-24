/**
 * 
 */
package be.ordina.utdemo.factoids.service;

import java.util.Random;

import junit.framework.Assert;
import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.mock.Mock;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;

/**
 * Test random fact
 * 
 * @author ppoissinger
 * 
 */
public class RandomFactServiceTest extends UnitilsJUnit4 {
    /**
     * Tested object
     * 
     */
    private RandomFactService service;

    /** Provider mock */
    Mock<FactProvider> factProvider;

    /** Provider for random goodness */
    Mock<Random> random;

    final int SIZE = 10;

    /**
     * inits
     */
    @Before
    @SneakyThrows
    public final void init() {
        // Setup mocks: Lock random
        for (int i = 0; i < SIZE; ++i) {
            random.returns(i).nextInt(i + 1);
            factProvider.returns(new Fact(String.format("%d", i))).getFact(i);
        }
        factProvider.returns(SIZE).size();

        // Create service with our mock
        service = new RandomFactService(factProvider.getMock(),
                random.getMock());
    }


    /**
     * Test method for
     * {@link be.ordina.utdemo.factoids.service.RandomFactService#getAFact()}.
     * Make sure call to random follow the complete list/no repeat
     */
    @Test
    public final void testGetAFact() {
        for (int i = SIZE - 1; i < 0; i--) {
            Fact expected = new Fact(String.format("%d", i));
            Assert.assertEquals(expected, service.getAFact());
        }
    }

}
