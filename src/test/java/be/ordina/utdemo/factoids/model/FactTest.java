package be.ordina.utdemo.factoids.model;

import org.junit.Before;
import org.junit.Test;

import com.google.common.testing.EqualsTester;

/**
 * Basic UT for {@link Fact}
 * 
 * @author ppoissinger
 * 
 */
public class FactTest {
    // Test dataset
    private Fact a;
    private Fact aBis;
    private Fact b;
    private Fact bBis;
    private Fact nullFact;

    /**
     * Create our test data set
     * 
     */
    @Before
    public final void inits() {
        a = new Fact("a");
        // Make sure data is another instance
        aBis = new Fact(new String(a.getContent()));

        b = new Fact("b");
        // Make sure data is another instance
        bBis = new Fact(new String(b.getContent()));

        // Null fact
        nullFact = new Fact(null);
    }

    /**
     * Make sure equals/hash works (use Guava test utils)
     * 
     */
    @Test
    public final void testEquals() {
        new EqualsTester().addEqualityGroup(a, aBis).addEqualityGroup(b, bBis)
                .addEqualityGroup(nullFact).testEquals();
    }
}
