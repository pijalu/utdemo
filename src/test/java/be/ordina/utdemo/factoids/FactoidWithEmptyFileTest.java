package be.ordina.utdemo.factoids;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class FactoidWithEmptyFileTest {

    @Before
    public void replaceSourceFileWithEmptyFile() throws IOException {
        File file = new File(Factoid.class.getResource("/chuck.txt").getFile());
        boolean success = file.renameTo(new File(file.getParent(), "chuckOriginal.txt"));
        ifNoSuccessThrowIOException(success);
        Files.createFile(Paths.get(file.getParent(), "chuck.txt"));
    }

    private void ifNoSuccessThrowIOException(boolean success) throws IOException {
        if (!success) {
            throw new IOException("renaming file not succeeded!");
        }
    }

    @Test
    public void main_should_not_throw_any_exception_when_called_with_an_empty_list() {
        Factoid.main(null);
    }


    @After
    public void replaceEmptyFileWithSourceFile() throws IOException {
        File emptyFile = new File(Factoid.class.getResource("/chuck.txt").getFile());
        File originalFile = new File(Factoid.class.getResource("/chuckOriginal.txt").getFile());
        Files.delete(emptyFile.toPath());
        boolean success = originalFile.renameTo(new File(originalFile.getParent(), "chuck.txt"));
        ifNoSuccessThrowIOException(success);
    }

}
