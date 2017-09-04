package com.sage.loanapound.dto;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.entity.TypeLoan;

/**
 * The Class TypeLoanDto.
 */
public class TypeLoanDto {

	/** The name. */
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TypeLoanDto [name=" + name + "]";
	}

	/**
	 * To entity.
	 * @return the type loan
	 */
	public TypeLoan toEntity() {
		return new ModelMapper().map(this, TypeLoan.class);
	}

}
