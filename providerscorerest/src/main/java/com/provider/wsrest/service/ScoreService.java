package com.provider.wsrest.service;

import org.springframework.stereotype.Service;

/**
 * The Class ScoreService. //Business Logic
 */
@Service("scoreService")
public class ScoreService {

	/**
	 * Find score.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int findScore(String	id){
	
		Double score = (Math.random()*100) + 1;		

		return score.intValue();
	}
}
