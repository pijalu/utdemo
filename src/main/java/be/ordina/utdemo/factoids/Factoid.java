/**
 * 
 */
package be.ordina.utdemo.factoids;

import java.io.IOException;
import java.util.Random;

import lombok.SneakyThrows;
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
     * Default provider
     * 
     */
    private static FactProvider defaultProvider;
    static {
        try {
            defaultProvider = new FileFactProvider().loadStream(Factoid.class
                    .getResourceAsStream("/chuck.txt"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load facts !", e);
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
        int defaultNumber = 1;
        new Factoid(defaultProvider).getFacts(defaultNumber);
    }
}
