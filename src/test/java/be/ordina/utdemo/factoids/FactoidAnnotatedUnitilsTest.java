package be.ordina.utdemo.factoids;

import be.ordina.utdemo.factoids.configuration.FactoidConfiguration;
import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.service.FactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.easymock.annotation.RegularMock;
import org.unitils.inject.annotation.InjectIntoStaticByType;

import static org.easymock.EasyMock.expect;
import static org.unitils.easymock.EasyMockUnitils.replay;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SuppressWarnings("unused")
public class FactoidAnnotatedUnitilsTest {

    @RegularMock
    @InjectIntoStaticByType(target = Factoid.class)
    private FactoidConfiguration factoidConfiguration;
    @RegularMock
    private FactService factService;

    private Fact fact;
    

    @Before
    public void setUpFact() {
        fact = new Fact("content");
    }
    

    @Test
    public void main_when_no_fact_returned_only_a_call_to_sysout_is_performed() throws Exception {
        expect(factoidConfiguration.getService()).andReturn(factService);
        expect(factService.getAFact()).andReturn(null);
        replay();
        Factoid.main(null);
    }

    @Test
    public void main_should_call_getAFact_ones_on_factService() throws Exception {
        expect(factoidConfiguration.getService()).andReturn(factService);
        expect(factService.getAFact()).andReturn(fact);
        replay();
        Factoid.main(null);
    }

    @Test
    public void main_should_call_getAFact_number_of_argument_times_on_factService() throws Exception {
        expect(factoidConfiguration.getService()).andReturn(factService);
        for(int i = 0; i < 10; i++) {
            expect(factService.getAFact()).andReturn(fact);
        }
        replay();
        Factoid.main(new String[]{"10"});
    }
}