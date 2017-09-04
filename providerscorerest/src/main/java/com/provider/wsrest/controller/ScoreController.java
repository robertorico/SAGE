package com.provider.wsrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.provider.wsrest.constants.ErrorConstants;
import com.provider.wsrest.model.Customer;
import com.provider.wsrest.model.Result;
import com.provider.wsrest.service.ScoreService;

/**
 * The Class ScoreController.
 */
@RestController
public class ScoreController {
	
	/** The score user service impl. */
	@Autowired
	@Qualifier("scoreService")
	ScoreService scoreUserServiceImpl;

    /**
     * Gets the score.
     *
     * @param customer the customer
     * @return the score
     * @throws Exception 
     */
    @PostMapping("/getScore")
    public Result getScore(@RequestBody Customer customer) throws Exception {
    	if(customer != null){
	    	Result res = new Result();
	        res.setScore(scoreUserServiceImpl.findScore(customer.getId()));
	        res.setCustomerId(customer.getId());
	        
	        return res;
    	}else{
    		throw new Exception(ErrorConstants.ERROR_CUSTOMER_NULL);
    	}
    }
}
