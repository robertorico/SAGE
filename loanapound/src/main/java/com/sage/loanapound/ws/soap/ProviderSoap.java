package com.sage.loanapound.ws.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.sage.loanapound.entity.Customer;

import wsdl.classes.ScoreRequest;
import wsdl.classes.ScoreResponse;

/**
 * The Class ProviderSoap.
 */
public class ProviderSoap extends WebServiceGatewaySupport {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProviderSoap.class);
	
	/**
	 * Gets the score.
	 *
	 * @param customer the customer
	 * @return the score
	 * @throws Exception 
	 */
	public ScoreResponse getScore(Customer customer) {
		LOGGER.info("Start - getScore(" + customer + ")");
		
		ScoreRequest request = new ScoreRequest();
		request.setPassport(customer.getId());
		request.setName(customer.getName());
		request.setLastName(customer.getSurname());

		ScoreResponse response = null; 
		
		response = (ScoreResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request);
	
		LOGGER.info("End - getScore() - Result: " + response);
		return response;
	}

}
