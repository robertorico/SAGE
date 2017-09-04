package com.sage.loanapound.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sage.loanapound.entity.Customer;

/**
 * The JPA Interface CustomerDAOI.
 */
@Repository("customerDAOI")
public interface CustomerDAOI extends JpaRepository<Customer, Serializable>{}
