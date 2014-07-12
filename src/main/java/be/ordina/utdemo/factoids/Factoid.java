/**
 * 
 */
package be.ordina.utdemo.factoids;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;
import be.ordina.utdemo.factoids.provider.FileFactProviderSupplier;
import be.ordina.utdemo.factoids.service.FactService;
import be.ordina.utdemo.factoids.service.RandomFactService;
import lombok.SneakyThrows;

import java.util.Random;

/**
 * Simple fact service client.
 * 
 * @author ppoissinger
 * 
 */
public class Factoid {
    private static String defaultFileLocation = "/chuck.txt";
    /**
     * Default provider
     *
     */
    private static FactProvider defaultProvider = new FileFactProviderSupplier(defaultFileLocation, Factoid.class).get();


    /**
     * Fact service.
     * 
     */
    private static FactService service = new RandomFactService(defaultProvider, new Random());

    /**
     * Retrieve a number of fact
     * 
     * @param nbFacts
     *            the number of facts to retrieve
     */
    private static void getFacts(final int nbFacts) {
        for (int i = 0; i < nbFacts; ++i) {
            Fact aFact = service.getAFact();
            if(aFact != null) {
                System.out.println(aFact.getContent());
            }
            System.out.println("No facts found!!");
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
        getFacts(defaultNumber);
    }
}
