package com.sage.loanapound.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sage.loanapound.constant.ErrorConstants;
import com.sage.loanapound.entity.Criteria;
import com.sage.loanapound.entity.LoanRequest;
import com.sage.loanapound.entity.ProviderScore;
import com.sage.loanapound.helper.ProviderScoreHelper;

/**
 * The Class ScoreService with the business logic to obtain the score.
 */
@Service("scoreService")
public class ScoreService extends ProviderScoreHelper {

	/** The provider score service. */
	@Autowired
	@Qualifier("providerScoreService")
	ProviderScoreService providerScoreService;

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(ScoreService.class);

	/**
	 * Sets the score. 
	 *
	 * @param loanRequest
	 *            the loan customer
	 * @return the loan customer
	 * @throws Exception 
	 */
	public LoanRequest setScore(LoanRequest loanRequest) throws Exception {
		if(loanRequest != null){
			LOGGER.info("Start - setScore(" + loanRequest + ")");
			
			try {	
				Map<String, Integer> map;
	
				//Validate all criterias to provider and get the first that matched
				for(ProviderScore provider : providerScoreService.findAll()){
					map = new HashMap<>();
					
					for(Criteria list : provider.getCriterias()){
						map.put(list.getName(), list.getValue());
					}						
					if(map.isEmpty())
						LOGGER.warn("The provider " + provider + "hasn't any criteria");
					
					//Criteria for Amount				
					if(map.containsKey("minAmount") && map.containsKey("maxAmount")){
						if(loanRequest.getAmount() >= map.get("minAmount") && loanRequest.getAmount() <= map.get("maxAmount")){
							loanRequest.setProviderScore(provider);
							break;
						}
					}
				}
							
				if(loanRequest.getProviderScore().getId() > 0)
					loanRequest = getScoreFromProvider(loanRequest);
				//If no criteria matched
				else 
					loanRequest = getBestProviderScore(loanRequest);
						
			} catch (Exception e) {
				loanRequest.setStatus(ErrorConstants.ERROR_GETTING_SCORE);
			}
			LOGGER.info("End - setScore() - Return: " + loanRequest);
			return loanRequest;
		}else{
			throw new Exception(ErrorConstants.ERROR_LOAN_REQUEST_NULL);
		}
	}

}
