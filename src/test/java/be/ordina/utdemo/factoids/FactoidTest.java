package be.ordina.utdemo.factoids;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.service.FactService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.inject.annotation.InjectIntoStaticByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

/**
 * Test main fact
 * 
 * @author ppoissinger
 * 
 */
@SuppressWarnings("unused")
public class FactoidTest extends UnitilsJUnit4 {
    /**
     * Tested object.
     */
    @TestedObject
    private Factoid factoid;

    @InjectIntoStaticByType(target = Factoid.class)
    private Mock<FactService> serviceMock;

    /**
     * Inits.
     */
    @Before
    @SneakyThrows
    public final void init() {
        serviceMock.returns(new Fact("fact!")).getAFact();
    }

    @Test
    public void testMain() throws Exception {
        Factoid.main(null);
        serviceMock.assertInvokedInSequence().getAFact();
    }

}
