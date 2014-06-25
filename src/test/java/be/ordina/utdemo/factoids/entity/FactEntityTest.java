/**
 * 
 */
package be.ordina.utdemo.factoids.entity;

import org.junit.Before;
import org.junit.Test;

import com.google.common.testing.EqualsTester;

/**
 * @author Turbots
 *
 */
public class FactEntityTest {

	private FactEntity a;
	private FactEntity aBis;

	@Before
	public final void inits() {
		a = new FactEntity();
		a.setContent("DBUNIT KICKS ASS, JUST LIKE CHUCK NORRIS");
		aBis = new FactEntity();
		aBis.setContent(a.getContent());
	}

	/**
	 * Make sure equals/hash works (use Guava test utils)
	 * 
	 */
	@Test
	public final void testEquals() {
		new EqualsTester().addEqualityGroup(a, aBis).testEquals();
	}
}
