package com.sage.loanapound.ws.soap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sage.loanapound.constant.ErrorConstants;
import com.sage.loanapound.entity.Customer;
import com.sage.loanapound.ws.configuration.SoapClientConfig;

import wsdl.classes.ScoreResponse;

public class TestProviderSoap {
	
	private ProviderSoap providerSoap;
	
	AnnotationConfigApplicationContext ctx; 
	
	@Before
	//Create the context
	public void createContext(){
		ctx = new AnnotationConfigApplicationContext();
		ctx.register(SoapClientConfig.class);
		ctx.refresh();
		providerSoap = ctx.getBean(ProviderSoap.class);
	}

	@Test
	public void testGetScore() throws Exception {
		Customer customer = new Customer();
		customer.setId("ID");
		customer.setName("Test");
		customer.setLastname("Test");
	
		ScoreResponse response = providerSoap.getScore(customer);
		
		assertNotNull(response);
		assertTrue(response.getScore() > 0);
		assertTrue(response.getScore() <= 100);
	}
	
	@Test
	public void testGetScoreCustomerNull() {

		try {
			providerSoap.getScore(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), ErrorConstants.ERROR_CUSTOMER_NULL);
		}
		
	}
	
	@After
	public void close(){
		ctx.close();
	}

}
