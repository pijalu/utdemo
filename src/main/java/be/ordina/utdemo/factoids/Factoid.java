/**
 * 
 */
package be.ordina.utdemo.factoids;

import be.ordina.utdemo.factoids.configuration.FactoidConfiguration;
import be.ordina.utdemo.factoids.configuration.FileFactProviderWithRandomFactsConfiguration;
import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.service.FactService;
import lombok.SneakyThrows;

/**
 * Simple fact service client.
 * 
 * @author ppoissinger
 * 
 */
public class Factoid {

    private static FactoidConfiguration factoidConfiguration = new FileFactProviderWithRandomFactsConfiguration();

    /**
     * Retrieve a number of fact
     * 
     * @param nbFacts
     *            the number of facts to retrieve
     */
    private static void getFacts(final int nbFacts) {
        FactService service = factoidConfiguration.getService();
        for (int i = 0; i < nbFacts; i++) {
            Fact aFact = service.getAFact();
            if (aFact != null) {
                System.out.println(aFact.getContent());
                continue;
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
        if (args != null && args.length == 1) {
            defaultNumber = Integer.parseInt(args[0]);
        }
        getFacts(defaultNumber);
    }
}
