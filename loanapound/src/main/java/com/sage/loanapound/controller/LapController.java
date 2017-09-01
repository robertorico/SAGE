package com.sage.loanapound.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.sage.loanapound.dto.LoanCustomerDto;
import com.sage.loanapound.dto.LoanDto;
import com.sage.loanapound.entity.Loan;
import com.sage.loanapound.entity.LoanCustomer;
import com.sage.loanapound.service.CustomerService;
import com.sage.loanapound.service.LoanCustomerService;
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
	@Qualifier("loanCustomerService")
	LoanCustomerService loanCustomerService;

	
	/*********************
	 *  
	 * 	GLOBAL VARIABLES
	 * 
	 *********************/
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LapController.class);	
	
	/** The loan customer dto. */
	private LoanCustomerDto globalLoanCustomerDto;
	
	/** The list loan. */
	private List<LoanDto> listLoan = new ArrayList<>();
	
	private Set<String> listTypeLoan = new HashSet<>();
	
	/** The int error*/
	private int error;
	
	
	/*********************
	 *  
	 * 		METHODS
	 * 
	 *********************/
	
	/**
	 * Show Form select loans.
	 *
	 * @return the model and view
	 */
	@GetMapping({"", "/"})	
	public ModelAndView formLoans(){
		LOGGER.info("Start - showLoans()" );
		
		//Initialize global dto
		if(globalLoanCustomerDto == null)
			globalLoanCustomerDto = new LoanCustomerDto();
		
		//Create list of loan, just one call to database
		if(listLoan.isEmpty())
			for(Loan entity : loanService.findAllByOrderByTypeAsc()){
				listLoan.add(entity.toDto());
				listTypeLoan.add(entity.getType());
			}
	
		
		
		//Show list of loans
		ModelAndView mav = new ModelAndView(ViewConstants.VIEW_FORM_SELECT_LOAN);
		mav.addObject("loanCustomer", globalLoanCustomerDto);
		mav.addObject("listLoans", listLoan);
		mav.addObject("listTypeLoan", listTypeLoan);
		mav.addObject("error", error);
		
		LOGGER.info("End - showLoans()");
		return mav;
	}
	
	
	/**
	 * Select loan.
	 *
	 * @param loanCustomer the loan customer dto
	 * @return the string
	 */
	@PostMapping("/selectLoan")
	public ModelAndView selectLoan(@ModelAttribute("loanCustomer") LoanCustomerDto loanCustomer){
		LOGGER.info("Start - selectLoan() " + loanCustomer);	
		
		ModelAndView mav = new ModelAndView();
		
		//Save values in global
		this.globalLoanCustomerDto = loanCustomer;
		
		//Validate loan selected
		System.out.println("OOOOOOOOOOJO" + globalLoanCustomerDto.getLoan().getType());
		if(globalLoanCustomerDto.getLoan().getId() == 0){
			this.error = ErrorConstants.ERROR_LOAN_NOT_SELECTED;
		}
		else{
			//Search and save the loan
			globalLoanCustomerDto.setLoan(listLoan.stream().filter(
					loan -> loan.getId() == globalLoanCustomerDto.getLoan().getId())
					.collect(Collectors.toList()).get(0));
			
			//Validate the fields
			if(globalLoanCustomerDto.getLoan().getAmountMin() <= globalLoanCustomerDto.getAmount() 
					&& globalLoanCustomerDto.getLoan().getAmountMax() >= globalLoanCustomerDto.getAmount()
					&& globalLoanCustomerDto.getLoan().getMonthMin() <= globalLoanCustomerDto.getMonths() 
					&& globalLoanCustomerDto.getLoan().getMonthMax() >= globalLoanCustomerDto.getMonths()){
				
				mav.setViewName("redirect:/lap/formCustomer");
				//Reset error variable
				this.error = 0;
				
				LOGGER.info("End - selectLoan() - Result: " + this.globalLoanCustomerDto);
				return mav;
			}
			else //Validation KO		
				this.error = ErrorConstants.ERROR_NOT_VALIDATE;	
		}
		
		//If any error return same page
		mav.setViewName("redirect:/lap");
		LOGGER.info("End - selectLoan() - Result = KO");
		return mav;
	}
	
	
	/**
	 * Show Form customer.
	 *
	 * @return the model and view
	 */
	@GetMapping("/formCustomer")
	public ModelAndView formCustomer(){
		LOGGER.info("Start - formCustomer()");
		
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
		LOGGER.info("Start - addCustomer() bindingResult " + bindingResult.getAllErrors());
		
		ModelAndView mav= new ModelAndView(ViewConstants.VIEW_FORM_CUSTOMER);
		
		//Validate errors CustomerDto
		if(bindingResult.hasErrors())
			return mav;
		else{			
			
			//Validate birthday customer
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
			
			//Validation OK
			//Save customer in global variable
			globalLoanCustomerDto.setCustomer(customerDto);
			
			//Set score according some rules
			globalLoanCustomerDto = scoreService.setScore(globalLoanCustomerDto.toEntity()).toDto();
			System.out.println(globalLoanCustomerDto);
			
			//save customer in table loanCustomer
			customerService.add(globalLoanCustomerDto.getCustomer().toEntity());
			loanCustomerService.add(globalLoanCustomerDto.toEntity());
			
			//SHOW ALL CUSTOMERS AND SCORE
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
		//Restart global datas
		globalLoanCustomerDto = null;
		listLoan = new ArrayList<>(); 
		
		ModelAndView mav = new ModelAndView(ViewConstants.VIEW_SHOW_CUSTOMERS);
		List<LoanCustomerDto> listLCDto = new ArrayList<>();
		for(LoanCustomer entity : loanCustomerService.findAllByOrderByCustomerAscStatusAsc()){
			listLCDto.add(entity.toDto());
		}
		mav.addObject("listLoanCustomers", listLCDto);
		LOGGER.info("End - showAllCustomers()");
		return mav;
	}
}
