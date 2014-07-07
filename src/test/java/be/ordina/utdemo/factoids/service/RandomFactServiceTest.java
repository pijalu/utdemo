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
        // Return a predictable order: last to first
        for (int factIndex = 0; factIndex < SIZE; ++factIndex) {
            int leftToSelect = factIndex + 1;
            random.returns(factIndex).nextInt(leftToSelect);
            String factText = String.format("%d", factIndex);
            factProvider.returns(new Fact(factText)).getFact(factIndex);
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
        for (int runIndex = SIZE - 1; runIndex < 0; runIndex--) {
            String expectFactText = String.format("%d", runIndex);
            Fact expectedFact = new Fact(expectFactText);
            Assert.assertEquals(expectedFact, service.getAFact());
        }
    }

}
