package com.sage.loanapound.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sage.loanapound.entity.LoanCustomer;

/**
 * The Interface LoanCustomerDAOI.
 */
@Repository("loanCustomerDAOI")
public interface LoanCustomerDAOI extends JpaRepository<LoanCustomer, Serializable>{
	List<LoanCustomer> findAllByOrderByCustomerAscStatusAsc();
}
