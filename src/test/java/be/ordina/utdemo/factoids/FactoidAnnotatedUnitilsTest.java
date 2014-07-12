package be.ordina.utdemo.factoids;

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
    private FactService factService;
    @RegularMock
    private Fact fact;
    

    @Before
    public void setUpDefaultExpectationsOfFact() {
        expect(fact.getContent()).andStubReturn("content");
    }
    

    @Test
    public void main_should_call_getAFact_ones_on_factService() throws Exception {
        expect(factService.getAFact()).andReturn(fact);
        replay();
        Factoid.main(null);
    }
}