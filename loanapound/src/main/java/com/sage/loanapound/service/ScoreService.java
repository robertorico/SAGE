package com.sage.loanapound.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sage.loanapound.constant.ErrorConstants;
import com.sage.loanapound.entity.LoanCustomer;
import com.sage.loanapound.helper.ProviderScoreHelper;

/**
 * The Class ScoreService.
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
	 * Sets the score. Contain the business logic to obtain the score
	 *
	 * @param loanCustomer
	 *            the loan customer
	 * @return the loan customer
	 */
	public LoanCustomer setScore(LoanCustomer loanCustomer) {
		LOGGER.info("Start - setScore(" + loanCustomer + ")");

		try {				
			if (loanCustomer.getAmount() < 2000)
				loanCustomer = getScoreFromProvider1(loanCustomer);
			else if (loanCustomer.getAmount() < 6000)
				loanCustomer = getScoreFromProvider2(loanCustomer);
			else
				loanCustomer = getBestProviderScore(loanCustomer);
		} catch (Exception e) {
			LOGGER.error("setScore - Error: " + e.toString());
			loanCustomer.setStatus(ErrorConstants.ERROR_GETTING_SCORE);		
		}

		LOGGER.info("End - setScore() - Result: " + loanCustomer);
		return loanCustomer;
	}

}
