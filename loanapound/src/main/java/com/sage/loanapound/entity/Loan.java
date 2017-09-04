package com.sage.loanapound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.dto.LoanDto;

/**
 * The Class Loan.
 */
@Entity
@Table(name = "loan")
public class Loan {

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	/** The type. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeLoan", nullable = false)
	private TypeLoan typeLoan;

	/** The amount min. */
	@Column(name = "minAmount", nullable = false, precision = 2)
	private double minAmount;

	/** The amount max. */
	@Column(name = "maxAmount", nullable = false, precision = 2)
	private double maxAmount;

	/** The rate. */
	@Column(name = "rate", nullable = false, precision = 2)
	private double rate;

	/** The month min. */
	@Column(name = "minMonth", nullable = false)
	private int minMonth;

	/** The month max. */
	@Column(name = "maxMonth", nullable = false)
	private int maxMonth;

	/** The provider. */
	@Column(name = "provider", nullable = false, length = 64)
	private String provider;

	/** The amount min. */
	@Column(name = "scoreApproved", nullable = false)
	private int scoreApproved;

	/** The amount max. */
	@Column(name = "scoreRejected", nullable = false)
	private int scoreRejected;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the typeLoan
	 */
	public TypeLoan getTypeLoan() {
		return typeLoan;
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
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param typeLoan
	 *            the typeLoan to set
	 */
	public void setTypeLoan(TypeLoan typeLoan) {
		this.typeLoan = typeLoan;
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
		return "Loan [id=" + id + ", typeLoan=" + typeLoan + ", minAmount=" + minAmount + ", maxAmount=" + maxAmount
				+ ", rate=" + rate + ", minMonth=" + minMonth + ", maxMonth=" + maxMonth + ", provider=" + provider
				+ ", scoreApproved=" + scoreApproved + ", scoreRejected=" + scoreRejected + "]";
	}

	/**
	 * To dto.
	 *
	 * @return the loan dto
	 */
	public LoanDto toDto() {
		return new ModelMapper().map(this, LoanDto.class);
	}
}
