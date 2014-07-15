package be.ordina.utdemo.factoids.provider;

import java.io.IOException;

import com.google.common.base.Supplier;

public class FileFactProviderSupplier implements Supplier<FileFactProvider> {

    private final String fileLocation;
    private final Class<?> lookupStartPoint;
    private final FileFactProvider fileFactProvider = new FileFactProvider();

    public FileFactProviderSupplier(final String fileLocation, final Class<?> lookupStartPoint) {
        this.fileLocation = fileLocation;
        this.lookupStartPoint = lookupStartPoint;
    }

    @Override
    public FileFactProvider get() {
        try {
            return fileFactProvider.loadStream(lookupStartPoint.getResourceAsStream(fileLocation));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load facts !", e);
        }
    }
}
