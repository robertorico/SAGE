package com.sage.loanapound.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sage.loanapound.entity.Loan;

/**
 * The JPA Interface LoanDAOI.
 */
@Repository("loanDAOI")
public interface LoanDAOI extends JpaRepository<Loan, Serializable>{}
