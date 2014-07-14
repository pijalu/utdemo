package be.ordina.utdemo.factoids.configuration;

import be.ordina.utdemo.factoids.service.RandomFactService;
import be.ordina.utdemo.factoids.service.RandomFactServiceSupplier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.easymock.annotation.Mock;
import org.unitils.easymock.annotation.RegularMock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.unitils.easymock.EasyMockUnitils.replay;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SuppressWarnings("unused")
public class FileFactProviderWithRandomFactsConfigurationTest {
    @TestedObject
    private FileFactProviderWithRandomFactsConfiguration fileFactProviderWithRandomFactsConfiguration;
    @RegularMock
    @InjectIntoByType
    private RandomFactServiceSupplier randomFactServiceSupplier;
    @Mock
    private RandomFactService randomFactService;

    @Test
    public void getService() throws Exception {
        expect(randomFactServiceSupplier.get()).andReturn(randomFactService);
        replay();
        assertThat(fileFactProviderWithRandomFactsConfiguration.getService()).isSameAs(randomFactService);
    }
}