package com.sage.loanapound.wsclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.sage.loanapound.entity.Customer;
import com.sage.loanapound.wsclient.model.ScoreRequest;
import com.sage.loanapound.wsclient.model.ScoreResponse;

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
	 */
	public ScoreResponse getScore(Customer customer) {
		LOGGER.info("Start - getScore(" + customer + ")");

		ScoreRequest request = new ScoreRequest();
		request.setPassport(customer.getId());
		request.setName(customer.getName());
		request.setLastName(customer.getSurname());

		LOGGER.info("Requesting score for " + request);
		ScoreResponse response = null; 
		try{
			response = (ScoreResponse) getWebServiceTemplate()
					.marshalSendAndReceive(request);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		LOGGER.info("End - getScore() - Result: " + response);
		return response;
	}

}
