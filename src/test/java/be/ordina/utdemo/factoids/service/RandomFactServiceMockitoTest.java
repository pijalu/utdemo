package be.ordina.utdemo.factoids.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;

@RunWith(MockitoJUnitRunner.class)
public class RandomFactServiceMockitoTest {

	
	@Mock
	private FactProvider factProviderMock;
	
	@Mock
	private Random randomMock;
	
	@Mock
	private List<Integer> indexesMock;
	
	@InjectMocks
	private RandomFactService objectUnderTest;
	
	private static final int SIZE = 10;
	
	@Before
	public void init() {		
		objectUnderTest.setIndexes(indexesMock);
	}
	
	@Test
	public void getAPermutationIndex() {
		int sizeIndex = 2;
		Integer randomInteger  = new Integer(5);
		//when
		when(indexesMock.isEmpty()).thenReturn(false);	
		when(indexesMock.size()).thenReturn(sizeIndex);
		when(randomMock.nextInt(sizeIndex)).thenReturn(randomInteger);
		when(indexesMock.get(randomInteger.intValue())).thenReturn(SIZE);
		
		//test
		int indexToReturn = objectUnderTest.getAPermutationIndex();
		
		//verify		
		verify(factProviderMock, times(0)).size();		
		verify(indexesMock).get(anyInt());
		
		assertThat(indexToReturn, equalTo(SIZE));
		
	}
	
	/**
	 * Tests with an empyt list
	 */
	@Test
	public void getAFact() {
		Fact fact = new Fact("TestContent 4 Mockito");	
		//when
		when(factProviderMock.getFact(anyInt())).thenReturn(fact); 
		
		Fact factReturned = objectUnderTest.getAFact();
		
		assertThat(factReturned, sameInstance(fact));
		
	}
}
