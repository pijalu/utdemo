package be.ordina.utdemo.factoids.configuration;

import be.ordina.utdemo.factoids.provider.FileFactProviderSupplier;
import be.ordina.utdemo.factoids.service.FactService;
import be.ordina.utdemo.factoids.service.RandomFactServiceSupplier;

import java.util.Random;

public class FileFactProviderWithRandomFactsConfiguration implements FactoidConfiguration {
    private FileFactProviderSupplier fileFactProviderSupplier;
    private RandomFactServiceSupplier randomFactServiceSupplier;

    public FileFactProviderWithRandomFactsConfiguration() {
        fileFactProviderSupplier = new FileFactProviderSupplier("/chuck.txt", this.getClass());
        randomFactServiceSupplier = new RandomFactServiceSupplier(new Random(), fileFactProviderSupplier);
    }

    @Override
    public FactService getService() {
        return randomFactServiceSupplier.get();
    }
}
