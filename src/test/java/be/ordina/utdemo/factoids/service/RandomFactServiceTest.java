/**
 * 
 */
package be.ordina.utdemo.factoids.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;
import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;

import be.ordina.utdemo.factoids.provider.FactProvider;
import be.ordina.utdemo.factoids.provider.FileFactProvider;

/**
 * Test random fact
 * 
 * @author ppoissinger
 * 
 */
public class RandomFactServiceTest {
	/**
	 * Expected content for /testfacts.txt
	 * 
	 */
	private final String expected[] = { "fact0", "fact1", "fact2" };

	/**
	 * Tested object
	 * 
	 */
	private RandomFactService service;

	/**
	 * inits FIXME: Use mock to isolate
	 */
	@Before
	@SneakyThrows
	public final void init() {
		// Build a file provider, based on a known file
		FactProvider provider = new FileFactProvider().loadStream(this
				.getClass().getResourceAsStream("/testfacts.txt"));
		// Random source
		// FIXME: Use mock to remove randomness
		Random random = new Random();
		// Build service
		service = new RandomFactService(provider, random);
	}

	/**
	 * Build a mutable list from expected facts
	 * 
	 * @return a mutable list FIXME: Mock probably don't need so complex code...
	 */
	private List<String> getExpectedFacts() {
		List<String> list = new ArrayList<>();
		for (String fact : expected) {
			list.add(fact);
		}
		return list;
	}

	/**
	 * Test method for
	 * {@link be.ordina.utdemo.factoids.service.RandomFactService#getAFact()}.
	 * We expect random order, but all item should appear once per run ! FIXME:
	 * That's madness, mock can help !
	 */
	@Test
	public final void testGetAFact() {
		List<String> expectedList = getExpectedFacts();

		while (!expectedList.isEmpty()) {
			String randomFact = service.getAFact().getContent();
			Assert.assertTrue(expectedList.contains(randomFact));
			expectedList.remove(randomFact);
		}
	}

}
