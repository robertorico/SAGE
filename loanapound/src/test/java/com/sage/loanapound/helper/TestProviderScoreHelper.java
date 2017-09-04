package com.sage.loanapound.helper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.sage.loanapound.constant.ErrorConstants;
import com.sage.loanapound.constant.TypeWS;
import com.sage.loanapound.entity.ConfigProvider;
import com.sage.loanapound.entity.Customer;
import com.sage.loanapound.entity.LoanRequest;
import com.sage.loanapound.entity.ProviderScore;
import com.sage.loanapound.entity.TypeWs;
import com.sage.loanapound.service.ProviderScoreService;

public class TestProviderScoreHelper {
	
	@InjectMocks
	ProviderScoreHelper providerScoreHelper = new ProviderScoreHelper();

	@Mock(name="providerScoreService")
	ProviderScoreService providerScoreService;

	@Before
	public void setupMock() {
		initMocks(this);
	}

	@Test
	public void testGetScoreFromProviderSoap() throws Exception {
		LoanRequest loanRequest = new LoanRequest();
		
		//Create customer
		Customer customer = new Customer();
		customer.setId("test");
		customer.setName("test");
		customer.setLastname("test");
		
		//Set customer
		loanRequest.setCustomer(customer);
		
		//Create config provider
		Set<ConfigProvider> setConfigProvider = new HashSet<>();
		ConfigProvider configProvider = new ConfigProvider();
		configProvider.setTypeWs(new TypeWs(TypeWS.SOAP.getName()));
		setConfigProvider.add(configProvider);
		
		//Create provider
		ProviderScore provider = new ProviderScore();	
		provider.setId(1);
		provider.setName("provider1");
		
		//Set config provider
		provider.setConfigProvider(setConfigProvider);
		
		loanRequest.setProviderScore(provider);

		// Call to WS SOAP provider 1
		loanRequest = providerScoreHelper.getScoreFromProvider(loanRequest);
			
		// The score it's a number between 1 and 100
		assertTrue(loanRequest.getScore() > 0);
		assertTrue(loanRequest.getScore() <= 100);
	}
	
	@Test
	public void testGetScoreFromWithoutConfigProvider() {
		LoanRequest loanRequest = new LoanRequest();
		
		//Create customer
		Customer customer = new Customer();
		customer.setId("test");
		customer.setName("test");
		customer.setLastname("test");
		
		//Set customer
		loanRequest.setCustomer(customer);
		
		//Create provider
		ProviderScore provider = new ProviderScore();	
		provider.setId(1);
		provider.setName("provider1");
		
		//Set config provider		
		loanRequest.setProviderScore(provider);

		// Call to WS SOAP provider 1
		try {
			providerScoreHelper.getScoreFromProvider(loanRequest);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals(ErrorConstants.ERROR_PROVIDERS_NOT_CONFIGURATION));
		}
	}
	
	@Test
	public void testGetScoreWithoutProvider() {
		LoanRequest loanRequest = new LoanRequest();
		
		//Create customer
		Customer customer = new Customer();
		customer.setId("test");
		customer.setName("test");
		customer.setLastname("test");
		
		//Set customer
		loanRequest.setCustomer(customer);

		// Call to WS SOAP provider 1
		try {
			providerScoreHelper.getScoreFromProvider(loanRequest);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals(ErrorConstants.ERROR_PROVIDER_NULL));
		}
	}
	
	@Test
	public void testGetScoreLoanRequestNull() {	
		try {
			providerScoreHelper.getScoreFromProvider(null);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals(ErrorConstants.ERROR_LOAN_REQUEST_NULL));
		}
	}
	
	@Test
	public void testGetScoreFromProviderRest() throws Exception {
		LoanRequest loanRequest = new LoanRequest();
		
		//Create customer
		Customer customer = new Customer();
		customer.setId("test");
		customer.setName("test");
		customer.setLastname("test");
		
		//Set customer
		loanRequest.setCustomer(customer);
		
		//Create config provider
		Set<ConfigProvider> setConfigProvider = new HashSet<>();
		ConfigProvider configProvider = new ConfigProvider();
		configProvider.setTypeWs(new TypeWs(TypeWS.REST.getName()));
		configProvider.setUrl("http://localhost:82/getScore/");
		setConfigProvider.add(configProvider);
		
		//Create provider
		ProviderScore provider = new ProviderScore();	
		provider.setId(2);
		provider.setName("provider2");
		
		//Set config provider
		provider.setConfigProvider(setConfigProvider);	
		loanRequest.setProviderScore(provider);

		// Call to WS REST provider 2	
		loanRequest = providerScoreHelper.getScoreFromProvider(loanRequest);
			
		// The score it's a number between 1 and 100
		assertTrue(loanRequest.getScore() > 0);
		assertTrue(loanRequest.getScore() <= 100);
	}
	
	@Test
	public void testGetBestProviderScore() throws Exception {
		LoanRequest loanRequest = new LoanRequest();
		
		//Create customer
		Customer customer = new Customer();
		customer.setId("test");
		customer.setName("test");
		customer.setLastname("test");
		loanRequest.setCustomer(customer);
		
		//Create config provider1
		Set<ConfigProvider> setConfigProvider1 = new HashSet<>();
		ConfigProvider configProvider1 = new ConfigProvider();
		configProvider1.setTypeWs(new TypeWs(TypeWS.SOAP.getName()));
		setConfigProvider1.add(configProvider1);
		
		//Create config provider2
		Set<ConfigProvider> setConfigProvider2 = new HashSet<>();
		ConfigProvider configProvider2 = new ConfigProvider();
		configProvider2.setTypeWs(new TypeWs(TypeWS.REST.getName()));
		configProvider2.setUrl("http://localhost:82/getScore/");
		setConfigProvider2.add(configProvider2);
		
		//Create provider1
		ProviderScore provider1 = new ProviderScore();	
		provider1.setId(1);
		provider1.setName("provider1");
		
		//Create provider2
		ProviderScore provider2 = new ProviderScore();	
		provider2.setId(2);
		provider2.setName("provider2");
		
		//Set config provider
		provider1.setConfigProvider(setConfigProvider1);	
		provider2.setConfigProvider(setConfigProvider2);	
	
		//Create list providers
		List<ProviderScore> listProviderScore = new ArrayList<>();
		listProviderScore.add(provider1);		
		listProviderScore.add(provider2);	
	
		//Mock
		when(providerScoreService.findAll()).thenReturn(listProviderScore);
		
		//Get the best score
		loanRequest = providerScoreHelper.getBestProviderScore(loanRequest);
		
		// The score it's a number between 1 and 100
		assertTrue(loanRequest.getScore() > 0);
		assertTrue(loanRequest.getScore() <= 100);
	}
	
	@Test
	public void testGetBestScoreWithoutConfigProvider(){
		LoanRequest loanRequest = new LoanRequest();
		
		//Create customer
		Customer customer = new Customer();
		customer.setId("test");
		customer.setName("test");
		customer.setLastname("test");
		loanRequest.setCustomer(customer);
		
		//Create provider1
		ProviderScore provider1 = new ProviderScore();	
		provider1.setId(1);
		provider1.setName("provider1");
		
		//Create provider2
		ProviderScore provider2 = new ProviderScore();	
		provider2.setId(2);
		provider2.setName("provider2");
	
		//Create list providers
		List<ProviderScore> listProviderScore = new ArrayList<>();
		listProviderScore.add(provider1);		
		listProviderScore.add(provider2);	
	
		//Mock
		when(providerScoreService.findAll()).thenReturn(listProviderScore);
		
		//Get the best score
		try {
			providerScoreHelper.getBestProviderScore(loanRequest);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals(ErrorConstants.ERROR_PROVIDERS_NOT_CONFIGURATION));
		}
	}
	
	@Test
	public void testGetBestScoreWithoutProvider(){
		LoanRequest loanRequest = new LoanRequest();
		
		//Create customer
		Customer customer = new Customer();
		customer.setId("test");
		customer.setName("test");
		customer.setLastname("test");
		loanRequest.setCustomer(customer);
	
		//Create list providers
		List<ProviderScore> listProviderScore = new ArrayList<>();
	
		//Mock
		when(providerScoreService.findAll()).thenReturn(listProviderScore);
		
		//Get the best score
		try {
			providerScoreHelper.getBestProviderScore(loanRequest);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals(ErrorConstants.ERROR_PROVIDER_NULL));
		}
	}
	
	@Test
	public void testGetBestScoreWithLoanRequestNull(){
		try {
			providerScoreHelper.getBestProviderScore(null);
		} catch (Exception e) {
			assertTrue(e.getMessage().equals(ErrorConstants.ERROR_LOAN_REQUEST_NULL));
		}
	}

}
