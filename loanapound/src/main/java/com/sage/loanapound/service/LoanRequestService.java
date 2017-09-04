package com.sage.loanapound.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sage.loanapound.constant.LoanStatus;
import com.sage.loanapound.dao.LoanRequestDAOI;
import com.sage.loanapound.entity.LoanRequest;

/**
 * The Class LoanRequestService.
 */
@Service("loanRequestService")
public class LoanRequestService {

	/** The loan customer DAO. */
	@Autowired
	@Qualifier("loanRequestDAOI")
	LoanRequestDAOI loanRequestDAO;

	/** The customer service. */
	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

	/** The score service. */
	@Autowired
	@Qualifier("scoreService")
	ScoreService scoreService;

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(LoanRequestService.class);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<LoanRequest> findAll() {
		LOGGER.info("Calling to findAll()");
		return loanRequestDAO.findAll();
	}

	/**
	 * Find all by order by customer asc status asc.
	 *
	 * @return the list
	 */
	public List<LoanRequest> findAllByOrderByCustomerAscStatusAsc() {
		LOGGER.info("Calling to findAllByOrderByCustomerAscStatusAsc()");
		return loanRequestDAO.findAllByOrderByCustomerAscStatusAsc();
	}

	/**
	 * Adds the.
	 *
	 * @param entity
	 *            the entity
	 */
	public void add(LoanRequest entity) {
		LOGGER.info("Start - add(" + entity + ")");

		entity.setStatus(getStatusLoan(entity));
		initializeValuesLoan(entity);
		validateDatas(entity);
		loanRequestDAO.save(entity);

		LOGGER.info("End - add()");
	}

	/**
	 * Confirm loan.
	 *
	 * @param entity
	 *            the entity
	 */
	private void initializeValuesLoan(LoanRequest entity) {
		LOGGER.info("Start - initializeValuesLoan(" + entity + ")");

		entity.setStartDate(LocalDate.now());
		entity.setEndDate(entity.getStartDate().plusMonths(entity.getMonths()));
		entity.setIsMailSend(false);

		LOGGER.info("End - initializeValuesLoan(" + entity + ")");
	}
	
	/**
	 * Confirm loan.
	 *
	 * @param entity
	 *            the entity
	 */
	private void validateDatas(LoanRequest entity) {
		LOGGER.info("Start - validateDatas(" + entity + ")");

		//Set a null for save in database
		if(entity.getProviderScore().getId() == 0)
			entity.setProviderScore(null);

		LOGGER.info("End - validateDatas(" + entity + ")");
	}
	
	/**
	 * Gets the status loan.
	 *
	 * @param loanRequest
	 *            the entity
	 * @return the status loan
	 */
	private String getStatusLoan(LoanRequest loanRequest) {
		LOGGER.info("Start - getStatusLoan(" + loanRequest + ")");

		String status = loanRequest.getStatus();
		int score = loanRequest.getScore();

		// Validate if there is some error
		if (status == null)
			//Set the status according to loan
			if (score > loanRequest.getLoan().getScoreApproved())
				status = LoanStatus.APPROVED.name();
			else if (score < loanRequest.getLoan().getScoreRejected())
				status = LoanStatus.REJECTED.name();
			else
				status = LoanStatus.PENDING.name();

		LOGGER.info("End - getStatusLoan() - Return: " + status);
		return status;
	}

}
