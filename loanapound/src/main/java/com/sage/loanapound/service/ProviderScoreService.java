package com.sage.loanapound.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sage.loanapound.dao.ProviderScoreDAOI;
import com.sage.loanapound.entity.ProviderScore;

/**
 * The Class ProviderScoreService.
 */
@Service("providerScoreService")
public class ProviderScoreService {

	/** The provider score DAOI. */
	@Autowired
	@Qualifier("providerScoreDAOI")
	ProviderScoreDAOI providerScoreDAOI;

	private static final Log LOGGER = LogFactory.getLog(ProviderScoreService.class);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<ProviderScore> findAll() {
		LOGGER.info("Calling to findAll()");
		return providerScoreDAOI.findAll();
	}

	/**
	 * Find by name.
	 *
	 * @param name
	 *            the name
	 * @return the provider score
	 */
	public ProviderScore findByName(String name) {
		LOGGER.info("Calling to findByName(" + name + ")");
		return providerScoreDAOI.findByName(name);
	}
}
