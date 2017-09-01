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

import com.sage.loanapound.configuration.SoapClientConfig;
import com.sage.loanapound.constant.ErrorConstants;
import com.sage.loanapound.entity.Customer;
import com.sage.loanapound.entity.LoanCustomer;
import com.sage.loanapound.entity.ProviderScore;
import com.sage.loanapound.service.ProviderScoreService;
import com.sage.loanapound.wsclient.ProviderSoap;
import com.sage.loanapound.wsclient.model.RequestProviderRest;
import com.sage.loanapound.wsclient.model.ResponseProviderRest;
import com.sage.loanapound.wsclient.model.ScoreResponse;

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
	 */
	protected LoanCustomer getScoreFromProvider1(LoanCustomer loanCustomer) {
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
	 */
	protected LoanCustomer getScoreFromProvider2(LoanCustomer loanCustomer) {
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
	 */
	protected LoanCustomer getBestProviderScore(LoanCustomer loanCustomer) {
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
	 */
	private List<LoanCustomer> getAllScore(LoanCustomer loanCustomer) {
		LOGGER.info("Start - getScoreFromProvider(" + loanCustomer + ")");

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
		LOGGER.info("End - getScoreFromProvider() - Return: " + listWithProviderScore);
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
	 */
	private int getScoreFromProvider(Customer customer, String provider) {
		LOGGER.info("Start - getScoreFromProvider(" + customer + ", provider=" + provider + ")");

		Integer score = null;

		switch (provider) {
			case "provider1":
				AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
				ctx.register(SoapClientConfig.class);
				ctx.refresh();
				ProviderSoap provider1 = ctx.getBean(ProviderSoap.class);
				ScoreResponse response = provider1.getScore(customer);
				score = response.getScore();
				break;
			case "provider2":
				RestTemplate restTemplate = new RestTemplate();
				RequestProviderRest request = new RequestProviderRest(customer.getId(), customer.getName(),
						customer.getSurname());
				ResponseProviderRest res = restTemplate.postForObject("http://localhost:82/getScore/", request,
						ResponseProviderRest.class);
				score = res.getScore();
				break;
		}

		LOGGER.info("End - getScoreFromProvider() - Return: Score = " + score);
		return score;
	}
}
