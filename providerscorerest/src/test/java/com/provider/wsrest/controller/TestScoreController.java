package com.provider.wsrest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.provider.wsrest.constants.ErrorConstants;
import com.provider.wsrest.model.Customer;
import com.provider.wsrest.model.Result;
import com.provider.wsrest.service.ScoreService;

public class TestScoreController {
	
	@InjectMocks
	ScoreController scoreController = new ScoreController();
	
	@Mock
	ScoreService scoreService;
	
	@Before
	public void setupMock() {
		initMocks(this);
	}

	@Test
	public void testGetScore() throws Exception {
		
		Customer customer = new Customer();
		customer.setId("test");
		customer.setName("test");
		customer.setLastname("test");

		Result res = scoreController.getScore(customer);
		
		assertNotNull(res);
	}
	
	@Test
	public void testGetScoreCustomerNull() {	
		try {
			scoreController.getScore(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), ErrorConstants.ERROR_CUSTOMER_NULL);
		}
	}

}
