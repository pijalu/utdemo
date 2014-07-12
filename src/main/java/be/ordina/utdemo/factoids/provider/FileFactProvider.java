/**
 * 
 */
package be.ordina.utdemo.factoids.provider;

import be.ordina.utdemo.factoids.model.Fact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Provide facts, loaded from a file
 * 
 * @author ppoissinger
 * 
 */
public class FileFactProvider implements FactProvider {
	/** Fact (memory) storage.
	 */
	private final List<Fact> facts = new ArrayList<>();

	/**
	 * Load a series of fact from a stream
	 * 
	 * @param stream stream to load - should be text!
	 * @return FileFactProvider
	 * @throws IOException
	 *             Error when accessing file
	 */
	public final FileFactProvider loadStream(final InputStream stream)
			throws IOException {
		if (stream == null) {
			throw new IllegalArgumentException("NULL stream");
		}

		facts.clear();

		BufferedReader br = new BufferedReader(
				new InputStreamReader(stream));
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
        if (facts.isEmpty()) {
            return null;
        }
        return facts.get(index);
    }


    public FileFactProvider loadStream(BufferedReader bufferedReader) throws IOException {
        checkArgument(bufferedReader != null, "BufferedReader must not be null when calling loadStream.");
        facts.clear();
        try (BufferedReader closeableBufferedReader = bufferedReader) {
            if (closeableBufferedReader == null) {
                return this;
            }
            String fact = closeableBufferedReader.readLine();
            while (fact != null) {
                facts.add(new Fact(fact));
                fact = closeableBufferedReader.readLine();
            }
        }
        return this;
    }
}
