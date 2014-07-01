package be.ordina.utdemo.factoids;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import lombok.SneakyThrows;

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
public class FactoidTest {
    /**
     * Tested object.
     */
    private Factoid f;

    /** Mock */
    FactProvider provider;

    /**
     * Inits.
     */
    @Before
    @SneakyThrows
    public final void init() {
        // Build a file provider, based on a known file
        provider = mock(FactProvider.class);
        when(provider.size()).thenReturn(1);
        when(provider.getFact(anyInt())).thenReturn(new Fact("Fact!"));
        // Use the mock !
        f = new Factoid(provider);
    }

    /**
     * @throws Exception
     */
    @Test
    public final void testGetFacts() {
        for (int i = 0; i < 3; ++i) {
            f.getFacts(i);
        }
        verify(provider, atLeast(3)).size();
        verify(provider, atLeast(3)).getFact(0);
    }

    @Test
    public void testMain() throws Exception {
        Factoid.provider=provider;
        Factoid.main(null);
        verify(provider, atLeast(1)).size();
        verify(provider, atLeast(1)).getFact(0);
    }

}
