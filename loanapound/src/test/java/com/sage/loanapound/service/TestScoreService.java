package com.sage.loanapound.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.sage.loanapound.constant.ErrorConstants;
import com.sage.loanapound.entity.Criteria;
import com.sage.loanapound.entity.LoanRequest;
import com.sage.loanapound.entity.ProviderScore;


public class TestScoreService {

	@InjectMocks
	private ScoreService scoreService = new ScoreService();
	
	@Mock
	ProviderScoreService providerScoreService;
	
	@Before
	public void setupMock() {
		initMocks(this);
	}
	
	@Test
	public void testSetScore() throws Exception {
		LoanRequest loanRequest = new LoanRequest();
		loanRequest.setAmount(2500);

		//Create provider
		ProviderScore provider = new ProviderScore();	
		provider.setId(1);
		provider.setName("provider1");
			
		//Create creterias
		Criteria criteria1 = new Criteria();
		criteria1.setId(1);
		criteria1.setName("minAmount");
		criteria1.setValue(1000);
		
		Criteria criteria2 = new Criteria();
		criteria2.setId(1);
		criteria2.setName("maxAmount");
		criteria2.setValue(5000);
		
		//Set criterias
		List<Criteria> listCriteria = new ArrayList<>();
		listCriteria.add(criteria1);
		listCriteria.add(criteria2);
		
		//Set Criteria
		provider.setCriterias(listCriteria);
		
		List<ProviderScore> listProvider = new ArrayList<>();
			listProvider.add(provider);		
		//Mock
		when(providerScoreService.findAll()).thenReturn(listProvider);
		
		loanRequest = scoreService.setScore(loanRequest);
		
		assertTrue(loanRequest.getProviderScore().getId() > 0);	
	}
	
	@Test
	public void testSetScoreWithoutCriteria() throws Exception {
		LoanRequest loanRequest = new LoanRequest();
		loanRequest.setAmount(2500);

		//Create provider
		ProviderScore provider = new ProviderScore();	
		provider.setId(1);
		provider.setName("provider1");
			
		
		List<ProviderScore> listProvider = new ArrayList<>();
			listProvider.add(provider);		
		//Mock
		when(providerScoreService.findAll()).thenReturn(listProvider);
		
		LoanRequest out = scoreService.setScore(loanRequest);
		
		assertEquals(loanRequest, out);
		
	}
	
	@Test
	public void testSetScoreLoanRequestNull() {
		try{
			scoreService.setScore(null);
		}catch(Exception e){
			assertEquals(e.getMessage(), ErrorConstants.ERROR_LOAN_REQUEST_NULL);
		}
	}

}
