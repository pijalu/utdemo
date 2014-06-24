/**
 * 
 */
package be.ordina.utdemo.factoids;

import lombok.SneakyThrows;
import be.ordina.utdemo.factoids.provider.DatabaseFactProvider;
import be.ordina.utdemo.factoids.provider.FactProvider;
import be.ordina.utdemo.factoids.provider.FileFactProvider;

/**
 * Simple fact service client.
 * 
 * @author ppoissinger
 * 
 */
public class Factoid {
	/**
	 * Fact service.
	 * 
	 */
	private final FactProvider factProvider;

	/**
	 * Build our factoids.
	 * 
	 * @param factProvider
	 *            provider to use
	 */
	public Factoid(final FactProvider factProvider) {
		this.factProvider = factProvider;
	}

	/**
	 * Displays all the facts in the factProvider.
	 */
	protected final void displayFacts() {
		for (int i = 1; i <= factProvider.size(); ++i) {
			System.out.println(factProvider.getFact(i).getContent());
		}
	}

	/**
	 * Main !
	 * 
	 * @param args
	 *            arguments for the program
	 */
	@SneakyThrows
	public static void main(final String[] args) {
		FactProvider databaseFactProvider = new DatabaseFactProvider();
		FactProvider fileFactProvider = new FileFactProvider().loadStream(Factoid.class.getResourceAsStream("/chuck.txt"));

		for (int i = 0; i < fileFactProvider.size(); i++) {
			databaseFactProvider.addFact(fileFactProvider.getFact(i));
		}

		new Factoid(databaseFactProvider).displayFacts();
	}
}
