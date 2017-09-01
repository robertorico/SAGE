package com.sage.loanapound.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.sage.loanapound.constant.ErrorConstants;
import com.sage.loanapound.entity.Customer;
import com.sage.loanapound.entity.LoanCustomer;
import com.sage.loanapound.entity.ProviderScore;
import com.sage.loanapound.service.ProviderScoreService;
import com.sage.loanapound.ws.configuration.SoapClientConfig;
import com.sage.loanapound.ws.model.RequestProviderRest;
import com.sage.loanapound.ws.model.ResponseProviderRest;
import com.sage.loanapound.ws.soap.ProviderSoap;

import wsdl.classes.ScoreResponse;

/**
 * The Class ProviderScoreHelper.
 */
@XmlSeeAlso({ScoreResponse.class}) 
public class ProviderScoreHelper {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(ProviderScoreHelper.class);

	/** The provider score service. */
	@Autowired
	@Qualifier("providerScoreService")
	ProviderScoreService providerScoreService;

	/**
	 * Gets the score from provider 1.
	 *
	 * @param loanCustomer
	 *            the loan customer
	 * @return the score from provider 1
	 * @throws Exception 
	 */
	protected LoanCustomer getScoreFromProvider1(LoanCustomer loanCustomer) throws Exception {
		LOGGER.info("Start - getScoreFromProvider1(" + loanCustomer + ")");
		
		loanCustomer.setProvider(providerScoreService.findByName("provider1"));
		loanCustomer.setScore(getScoreFromProvider(loanCustomer.getCustomer(), "provider1"));
		
		LOGGER.info("Start - getScoreFromProvider1() - Return: " + loanCustomer);
		return loanCustomer;
	}

	/**
	 * Gets the score from provider 2.
	 *
	 * @param loanCustomer
	 *            the loan customer
	 * @return the score from provider 2
	 * @throws Exception 
	 */
	protected LoanCustomer getScoreFromProvider2(LoanCustomer loanCustomer) throws Exception {
		LOGGER.info("Start - getScoreFromProvider2(" + loanCustomer + ")");
		
		loanCustomer.setProvider(providerScoreService.findByName("provider2"));
		loanCustomer.setScore(getScoreFromProvider(loanCustomer.getCustomer(), "provider2"));
				
		LOGGER.info("Start - getScoreFromProvider2() - Return: " + loanCustomer);
		return loanCustomer;
	}

	/**
	 * Gets the best provider score.
	 *
	 * @param loanCustomer
	 *            the loan customer
	 * @return the best provider score
	 * @throws Exception 
	 */
	protected LoanCustomer getBestProviderScore(LoanCustomer loanCustomer) throws Exception {
		LOGGER.info("Start - getBestProviderScore(" + loanCustomer + ")");

	
		// Get Scores of all providers
		List<LoanCustomer> listLC = getAllScore(loanCustomer);
		// Orders to Score
		listLC.sort(Comparator.comparing(LoanCustomer::getScore).reversed());
		
	

		LOGGER.info("End - getBestProviderScore() - Return: " + listLC.get(0));
		return listLC.get(0);
	}

	/**
	 * Gets the all score.
	 *
	 * @param loanCustomer
	 *            the loan customer
	 * @return the all score
	 * @throws Exception 
	 */
	private List<LoanCustomer> getAllScore(LoanCustomer loanCustomer) throws Exception {
		LOGGER.info("Start - getAllScore(" + loanCustomer + ")");

		List<LoanCustomer> listWithProviderScore = new ArrayList<>();
		LoanCustomer lCustomer;

		for (ProviderScore pScore : providerScoreService.findAll()) {

			// if fail the clone return Loancustomer with error like status and
			// finish de for
			try {
				lCustomer = (LoanCustomer) loanCustomer.clone();
			} catch (CloneNotSupportedException e) {
				lCustomer = loanCustomer;
				loanCustomer.setStatus(ErrorConstants.ERROR_CLONNING_OBJECT);
				listWithProviderScore.add(loanCustomer);
				break;
			}

			lCustomer.setProvider(pScore);
			lCustomer.setScore(getScoreFromProvider(loanCustomer.getCustomer(), pScore.getName()));

			listWithProviderScore.add(lCustomer);
		}
		LOGGER.info("End - getAllScore() - Return: " + listWithProviderScore);
		return listWithProviderScore;
	}

	/**
	 * Gets the score from provider.
	 *
	 * @param customer
	 *            the customer
	 * @param provider
	 *            the provider
	 * @return the score from provider
	 * @throws Exception 
	 */
	private int getScoreFromProvider(Customer customer, String provider) throws Exception {
		LOGGER.info("Start - getScoreFromProvider(" + customer + ", provider=" + provider + ")");

		Integer score = null;

		switch (provider) {
			case "provider1":
				try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()){
					ctx.register(SoapClientConfig.class);
					ctx.refresh();
					ProviderSoap provider1 = ctx.getBean(ProviderSoap.class);
					ScoreResponse response = provider1.getScore(customer);
					score = response.getScore();
				}catch(Exception e){
					LOGGER.error("getScoreFromProvider - " + e.toString());
					throw new Exception("Error calling WS \"Provider1\" ");
				}
			break;
				
			case "provider2":
				try{
					RequestProviderRest request = new RequestProviderRest(customer.getId(), customer.getName(),
							customer.getSurname());
					ResponseProviderRest res = new RestTemplate().postForObject("http://localhost:82/getScore/", request,
							ResponseProviderRest.class);
					score = res.getScore();
					
				}catch(Exception e){
					LOGGER.error("getScoreFromProvider - " + e.toString());
					throw new Exception("Error calling WS \"Provider2\" ");
				}
			break;
		}

		LOGGER.info("End - getScoreFromProvider() - Return: Score = " + score);
		return score;
	}
	
}
