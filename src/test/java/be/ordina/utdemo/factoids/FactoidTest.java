package be.ordina.utdemo.factoids;

import lombok.SneakyThrows;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.VerificationsInOrder;
import mockit.integration.junit4.JMockit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;

/**
 * Test main fact
 * 
 * @author ppoissinger
 * 
 */
@RunWith(JMockit.class)
public class FactoidTest {
    /**
     * Tested object.
     */
    private Factoid f;

    @Mocked
    FactProvider provider;

    /**
     * Inits.
     */
    @Before
    @SneakyThrows
    public final void init() {
        f = new Factoid(provider);
    }

    /**
     * @throws Exception
     */
    @Test
    public final void testGetFacts() {
        final int RUNCNT = 3;
        new Expectations() {
            {
                for (int i = 0; i < RUNCNT; ++i) {
                    provider.size();
                    result = 1;

                    provider.getFact(0);
                    result = new Fact("Fact!");
                }
            }
        };

        f.getFacts(RUNCNT);
    }

    @Test
    public void testMain() throws Exception {
        new NonStrictExpectations() {
            {
                provider.size();
                result = 1;

                provider.getFact(0);
                result = new Fact("Fact!");
            }
        };

        Factoid.provider = provider;
        Factoid.main(null);

        new VerificationsInOrder(1) {
            {
                provider.size();
                provider.getFact(0);
            }
        };
    }

}
