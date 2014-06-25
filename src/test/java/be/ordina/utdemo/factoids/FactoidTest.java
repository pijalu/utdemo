package be.ordina.utdemo.factoids;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import lombok.SneakyThrows;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;

/**
 * Test main fact
 * 
 * @author ppoissinger
 * 
 */
public class FactoidTest extends EasyMockSupport {

    /**
     * Tested object.
     */
    private Factoid f;

    /** Mock */
    private FactProvider provider;

    /**
     * Inits.
     */
    @Before
    @SneakyThrows
    public final void init() {
        provider = createMock(FactProvider.class);
        f = new Factoid(provider);
    }

    /**
     * @throws Exception
     */
    @Test
    public final void testGetFacts() {
        final int TIMES=3;
        expect(provider.size()).andReturn(1).times(TIMES * TIMES);
        expect(provider.getFact(0)).andReturn(new Fact("fact!")).times(
                TIMES * TIMES);
        replay(provider);

        for (int i = 0; i < TIMES; ++i) {
            f.getFacts(TIMES);
        }

        verify(provider);
    }

    @Test
    public void testMain() throws Exception {
        expect(provider.size()).andReturn(1);
        expect(provider.getFact(0)).andReturn(new Fact("fact!"));
        replay(provider);

        Factoid.factProvider = provider;
        Factoid.main(null);

        verify(provider);
    }

}
