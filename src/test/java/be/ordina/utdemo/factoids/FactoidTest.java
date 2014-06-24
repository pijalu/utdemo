package be.ordina.utdemo.factoids;

import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;

import be.ordina.utdemo.factoids.provider.FactProvider;
import be.ordina.utdemo.factoids.provider.FileFactProvider;

/**
 * Test main fact
 * 
 * @author ppoissinger
 * 
 */
public class FactoidTest {
	/**
	 * Expected content in testfacts.txt UT file
	 */
	final String expectedFacts[] = { "fact0", "fact1", "fact2" };

	/**
	 * Tested object.
	 */
	private Factoid f;

	/**
	 * Inits.
	 */
	@Before
	@SneakyThrows
	public final void init() {
		// Build a file provider, based on a known file
		FactProvider provider = new FileFactProvider().loadStream(this.getClass().getResourceAsStream("/testfacts.txt"));
		f = new Factoid(provider);
		// FIXME: Use mock to isolate the test
	}

	/**
	 * @throws Exception
	 */
	@Test
	public final void testGetFacts() {
		// Happy test: We only can ensure it does not exception on us...
		f.displayFacts();
		// FIXME: Use mock to ensure call is correct
	}

	@Test
	public void testMain() throws Exception {
		// Happy test: We can really test this... or can we ?
		Factoid.main(null);
		// FIXME: Use partial mock ?
	}

}
