package com.sage.loanapound.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sage.loanapound.entity.ProviderScore;

/**
 * The Interface LoanCustomerDAOI.
 */
@Repository("providerScoreDAOI")
public interface ProviderScoreDAOI extends JpaRepository<ProviderScore, Serializable>{
	ProviderScore findByName(String name);
}
