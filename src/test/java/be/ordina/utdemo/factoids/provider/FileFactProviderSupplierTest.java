package be.ordina.utdemo.factoids.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.easymock.annotation.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import java.io.BufferedReader;
import java.io.IOException;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.unitils.easymock.EasyMockUnitils.replay;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SuppressWarnings("unused")
public class FileFactProviderSupplierTest {

    @TestedObject
    private FileFactProviderSupplier fileFactProviderSupplier = new FileFactProviderSupplier("/chuck.txt", FileFactProviderSupplier.class);
    @Mock
    @InjectIntoByType
    private FileFactProvider fileFactProvider;

    @Test
    public void get_correct_fileLocation_and_lookupStartPoint_returns_a_correct_FileFactProvider() throws Exception {
        assertThat(new FileFactProviderSupplier("/chuck.txt", FileFactProviderSupplier.class).get())
                .isNotNull()
                .isInstanceOf(FileFactProvider.class);
    }

    @Test(expected = RuntimeException.class)
    public void get_IOException_in_loadStream_throws_a_RuntimeException() throws Exception {
        expect(fileFactProvider.loadStream(anyObject(BufferedReader.class))).andStubThrow(new IOException());
        replay();
        fileFactProviderSupplier.get();
    }

    @Test(expected = RuntimeException.class)
    public void get_incorrect_fileLocation_and_lookupStartPoint_throws_a_RuntimeException() throws Exception {
        new FileFactProviderSupplier("/wrong.txt", FileFactProviderSupplier.class).get();
    }
}