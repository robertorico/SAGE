package com.sage.loanapound.dto;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.entity.Loan;

/**
 * The Class LoanDto.
 */
public class LoanDto {

	/** The id. */
	private int id;

	/** The type. */
	@NotNull
	private String type;

	/** The amount min. */
	@NotNull
	private double amountMin;

	/** The amount max. */
	@NotNull
	private double amountMax;

	/** The rate. */
	@NotNull
	private double rate;

	/** The month min. */
	@NotNull
	private int monthMin;

	/** The month max. */
	@NotNull
	private int monthMax;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the amount min.
	 *
	 * @return the amountMin
	 */
	public double getAmountMin() {
		return amountMin;
	}

	/**
	 * Sets the amount min.
	 *
	 * @param amountMin
	 *            the amountMin to set
	 */
	public void setAmountMin(double amountMin) {
		this.amountMin = amountMin;
	}

	/**
	 * Gets the amount max.
	 *
	 * @return the amountMax
	 */
	public double getAmountMax() {
		return amountMax;
	}

	/**
	 * Sets the amount max.
	 *
	 * @param amountMax
	 *            the amountMax to set
	 */
	public void setAmountMax(double amountMax) {
		this.amountMax = amountMax;
	}

	/**
	 * Gets the rate.
	 *
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Sets the rate.
	 *
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * Gets the month min.
	 *
	 * @return the monthMin
	 */
	public int getMonthMin() {
		return monthMin;
	}

	/**
	 * Sets the month min.
	 *
	 * @param monthMin
	 *            the monthMin to set
	 */
	public void setMonthMin(int monthMin) {
		this.monthMin = monthMin;
	}

	/**
	 * Gets the month max.
	 *
	 * @return the monthMax
	 */
	public int getMonthMax() {
		return monthMax;
	}

	/**
	 * Sets the month max.
	 *
	 * @param monthMax
	 *            the monthMax to set
	 */
	public void setMonthMax(int monthMax) {
		this.monthMax = monthMax;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoanDto [id=" + id + ", type=" + type + ", amountMin=" + amountMin + ", amountMax=" + amountMax
				+ ", rate=" + rate + ", monthMin=" + monthMin + ", monthMax=" + monthMax + "]";
	}
	
	/**
	 * To entity.
	 *
	 * @return the loan
	 */
	public Loan toEntity(){
		return new ModelMapper().map(this, Loan.class);
	}
}
