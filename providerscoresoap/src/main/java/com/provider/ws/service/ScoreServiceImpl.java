package com.provider.ws.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.provider.ws.contansts.ErrorConstants;
import com.provider.ws.model.ScoreRequest;

/**
 * The Class ScoreServiceImpl.
 */
@Repository("scoreServiceImpl")
public class ScoreServiceImpl {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(ScoreServiceImpl.class);
	
	/**
	 * Find score.
	 *
	 * @param request the request
	 * @return the int
	 * @throws Exception 
	 */
	public int findScore(ScoreRequest request) throws Exception {
		if(request != null){
			LOGGER.info("Start - findScore()");
			Double score = (Math.random()*100) + 1;		
			System.out.println("For the user " + request.getName() + " the score is: " + score.intValue());
			LOGGER.info("End - findScore() - Return: " + score.intValue());
			return score.intValue();
		}else{
			throw new Exception(ErrorConstants.ERROR_REQUEST_NULL);
		}
	}
}
