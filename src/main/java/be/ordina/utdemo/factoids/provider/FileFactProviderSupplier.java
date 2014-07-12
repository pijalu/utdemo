package be.ordina.utdemo.factoids.provider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public class FileFactProviderSupplier implements Supplier<FileFactProvider> {

    private final String fileLocation;
    private final Class<?> lookupStartPoint;
    private FileFactProvider fileFactProvider = new FileFactProvider();

    public FileFactProviderSupplier(String fileLocation, Class<?> lookupStartPoint) {
        this.fileLocation = fileLocation;
        this.lookupStartPoint = lookupStartPoint;
    }

    @Override
    public FileFactProvider get() {
            try {
                Path path = new File(lookupStartPoint.getResource(fileLocation).getFile()).toPath();
                return fileFactProvider.loadStream(Files.newBufferedReader(path));
            } catch (IOException e) {
                throw new RuntimeException("Failed to load facts !", e);
            }
    }
}
