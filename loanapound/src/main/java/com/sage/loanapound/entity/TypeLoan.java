package com.sage.loanapound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.dto.TypeLoanDto;

/**
 * The Class TypeLoan.
 */
@Entity
@Table(name = "typeLoan")
public class TypeLoan {

	/** The name. */
	
	@Id
	@Column(name = "name", unique = true, nullable = false, length = 20)
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TypeLoan [name=" + name + "]";
	}
	
	/**
	 * To dto.
	 *
	 * @return the type loan dto
	 */
	public TypeLoanDto toDto() {
		return new ModelMapper().map(this, TypeLoanDto.class);
	}

}
