package com.sage.loanapound.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sage.loanapound.entity.Loan;

/**
 * The Interface LoanDAOI.
 */
@Repository("loanDAOI")
public interface LoanDAOI extends JpaRepository<Loan, Serializable>{
	List<Loan> findAllByOrderByTypeAsc();
}
