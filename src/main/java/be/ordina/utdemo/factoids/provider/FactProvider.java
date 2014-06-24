package be.ordina.utdemo.factoids.provider;

import be.ordina.utdemo.factoids.model.Fact;

/**
 * Fact provider: Provide fact to client
 * 
 * @author ppoissinger
 * 
 */
public interface FactProvider {
    /**
     * Number of facts available
     * 
     * @return number of facts available
     */
    int size();

    /**
     * Retrieve a fact
     * 
     * @param index
     *            index of the fact to retrieve
     * @return matching fact
     */
    Fact getFact(final int index);
}
