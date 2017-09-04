package com.sage.loanapound.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sage.loanapound.constant.ErrorConstants;
import com.sage.loanapound.constant.ViewConstants;
import com.sage.loanapound.dto.CustomerDto;
import com.sage.loanapound.dto.LoanDto;
import com.sage.loanapound.dto.LoanRequestDto;
import com.sage.loanapound.entity.LoanRequest;
import com.sage.loanapound.service.CustomerService;
import com.sage.loanapound.service.LoanRequestService;
import com.sage.loanapound.service.LoanService;
import com.sage.loanapound.service.ScoreService;

/**
 * The Class LapController.
 */
@Controller
@RequestMapping("/lap")
public class LapController{
	
	/*********************
	 *  
	 * 		BEANS
	 * 
	 *********************/
		
	/** The customer service. */
	@Autowired
	@Qualifier("customerService")
	CustomerService customerService;
	
	/** The loan service. */
	@Autowired
	@Qualifier("loanService")
	LoanService loanService;
	
	/** The score service. */
	@Autowired
	@Qualifier("scoreService")
	ScoreService scoreService;
	
	/** The loan customer service. */
	@Autowired
	@Qualifier("loanRequestService")
	LoanRequestService loanRequestService;

	
	/*********************
	 *  
	 * 	GLOBAL VARIABLES
	 * 
	 *********************/
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LapController.class);	
	
	/** The loan customer dto. */
	private LoanRequestDto globalLoanRequestDto;
	
	
	/*********************
	 *  
	 * 		METHODS
	 * 
	 *********************/
	
	/**
	 * Show Form customer.
	 *
	 * @return the model and view
	 */
	@GetMapping({"", "/"})	
	public ModelAndView formCustomer(){
		LOGGER.info("Start - formCustomer()");
		
		//Initialize global variable
		globalLoanRequestDto = new LoanRequestDto();
		
		ModelAndView mav = new ModelAndView(ViewConstants.VIEW_FORM_CUSTOMER);
		mav.addObject("customer", new CustomerDto());
		
		LOGGER.info("End - formCustomer()");
		return mav;
	}
	
	
	/**
	 * Add the customer.
	 *
	 * @param customerDto the customer dto
	 * @param bindingResult the binding result
	 * @return the model and view
	 * @throws CloneNotSupportedException 
	 */
	@PostMapping("/addCustomer")
	public ModelAndView addCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult bindingResult) throws CloneNotSupportedException{
		LOGGER.info("Start - addCustomer()");
		
		ModelAndView mav= new ModelAndView(ViewConstants.VIEW_FORM_CUSTOMER);
		
		//Validate errors CustomerDto
		if(bindingResult.hasErrors())
			return mav;
		else{					
			//Validate birthday customer JSR-310 not found with LocalTime
			if(customerDto.getBirthday().isAfter(LocalDate.now())){
				bindingResult.rejectValue("birthday", "error.date.future");
				return mav;
			}else if(customerDto.getBirthday().isAfter(LocalDate.now().minusYears(18)) 
					&& customerDto.getBirthday().isBefore(LocalDate.now()) || customerDto.getBirthday().isEqual(LocalDate.now())){
				bindingResult.rejectValue("birthday", "error.birthday.young");
				return mav;
			}else if(customerDto.getBirthday().isBefore(LocalDate.now().minusYears(110))){
				bindingResult.rejectValue("birthday", "error.birthday.dead");
				return mav;
			}
			//Validate amount
			if(customerDto.getAmount() <= 0l){
				bindingResult.rejectValue("amount", "error.amount.negative");
				return mav;
			}
			
			//Validation OK
			// Loan Configuration			
			globalLoanRequestDto.setLoan(getLoanDtoDefault());
			globalLoanRequestDto.setAmount(customerDto.getAmount());
			
			//Save customer in global variable
			globalLoanRequestDto.setCustomer(customerDto);
					
			//Set score according some rules
			try {
				globalLoanRequestDto = scoreService.setScore(globalLoanRequestDto.toEntity()).toDto();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				globalLoanRequestDto.setStatus(ErrorConstants.ERROR_GETTING_SCORE);
			}
			System.out.println(globalLoanRequestDto);
			
			//save datas in DB
			customerService.add(globalLoanRequestDto.getCustomer().toEntity());
			loanRequestService.add(globalLoanRequestDto.toEntity());
			
			//Show customers
			LOGGER.info("End - addCustomer() - Result OK");
			return new ModelAndView("redirect:/lap/showAllCustomers");
		}
	
	}
	
	/**
	 * Show all customers.
	 *
	 * @return the model and view
	 */
	@GetMapping("/showAllCustomers")
	public ModelAndView showAllCustomers(){
		LOGGER.info("Start - showAllCustomers()");
		
		ModelAndView mav = new ModelAndView(ViewConstants.VIEW_SHOW_CUSTOMERS);
		
		//get all loanRequest
		List<LoanRequest> listEntity = loanRequestService.findAllByOrderByCustomerAscStatusAsc();
		List<LoanRequestDto> listLRDto = new ArrayList<>();
		
		//Convert to dto object
		for(LoanRequest entity : listEntity){
			listLRDto.add(entity.toDto());
		}
		
		mav.addObject("listLoanRequest", listLRDto);
		
		LOGGER.info("End - showAllCustomers()");
		return mav;
	}
	
	//Get loan default
	private LoanDto getLoanDtoDefault(){
		return loanService.findAll().get(0).toDto();
	}
}
