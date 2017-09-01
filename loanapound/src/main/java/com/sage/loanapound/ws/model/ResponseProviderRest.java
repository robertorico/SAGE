package com.sage.loanapound.ws.model;

/**
 * The Class ResponseProviderRest.
 */
public class ResponseProviderRest {
	
	/** The customer id. */
	private String customerId;
	
	/** The score. */
	private int score;
	
	/**
	 * Gets the customer id.
	 *
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the customer id.
	 *
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
