package com.sage.loanapound.ws.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.sage.loanapound.constant.ErrorConstants;
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
	public ScoreResponse getScore(Customer customer) throws Exception {
		if(customer != null){
			LOGGER.info("Start - getScore(" + customer + ")");
			
			ScoreRequest request = new ScoreRequest();
			request.setPassport(customer.getId());
			request.setName(customer.getName());
			request.setLastName(customer.getLastname());
	
			ScoreResponse response = null; 
			
			response = (ScoreResponse) getWebServiceTemplate()
					.marshalSendAndReceive(request);
		
			LOGGER.info("End - getScore() - Result: " + response);
			return response;
		}else{
			throw new Exception(ErrorConstants.ERROR_CUSTOMER_NULL);
		}
	}

}
