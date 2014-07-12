package be.ordina.utdemo.factoids.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public class FileFactProviderSupplierTest {

    @Test
    public void get_correct_fileLocation_and_lookupStartPoint_returns_a_correct_FileFactProvider() throws Exception {
        assertThat(new FileFactProviderSupplier("/chuck.txt", FileFactProviderSupplier.class).get())
                .isNotNull()
                .isInstanceOf(FileFactProvider.class);
    }

    @Test(expected = RuntimeException.class)
    public void get_incorrect_fileLocation_and_lookupStartPoint_throws_a_RuntimeException() throws Exception {
        new FileFactProviderSupplier("/wrong.txt", FileFactProviderSupplier.class).get();
    }
}