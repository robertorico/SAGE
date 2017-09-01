package com.sage.loanapound.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sage.loanapound.dao.CustomerDAOI;
import com.sage.loanapound.entity.Customer;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerService.
 */
@Service("customerService")
public class CustomerService{
	
	/** The customer DAO. */
	@Autowired
	@Qualifier("customerDAOI")
	CustomerDAOI customerDAO;
	
	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(CustomerService.class);
	

	/**
	 * Adds the.
	 *
	 * @param entity the entity
	 */
	public void add(Customer entity){
		LOGGER.info("Calling to add(" + entity + ")");
		customerDAO.save(entity);
	}
	
}
