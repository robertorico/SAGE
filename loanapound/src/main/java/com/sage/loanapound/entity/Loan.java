package com.sage.loanapound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@Column(name = "type", nullable = false)
	private String type;

	/** The amount min. */
	@NotNull
	@Column(name = "amount_min", nullable = false, precision = 2)
	private double amountMin;

	/** The amount max. */
	@NotNull
	@Column(name = "amount_max", nullable = false, precision = 2)
	private double amountMax;

	/** The rate. */
	@NotNull
	@Column(name = "rate", nullable = false, precision = 2)
	private double rate;

	/** The month min. */
	@NotNull
	@Column(name = "month_min", nullable = false)
	private int monthMin;

	/** The month max. */
	@NotNull
	@Column(name = "month_max", nullable = false)
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
		return "LoanEntity [id=" + id + ", type=" + type + ", amountMin=" + amountMin + ", amountMax=" + amountMax
				+ ", rate=" + rate + ", monthMin=" + monthMin + ", monthMax=" + monthMax + "]";
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
