package com.sage.loanapound.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.dto.LoanCustomerDto;

/**
 * The Class LoanCustomer.
 */
@Entity
@Table(name = "loanCustomer")
public class LoanCustomer implements Cloneable {

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	/** The customer. */
	@NotNull
	@OneToOne
	@JoinColumn(name = "customer")
	private Customer customer;

	/** The loan. */
	@NotNull
	@OneToOne
	@JoinColumn(name = "loan")
	private Loan loan;

	/** The amount. */
	@NotNull
	@Column(name = "amount")
	private Double amount;

	/** The months. */
	@NotNull
	@Column(name = "months")
	private int months;

	/** The start date. */
	@NotNull
	@Column(name = "start_date")
	private LocalDate startDate;

	/** The end date. */
	@NotNull
	@Column(name = "end_date")
	private LocalDate endDate;

	/** The status. */
	@NotNull
	@Column(name = "status")
	private String status;

	/** The is mail send. */
	@NotNull
	@Column(name = "is_mail_send")
	private Boolean isMailSend;

	/** The score. */
	@NotNull
	@Column(name = "score")
	private int score;

	/** The provider. */
	@OneToOne
	@JoinColumn(name = "provider")
	private ProviderScore provider;

	/**
	 * Gets the provider.
	 *
	 * @return the provider
	 */
	public ProviderScore getProvider() {
		return provider;
	}

	/**
	 * Sets the provider.
	 *
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(ProviderScore provider) {
		this.provider = provider;
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
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the loan.
	 *
	 * @return the loan
	 */
	public Loan getLoan() {
		return loan;
	}

	/**
	 * Sets the loan.
	 *
	 * @param loan
	 *            the loan to set
	 */
	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Double getAmount() {
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
	 * Sets the amount.
	 *
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Double amount) {
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
	 * Gets the start date.
	 *
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
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
	 * Gets the end date.
	 *
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the state to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * Sets the checks if is mail send.
	 *
	 * @param isMailSend
	 *            the isMailSend to set
	 */
	public void setIsMailSend(Boolean isMailSend) {
		this.isMailSend = isMailSend;
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
	 * Sets the score.
	 *
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoanCustomer [id=" + id + ", customer=" + customer + ", loan=" + loan + ", amount=" + amount
				+ ", months=" + months + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status
				+ ", isMailSend=" + isMailSend + ", score=" + score + ", provider=" + provider + "]";
	}

	/**
	 * To dto.
	 *
	 * @return the loan customer dto
	 */
	public LoanCustomerDto toDto() {
		return new ModelMapper().map(this, LoanCustomerDto.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
