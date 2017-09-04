package com.sage.loanapound.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sage.loanapound.entity.LoanRequest;

/**
 * The JPA Interface loanRequestDAOI.
 */
@Repository("loanRequestDAOI")
public interface LoanRequestDAOI extends JpaRepository<LoanRequest, Serializable>{
	List<LoanRequest> findAllByOrderByCustomerAscStatusAsc();
}
