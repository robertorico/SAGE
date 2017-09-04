package com.provider.ws.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import com.provider.ws.contansts.ErrorConstants;
import com.provider.ws.model.ScoreRequest;
import com.provider.ws.model.ScoreResponse;

public class TestScoreEndpoint {
	@InjectMocks
	ScoreEndpoint scoreEndpoint = new ScoreEndpoint(null);
	
	@Before
	public void setupMock() {
		initMocks(this);
	}

	@Test
	public void testGetScore() throws Exception {
		ScoreRequest request = new ScoreRequest();
		request.setPassport("test");
		request.setName("test");
		request.setLastName("test");
		ScoreResponse response = scoreEndpoint.getScore(request);
		
		assertNotNull(response);
	}
	
	@Test
	public void testGetScoreRequestNull() {
		try {
			scoreEndpoint.getScore(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), ErrorConstants.ERROR_REQUEST_NULL);
		}
	}

}
