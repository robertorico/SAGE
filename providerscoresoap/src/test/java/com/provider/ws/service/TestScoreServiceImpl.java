package com.provider.ws.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.provider.ws.contansts.ErrorConstants;
import com.provider.ws.model.ScoreRequest;

public class TestScoreServiceImpl {
	
	@InjectMocks
	ScoreServiceImpl scoreService = new ScoreServiceImpl();

	
	@Before
	public void setupMock() {
		initMocks(this);
	}

	@Test
	public void testFindScore() throws Exception {
		ScoreRequest request = new ScoreRequest();
		request.setPassport("test");
		request.setName("test");
		request.setLastName("test");
		
		int score = scoreService.findScore(request);
		
		assertTrue(score > 0);
		assertTrue(score <= 100);
	}
	
	@Test
	public void testFindScoreRequestNull() {
	
		try {
			scoreService.findScore(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), ErrorConstants.ERROR_REQUEST_NULL);
		}

	}

}
