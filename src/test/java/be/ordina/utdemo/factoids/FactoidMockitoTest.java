package be.ordina.utdemo.factoids;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jws.soap.InitParam;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.ordina.utdemo.factoids.model.Fact;
import be.ordina.utdemo.factoids.provider.FactProvider;
import be.ordina.utdemo.factoids.service.FactService;


@RunWith(MockitoJUnitRunner.class)
public class FactoidMockitoTest {

	@InjectMocks
	private Factoid objectUnderTest;
	
	@Mock
	private FactProvider factProvider;
	
	@Mock
	private FactService factServiceMock;
	
	
	private static final int SIZE = 3;
	
	
	
	@Test
	public void getFacts() {
		
		
		Fact fact1 = new Fact("Fact 1");
		Fact fact2 = new Fact("Fact 2");
		Fact fact3 = new Fact("Fact 3");
		//before
		when(factServiceMock.getAFact()).thenReturn(fact1).thenReturn(fact2).thenReturn(fact3);
		objectUnderTest.getFacts(SIZE);
		//verify
		verify(factServiceMock, times(3)).getAFact().getContent();
		
	}
}
