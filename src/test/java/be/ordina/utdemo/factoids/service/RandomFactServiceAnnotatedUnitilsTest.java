package be.ordina.utdemo.factoids.service;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.easymock.annotation.Mock;
import org.unitils.easymock.annotation.RegularMock;

import java.util.Random;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.unitils.easymock.EasyMockUnitils.replay;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SuppressWarnings("unused")
public class RandomFactServiceAnnotatedUnitilsTest {

    private RandomFactService randomFactService;
    @RegularMock
    private FactProvider factProvider;
    @RegularMock
    private Random random;
    @Mock
    private Fact fact;

    @Before
    public void setUpRandomFactServiceTestObject() {
        randomFactService = new RandomFactService(factProvider, random);
    }

    @Before
    public void setUpDefaultExpectancyForFactprovider() {
        expect(factProvider.size()).andStubReturn(10);
    }

    @Test
    public void spike_API_test_for_random_what_are_accepted_values_for_random() {
        Random random = new Random();
        int valueToTestWith = 1;
        try {
            random.nextInt(valueToTestWith);
        } catch (IllegalArgumentException e) {
            fail(String.format("%s was not a correct value for random. Exception was: %s", valueToTestWith, e.getMessage()));
        }
    }

    @Test
    public void spike_API_test_for_random_is_the_return_value_for_nextInt_1_always_zero() {
        Random random = new Random();
        for(int i = 0; i < 50; i++) {
            assertThat(random.nextInt(1)).isEqualTo(0);
        }
    }

    @Test
    public void spike_API_test_for_random_is_the_return_value_for_nextInt_10_always_smaller_than_10() {
        Random random = new Random();
        for(int i = 0; i < 1000; i++) {
            assertThat(random.nextInt(10)).isLessThan(10);
        }
    }

    @Test
    public void getAFact_when_called_with_size_returned_by_factProvider_zero_no_exception_should_be_thrown_null_should_be_returned() throws Exception {
        expect(factProvider.size()).andReturn(0);
        replay();
        assertThat(randomFactService.getAFact()).isNull();
    }

    @Test
        public void getAFact_when_called_with_size_returned_by_factProvider_1_nextInt_on_random_is_called_and_the_returned_value_is_used_to_retrieve_the_fact_on_this_index() throws Exception {
        expect(factProvider.size()).andReturn(1);
        expect(random.nextInt(1)).andReturn(0);
        expect(factProvider.getFact(0)).andReturn(fact);
        replay();
        assertThat(randomFactService.getAFact()).isSameAs(fact);
    }

    @Test
        public void getAFact_when_called_with_size_returned_by_factProvider_10_nextInt_on_random_is_called_and_the_returned_value_is_used_to_retrieve_the_fact_on_this_index() throws Exception {
        expect(random.nextInt(10)).andReturn(4);
        expect(factProvider.getFact(4)).andReturn(fact);
        replay();
        assertThat(randomFactService.getAFact()).isSameAs(fact);
    }

    @Test
        public void getAFact_called_twice_first_index_is_removed_second_time_netxInt_is_called_with_9_instead_of_with_10_if_it_returns_8_index_9_is_returned() throws Exception {
        //first
        expect(random.nextInt(10)).andReturn(4);
        expect(factProvider.getFact(4)).andReturn(fact);
        //second
        expect(random.nextInt(9)).andReturn(8);
        expect(factProvider.getFact(9)).andReturn(fact);

        replay();
        assertThat(randomFactService.getAFact()).isSameAs(fact);
        assertThat(randomFactService.getAFact()).isSameAs(fact);
    }

}