package com.sage.loanapound.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.dto.LoanRequestDto;

/**
 * The Class LoanRequest.
 */
@Entity
@Table(name = "loanRequest")
public class LoanRequest implements Cloneable {

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	/** The customer. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "customer", nullable = false)
	private Customer customer;

	/** The loan. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "loan", nullable = false)
	private Loan loan;

	/** The proverScore. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "providerScore", nullable = true) 
	private ProviderScore providerScore;

	/** The amount. */
	@Column(name = "amount", nullable = false)
	private long amount;

	/** The months. */
	@Column(name = "months", nullable = false)
	private int months;

	/** The start date. */
	@Column(name = "startDate", nullable = false)
	private LocalDate startDate;

	/** The end date. */
	@Column(name = "endDate", nullable = false)
	private LocalDate endDate;

	/** The status. */
	@Column(name = "status", nullable = false)
	private String status;

	/** The is mail send. */
	@Column(name = "isMailSend", nullable = false)
	private Boolean isMailSend;

	/** The score. */
	@Column(name = "score", nullable = false)
	private int score;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the loan
	 */
	public Loan getLoan() {
		return loan;
	}

	/**
	 * @return the providerScore
	 */
	public ProviderScore getProviderScore() {
		return providerScore;
	}

	/**
	 * @return the amount
	 */
	public long getAmount() {
		return amount;
	}

	/**
	 * @return the months
	 */
	public int getMonths() {
		return months;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the isMailSend
	 */
	public Boolean getIsMailSend() {
		return isMailSend;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @param loan
	 *            the loan to set
	 */
	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	/**
	 * @param providerScore
	 *            the providerScore to set
	 */
	public void setProviderScore(ProviderScore providerScore) {
		this.providerScore = providerScore;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(long amount) {
		this.amount = amount;
	}

	/**
	 * @param months
	 *            the months to set
	 */
	public void setMonths(int months) {
		this.months = months;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param isMailSend
	 *            the isMailSend to set
	 */
	public void setIsMailSend(Boolean isMailSend) {
		this.isMailSend = isMailSend;
	}

	/**
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
		return "LoanRequest [id=" + id + ", customer=" + customer + ", loan=" + loan + ", providerScore="
				+ providerScore + ", amount=" + amount + ", months=" + months + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", status=" + status + ", isMailSend=" + isMailSend + ", score=" + score
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * To dto.
	 *
	 * @return the loan request dto
	 */
	public LoanRequestDto toDto() {
		return new ModelMapper().map(this, LoanRequestDto.class);
	}
}
