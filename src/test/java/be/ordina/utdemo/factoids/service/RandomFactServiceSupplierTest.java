package be.ordina.utdemo.factoids.service;

import be.ordina.utdemo.factoids.provider.FileFactProvider;
import be.ordina.utdemo.factoids.provider.FileFactProviderSupplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.easymock.annotation.Mock;

import java.util.Random;

import static org.easymock.EasyMock.expect;
import static org.unitils.easymock.EasyMockUnitils.replay;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SuppressWarnings("unused")
public class RandomFactServiceSupplierTest {

    @Mock
    private Random random;
    @Mock
    private FileFactProviderSupplier fileFactProviderSupplier;
    @Mock
    private FileFactProvider fileFactProvider;

    @Test
    public void get_returns_a_RandomFactService_with_injected_random_and_provider() throws Exception {
        expect(fileFactProviderSupplier.get()).andReturn(fileFactProvider);
        replay();
        assertReflectionEquals(new RandomFactService(fileFactProvider, random), new RandomFactServiceSupplier(random, fileFactProviderSupplier).get());
    }
}