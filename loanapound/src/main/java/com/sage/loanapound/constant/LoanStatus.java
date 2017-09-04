package com.sage.loanapound.constant;

/**
 * The Enum LoanStatus.
 */
public enum LoanStatus {
	
	APPROVED("Approved"), REJECTED("Rejected"), PENDING("Pending");
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new type WS.
	 *
	 * @param name the name
	 */
	LoanStatus(String name){
		this.name = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return this.name;
	}
}
