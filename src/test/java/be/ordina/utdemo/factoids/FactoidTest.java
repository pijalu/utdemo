package be.ordina.utdemo.factoids;

import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.inject.annotation.InjectIntoStaticByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;

/**
 * Test main fact
 * 
 * @author ppoissinger
 * 
 */
public class FactoidTest extends UnitilsJUnit4 {
    /**
     * Tested object.
     */
    @TestedObject
    private Factoid f;

    @InjectIntoStaticByType(target = Factoid.class)
    private Mock<FactProvider> provider;

    /**
     * Inits.
     */
    @Before
    @SneakyThrows
    public final void init() {
        provider.returns(1).size();
        provider.returns(new Fact("fact!")).getFact(0);

        f = new Factoid(provider.getMock());
    }

    /**
     * @throws Exception
     */
    @Test
    public final void testGetFacts() {
        for (int i = 0; i < 3; ++i) {
            f.getFacts(i);
            for (int j = 0; j < i; ++j) {
                provider.assertInvokedInSequence().getFact(0);
            }
        }
    }

    @Test
    public void testMain() throws Exception {
        Factoid.main(null);
        provider.assertInvokedInSequence().getFact(0);
    }

}
