package com.sage.loanapound.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.NotEmpty;
import org.modelmapper.ModelMapper;

import com.sage.loanapound.entity.LoanCustomer;

/**
 * The Class LoanCustomerDto.
 */
public class LoanCustomerDto {

	/** The id. */
	private int id;

	/** The customer. */
	@NotEmpty
	private CustomerDto customer;

	/** The loan. */
	@NotEmpty
	private LoanDto loan;

	/** The amount. */
	@NotEmpty
	private double amount;

	/** The month. */
	@NotEmpty
	private int months;

	/** The start date. */
	private LocalDate startDate;

	/** The end date. */
	private LocalDate endDate;

	/** The status. */
	private String status;

	/** The is mail send. */
	private Boolean isMailSend;

	/** The score. */
	private int score;

	/** The provider. */
	private ProviderScoreDto provider;

	/**
	 * Instantiates a new loan customer dto.
	 */
	public LoanCustomerDto() {
		customer = new CustomerDto();
		loan = new LoanDto();
		provider = new ProviderScoreDto();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public CustomerDto getCustomer() {
		return customer;
	}

	/**
	 * Gets the loan.
	 *
	 * @return the loan
	 */
	public LoanDto getLoan() {
		return loan;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Gets the months.
	 *
	 * @return the months
	 */
	public int getMonths() {
		return months;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Gets the checks if is mail send.
	 *
	 * @return the isMailSend
	 */
	public Boolean getIsMailSend() {
		return isMailSend;
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
	 * Gets the provider.
	 *
	 * @return the provider
	 */
	public ProviderScoreDto getProvider() {
		return provider;
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
	 * Sets the customer.
	 *
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	/**
	 * Sets the loan.
	 *
	 * @param loan
	 *            the loan to set
	 */
	public void setLoan(LoanDto loan) {
		this.loan = loan;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Sets the months.
	 *
	 * @param months
	 *            the months to set
	 */
	public void setMonths(int months) {
		this.months = months;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Sets the checks if is mail send.
	 *
	 * @param isMailSend
	 *            the isMailSend to set
	 */
	public void setIsMailSend(Boolean isMailSend) {
		this.isMailSend = isMailSend;
	}

	/**
	 * Sets the score.
	 *
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Sets the provider.
	 *
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(ProviderScoreDto provider) {
		this.provider = provider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoanCustomerDto [id=" + id + ", customer=" + customer + ", loan=" + loan + ", amount=" + amount
				+ ", months=" + months + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status
				+ ", isMailSend=" + isMailSend + ", score=" + score + ", provider=" + provider + "]";
	}

	/**
	 * To entity.
	 *
	 * @return the loan customer
	 */
	public LoanCustomer toEntity() {
		return new ModelMapper().map(this, LoanCustomer.class);
	}
}
