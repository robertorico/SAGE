package com.sage.loanapound.dto;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.entity.Loan;

/**
 * The Class LoanDto.
 */
public class LoanDto {

	/** The id. */
	private int id;

	/** The type. */
	private TypeLoanDto type;

	/** The amount min. */
	private double minAmount;

	/** The amount max. */
	private double maxAmount;

	/** The rate. */
	private double rate;

	/** The month min. */
	private int minMonth;

	/** The month max. */
	private int maxMonth;

	private String provider;

	private int scoreApproved;

	private int scoreRejected;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public TypeLoanDto getType() {
		return type;
	}

	/**
	 * @return the minAmount
	 */
	public double getMinAmount() {
		return minAmount;
	}

	/**
	 * @return the maxAmount
	 */
	public double getMaxAmount() {
		return maxAmount;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @return the minMonth
	 */
	public int getMinMonth() {
		return minMonth;
	}

	/**
	 * @return the maxMonth
	 */
	public int getMaxMonth() {
		return maxMonth;
	}

	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @return the scoreApproved
	 */
	public int getScoreApproved() {
		return scoreApproved;
	}

	/**
	 * @return the scoreRejected
	 */
	public int getScoreRejected() {
		return scoreRejected;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TypeLoanDto type) {
		this.type = type;
	}

	/**
	 * @param minAmount
	 *            the minAmount to set
	 */
	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * @param maxAmount
	 *            the maxAmount to set
	 */
	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @param minMonth
	 *            the minMonth to set
	 */
	public void setMinMonth(int minMonth) {
		this.minMonth = minMonth;
	}

	/**
	 * @param maxMonth
	 *            the maxMonth to set
	 */
	public void setMaxMonth(int maxMonth) {
		this.maxMonth = maxMonth;
	}

	/**
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * @param scoreApproved
	 *            the scoreApproved to set
	 */
	public void setScoreApproved(int scoreApproved) {
		this.scoreApproved = scoreApproved;
	}

	/**
	 * @param scoreRejected
	 *            the scoreRejected to set
	 */
	public void setScoreRejected(int scoreRejected) {
		this.scoreRejected = scoreRejected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoanDto [id=" + id + ", type=" + type + ", amountMin=" + minAmount + ", amountMax=" + maxAmount
				+ ", rate=" + rate + ", monthMin=" + minMonth + ", monthMax=" + maxMonth + ", provider=" + provider
				+ ", scoreApproved=" + scoreApproved + ", scoreRejected=" + scoreRejected + "]";
	}

	/**
	 * To entity.
	 *
	 * @return the loan
	 */
	public Loan toEntity() {
		return new ModelMapper().map(this, Loan.class);
	}
}
