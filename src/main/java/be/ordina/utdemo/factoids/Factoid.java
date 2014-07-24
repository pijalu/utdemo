/**
 *
 */
package be.ordina.utdemo.factoids;

import java.io.IOException;
import java.util.Random;

import be.ordina.utdemo.factoids.provider.FactProvider;
import be.ordina.utdemo.factoids.provider.FileFactProvider;
import be.ordina.utdemo.factoids.service.FactService;
import be.ordina.utdemo.factoids.service.RandomFactService;

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
    private final FactService service;

    /**
     * Build our factoids.
     *
     * @param provider
     *            provider to use
     */
    public Factoid(final FactProvider provider) {
        service = new RandomFactService(provider, new Random());
    }

    /**
     * Retrieve a number of fact
     *
     * @param nbFacts
     *            the number of facts to retrieve
     */
    protected final void getFacts(final int nbFacts) {
        for (int i = 0; i < nbFacts; ++i) {
            System.out.println(service.getAFact().getContent());
        }
    }

    /**
     * Main !
     *
     * @param args
     *            arguments for the program
     * @throws IOException ioException
     */
    public static void main(final String[] args) throws IOException {
        int defaultNumber = 1;
        FactProvider provider = new FileFactProvider().loadStream(Factoid.class
                .getResourceAsStream("/chuck.txt"));
        new Factoid(provider).getFacts(defaultNumber);
    }
}
