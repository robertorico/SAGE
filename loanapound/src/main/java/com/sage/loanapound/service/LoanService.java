package com.sage.loanapound.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sage.loanapound.dao.LoanDAOI;
import com.sage.loanapound.entity.Loan;

/**
 * The Class LoanService.
 */
@Service("loanService")
public class LoanService{
	
	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(LoanService.class);
	
	/** The loan DAO. */
	@Autowired
	@Qualifier("loanDAOI")
	LoanDAOI loanDAO;
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Loan> findAll(){	
		LOGGER.info("Calling to findAll()");
		return loanDAO.findAll();	
	}
		
}
