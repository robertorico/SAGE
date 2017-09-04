package com.provider.ws.endpoint;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.provider.ws.contansts.ErrorConstants;
import com.provider.ws.model.ScoreRequest;
import com.provider.ws.model.ScoreResponse;
import com.provider.ws.service.ScoreServiceImpl;

/**
 * The Class ScoreEndpoint.
 */
@Endpoint //POJO registers the class with Spring WS as a potential candidate for processing incoming SOAP messages.
public class ScoreEndpoint {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ScoreEndpoint.class);
	
	/** The Constant NAMESPACE_URI. */
	private static final String NAMESPACE_URI = "http://provider.com/ws/model";
	
	/** The score repository. */
	private ScoreServiceImpl scoreRepository;
	
	/**
	 * Instantiates a new score endpoint.
	 *
	 * @param scoreRepository the score repository
	 */
	@Autowired
	ScoreEndpoint (ScoreServiceImpl scoreRepository){
		this.scoreRepository = scoreRepository;
	}
	
	/**
	 * Gets the score.
	 *
	 * @param request the request
	 * @return the score
	 * @throws Exception 
	 * @PayloadRoot - Is then used by Spring WS to pick the handler method
	 * @RequestPayload - indicates that the incoming message will be mapped to the method’s request parameter.
	 * @ResponsePayload - Annotation makes Spring WS map the returned value to the response payload.
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "scoreRequest")
	@ResponsePayload
	public ScoreResponse getScore(@RequestPayload ScoreRequest request) throws Exception {
		if(request != null){
			LOGGER.info("Start - getScore");
			
			ScoreResponse response = new ScoreResponse();
			try {
				response.setScore(scoreRepository.findScore(request));
			} catch (Exception e) {
				LOGGER.error(e.toString());
				response.setScore(-1);
			}
			response.setName(request.getName());
			response.setLastName(request.getLastName());
			
			LOGGER.info("End - getScore");
			return response;
		}else{
			throw new Exception(ErrorConstants.ERROR_REQUEST_NULL);
		}
	}
	
}
