package be.ordina.utdemo.factoids.service;

import be.ordina.utdemo.factoids.provider.FileFactProviderSupplier;
import com.google.common.base.Supplier;

import java.util.Random;

public class RandomFactServiceSupplier implements Supplier<RandomFactService>{
    private final Random random;
    private final FileFactProviderSupplier fileFactProviderSupplier;
    public RandomFactServiceSupplier(Random random, FileFactProviderSupplier fileFactProviderSupplier) {
        this.random = random;
        this.fileFactProviderSupplier = fileFactProviderSupplier;
    }

    @Override
    public RandomFactService get() {
        return new RandomFactService(fileFactProviderSupplier.get(), random);
    }
}
