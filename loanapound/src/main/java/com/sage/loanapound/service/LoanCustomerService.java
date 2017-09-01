package com.sage.loanapound.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sage.loanapound.constant.GlobalConstants;
import com.sage.loanapound.dao.LoanCustomerDAOI;
import com.sage.loanapound.entity.LoanCustomer;

/**
 * The Class LoanCustomerService.
 */
@Service("loanCustomerService")
public class LoanCustomerService {

	/** The loan customer DAO. */
	@Autowired
	@Qualifier("loanCustomerDAOI")
	LoanCustomerDAOI loanCustomerDAO;

	/** The customer service. */
	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;

	/** The score service. */
	@Autowired
	@Qualifier("scoreService")
	ScoreService scoreService;

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(LoanCustomerService.class);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<LoanCustomer> findAll() {
		LOGGER.info("Calling to findAll()");
		return loanCustomerDAO.findAll();
	}

	/**
	 * Find all by order by customer asc status asc.
	 *
	 * @return the list
	 */
	public List<LoanCustomer> findAllByOrderByCustomerAscStatusAsc() {
		LOGGER.info("Calling to findAllByOrderByCustomerAscStatusAsc()");
		return loanCustomerDAO.findAllByOrderByCustomerAscStatusAsc();
	}

	/**
	 * Adds the.
	 *
	 * @param entity
	 *            the entity
	 */
	public void add(LoanCustomer entity) {
		LOGGER.info("Start - add(" + entity + ")");
		
		entity.setStatus(getStatusLoan(entity));
		initializeValuesLoan(entity);
		loanCustomerDAO.save(entity);
		
		LOGGER.info("End - add()");
	}

	/**
	 * Gets the status loan.
	 *
	 * @param entity
	 *            the entity
	 * @return the status loan
	 */
	private String getStatusLoan(LoanCustomer entity) {
		LOGGER.info("Start - getStatusLoan(" + entity + ")");

		String status = entity.getStatus();
		int score = entity.getScore();

		//Validate if there is some error
		if (status == null)
			if (score > GlobalConstants.LOAN_APPROVED_MIN)
				status = GlobalConstants.APPROVED;
			else if (score < GlobalConstants.LOAN_REJECTED_MAX)
				status = GlobalConstants.REJECTED;
			else
				status = GlobalConstants.PENDING;

		LOGGER.info("End - getStatusLoan() - Return: " + status);
		return status;
	}

	/**
	 * Confirm loan.
	 *
	 * @param entity
	 *            the entity
	 */
	private void initializeValuesLoan(LoanCustomer entity) {
		LOGGER.info("Start - initializeValuesLoan(" + entity + ")");
		
		entity.setStartDate(LocalDate.now());
		entity.setEndDate(entity.getStartDate().plusMonths(entity.getMonths()));
		entity.setIsMailSend(false);
		
		LOGGER.info("End - initializeValuesLoan(" + entity + ")");
	}

}
