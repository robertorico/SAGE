package com.provider.wsrest.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class TestScoreService {

	@InjectMocks
	ScoreService scoreService = new ScoreService();
	
	@Before
	public void setupMock() {
		initMocks(this);
	}
	
	@Test
	public void testFindScore() {
		int score = scoreService.findScore("ID");
		assertTrue(score > 0);
		assertTrue(score <= 100);
	}

}
