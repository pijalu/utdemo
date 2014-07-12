package be.ordina.utdemo.factoids.provider;

import be.ordina.utdemo.factoids.model.Fact;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.easymock.annotation.Mock;
import org.unitils.easymock.annotation.RegularMock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import java.io.BufferedReader;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.unitils.easymock.EasyMockUnitils.replay;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SuppressWarnings("unused")
public class FileFactProviderAnnotatedUnitilsTest {

    public static final int EXPECTED_SIZE = 20;
    public static final String FACT = "fact";
    @TestedObject
    private FileFactProvider fileFactProvider;
    @InjectIntoByType
    private List<Fact> facts = Lists.newArrayList();
    @RegularMock
    private BufferedReader bufferedReader;
    @Mock
    private Fact fact1;
    @Mock
    private Fact fact2;
    private List<Fact> expectedFacts;

    @Before
    public void setUpListOfInjectedFacts() {
        facts.add(fact1);
        facts.add(fact2);
    }

    @Before
    public void setUpListOfExpectedFacts() {
        expectedFacts = Lists.newArrayList();
        for(int i = 1 ; i <= EXPECTED_SIZE; i++) {
            expectedFacts.add(new Fact(FACT + i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadStream_throws_IllegalArgumentException_when_given_stream_is_null() throws Exception {
        fileFactProvider.loadStream((BufferedReader)null);
    }
    
    @Test
    public void loadStream_when_one_line_is_read_from_BufferedReader_line_is_used_to_create_fact_and_is_added_to_list_of_facts_after_list_is_cleared() throws Exception {
        expect(bufferedReader.readLine()).andReturn(FACT);
        expect(bufferedReader.readLine()).andReturn(null);
        bufferedReader.close();
        replay();
        assertSame(fileFactProvider, fileFactProvider.loadStream(bufferedReader));
        assertReflectionEquals(Lists.newArrayList(new Fact(FACT)), facts);
    }

    @Test
    public void loadStream_when_several_lines_are_read_from_BufferedReader_lines_are_used_to_create_facts_and_is_added_to_list_of_facts_after_list_is_cleared() throws Exception {
        for (int i = 1; i <= EXPECTED_SIZE; i++) {
            expect(bufferedReader.readLine()).andReturn(FACT + i);
        }
        expect(bufferedReader.readLine()).andReturn(null);
        bufferedReader.close();
        replay();
        assertSame(fileFactProvider, fileFactProvider.loadStream(bufferedReader));
        assertReflectionEquals(expectedFacts, facts);
    }

    @Test
    public void size_returns_size_of_facts_list_when_list_is_not_empty() throws Exception {
        assertThat(fileFactProvider.size(), is(2));
    }

    @Test
    public void size_returns_0_when_list_is_empty() throws Exception {
        assertThat(new FileFactProvider().size(), is(0));
    }

    @Test
    public void getFact_returns_fact_that_is_positioned_on_given_index() throws Exception {
        assertThat(fileFactProvider.getFact(1), is(fact2));
    }

    @Test
    public void getFact_returns_null_when_the_list_is_empty() throws Exception {
        assertThat(new FileFactProvider().getFact(1), nullValue());
    }
}