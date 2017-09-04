package com.sage.loanapound.helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.sage.loanapound.constant.ErrorConstants;
import com.sage.loanapound.constant.GlobalConstants;
import com.sage.loanapound.constant.TypeWS;
import com.sage.loanapound.entity.ConfigProvider;
import com.sage.loanapound.entity.LoanRequest;
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
public class ProviderScoreHelper {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(ProviderScoreHelper.class);

	/** The provider score service. */
	@Autowired
	@Qualifier("providerScoreService")
	ProviderScoreService providerScoreService;

	/** The score. */
	private int score;

	/**
	 * Gets the score from provider 1 soap.
	 *
	 * @param loanRequest
	 *            the loan request
	 * @return the score from provider 1 soap
	 * @throws Exception
	 *             the exception
	 */
	protected LoanRequest getScoreFromProvider(LoanRequest loanRequest) throws Exception {
		if(loanRequest != null){
			LOGGER.info("Start - getScoreFromProvider(" + loanRequest + ")");
	
			if(loanRequest.getProviderScore() != null){
				//For default call WS Rest if the provider has
				if(loanRequest.getProviderScore().getConfigProvider() != null){
					for(ConfigProvider config : loanRequest.getProviderScore().getConfigProvider()){
						if(config.getTypeWs().getName().equalsIgnoreCase(TypeWS.REST.name())){
							score = getScoreFromWSRest(loanRequest);
							break;
						}else{
							score = getScoreFromWSSoap(loanRequest);
							break;		
						}
					}
				}else{
					throw new Exception(ErrorConstants.ERROR_PROVIDERS_NOT_CONFIGURATION);
				}
				loanRequest.setScore(score);
			}else{
				throw new Exception(ErrorConstants.ERROR_PROVIDER_NULL);
			}
		}else{
			throw new Exception(ErrorConstants.ERROR_LOAN_REQUEST_NULL);
		}
		
		LOGGER.info("End - getScoreFromProvider() - Return: " + loanRequest);
		return loanRequest;
	}

	/**
	 * Gets the best provider score.
	 *
	 * @param loanRequest
	 *            the loan request
	 * @return the best provider score
	 * @throws Exception
	 *             the exception
	 */
	protected LoanRequest getBestProviderScore(LoanRequest loanRequest) throws Exception {
		if(loanRequest != null){
			LOGGER.info("Start - getBestProviderScore(" + loanRequest + ")");
	
			// Get Scores of all providers
			List<LoanRequest> listLC = getAllScore(loanRequest);
			// Orders to Score
			listLC.sort(Comparator.comparing(LoanRequest::getScore).reversed());
	
			LOGGER.info("End - getBestProviderScore() - Return: " + listLC.get(0));
			//Get the best score
			return listLC.get(0);

		}else{
			throw new Exception(ErrorConstants.ERROR_LOAN_REQUEST_NULL);
		}
	}

	/**
	 * Gets the all score.
	 *
	 * @param loanRequest
	 *            the loan request
	 * @return the all score
	 * @throws Exception
	 *             the exception
	 */
	private List<LoanRequest> getAllScore(LoanRequest loanRequest) throws Exception {
		LOGGER.info("Start - getAllScore(" + loanRequest + ")");

		List<LoanRequest> listWithProviderScore = new ArrayList<>();
		LoanRequest lCustomer = null;

		for (ProviderScore pScore : providerScoreService.findAll()) {
			// If fail the clone, return loanRequest with error in status and
			// finish de for
			try {
				lCustomer = (LoanRequest) loanRequest.clone();
			} catch (CloneNotSupportedException e) {
				lCustomer = loanRequest;
				lCustomer.setStatus(ErrorConstants.ERROR_CLONNING_OBJECT);
				listWithProviderScore.add(lCustomer);
				break;
			}

			lCustomer.setProviderScore(pScore);

			// Call to all WS
			if(pScore.getConfigProvider() != null){
				for (ConfigProvider config : pScore.getConfigProvider()) {
					if (config.getTypeWs().getName().equalsIgnoreCase(TypeWS.SOAP.getName()))
						lCustomer.setScore(getScoreFromWSSoap(lCustomer));
					else
						lCustomer.setScore(getScoreFromWSRest(lCustomer));
				}
			}else{
				throw new Exception(ErrorConstants.ERROR_PROVIDERS_NOT_CONFIGURATION);
			}

			listWithProviderScore.add(lCustomer);
		}
		
		if(lCustomer == null){
			throw new Exception(ErrorConstants.ERROR_PROVIDER_NULL);
		}
			
			
		LOGGER.info("End - getAllScore() - Return: " + listWithProviderScore);
		return listWithProviderScore;
	}

	/**
	 * Gets the score from provider with WS soap.
	 *
	 * @param loanRequest
	 *            the loan request
	 * @return the score from provider with WS soap
	 * @throws Exception
	 *             the exception
	 */
	// All call Soap
	private int getScoreFromWSSoap(LoanRequest loanRequest) throws Exception {
		LOGGER.info("Start - getScoreFromWSSoap(" + loanRequest + ")");
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
			
			switch (loanRequest.getProviderScore().getName()) {
			
			//Calling WS provider1
			case GlobalConstants.PROVIDER1:
				ctx.register(SoapClientConfig.class);
				ctx.refresh();
				ProviderSoap provider1 = ctx.getBean(ProviderSoap.class);
				ScoreResponse response = provider1.getScore(loanRequest.getCustomer());
				score = response.getScore();
				break;
			}
		} catch (Exception e) {
			throw new Exception(ErrorConstants.ERROR_CALL_WS_SOAP);
		}
		
		LOGGER.info("End - getScoreFromWSSoap() - Score: " + score);
		return score;
	}

	/**
	 * Gets the score from provider with WS rest.
	 *
	 * @param loanRequest
	 *            the loan request
	 * @return the score from provider with WS rest
	 * @throws Exception
	 *             the exception
	 */
	// All call Rest
	private int getScoreFromWSRest(LoanRequest loanRequest) throws Exception {
		LOGGER.info("Start - getScoreFromWSRest(" + loanRequest + ")");
		try {
			switch (loanRequest.getProviderScore().getName()) {
			
			//Calling WS provider 2	
			case GlobalConstants.PROVIDER2:
				ResponseProviderRest res = null;
				
				//Create request
				RequestProviderRest request = new RequestProviderRest(loanRequest.getCustomer().getId(),
						loanRequest.getCustomer().getName(), loanRequest.getCustomer().getLastname());
				
				//Get config REST
				for(ConfigProvider config : loanRequest.getProviderScore().getConfigProvider())
					if(config.getTypeWs().getName().equalsIgnoreCase(TypeWS.REST.name()))
						//Call WS
						res = new RestTemplate().postForObject(config.getUrl(), request,
								ResponseProviderRest.class);
							
				score = res.getScore();
				break;			
			}
			
		} catch (Exception e) {
			LOGGER.error(ErrorConstants.ERROR_CALL_WS_REST + " - " + e.toString());
			throw new Exception(ErrorConstants.ERROR_CALL_WS_REST);
		}
		
		LOGGER.info("End - getScoreFromWSRest() - Score: " + score);
		return score;
	}

}
