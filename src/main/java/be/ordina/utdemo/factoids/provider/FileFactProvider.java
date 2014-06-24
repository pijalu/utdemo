/**
 * 
 */
package be.ordina.utdemo.factoids.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import be.ordina.utdemo.factoids.model.Fact;

/**
 * Provide facts, loaded from a file
 * 
 * @author ppoissinger
 * 
 */
public class FileFactProvider implements FactProvider {

	/**
	 * Fact (memory) storage.
	 */
	private final List<Fact> facts = new ArrayList<>();

	/**
	 * Load a series of fact from a stream
	 * 
	 * @param stream
	 *            stream to load - should be text!
	 * @return FileFactProvider
	 * @throws IOException
	 *             Error when accessing file
	 */
	public final FileFactProvider loadStream(final InputStream stream) throws IOException {
		if (stream == null) {
			throw new IllegalArgumentException("NULL stream");
		}

		facts.clear();

		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String str = br.readLine();
		while (str != null) {
			facts.add(new Fact(str));
			str = br.readLine();
		}
		return this;
	}

	@Override
	public final int size() {
		return facts.size();
	}

	@Override
	public final Fact getFact(final int index) {
		return facts.get(index);
	}

	@Override
	public void addFact(Fact fact) {
		facts.add(fact);
	}

}
