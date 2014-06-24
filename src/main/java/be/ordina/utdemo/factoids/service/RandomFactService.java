package be.ordina.utdemo.factoids.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;

/**
 * Return pseudo-random facts from a fact provider (All facts will be returned)
 * 
 * @author ppoissinger
 * 
 */
public class RandomFactService implements FactService {

    /**
     * Permutation index
     * 
     */
    private final List<Integer> indexes = new ArrayList<>();

    /**
     * Fact provider
     * 
     */
    private final FactProvider provider;

    /**
     * Random generator
     * 
     */
    private final Random random;

    /**
     * Random fact provider
     * 
     * @param provider
     *            provider for the fact
     * @param random
     *            random source
     */
    public RandomFactService(final FactProvider provider, final Random random) {
        this.provider = provider;
        this.random = random;
    }

    /**
     * Create permutation indexes
     * 
     */
    protected final void createPermutationIndexes() {
        // populate with valid indexes
        for (int i = 0; i < provider.size(); ++i) {
            indexes.add(i);
        }
    }

    /**
     * Retrieve a pseudo random index
     * 
     * @return a random index
     */
    protected final int getAPermutationIndex() {
        // Create permutation index
        if (indexes.isEmpty()) {
            createPermutationIndexes();
        }
        int permuationIndex = random.nextInt(indexes.size());
        // select
        int indexToReturn = indexes.get(permuationIndex);
        // Remove from our permutation to ensure we go thru the complete set
        // without dups
        indexes.remove(permuationIndex);
        // return the index
        return indexToReturn;
    }

    @Override
    public final Fact getAFact() {
        return provider.getFact(getAPermutationIndex());
    }
}
