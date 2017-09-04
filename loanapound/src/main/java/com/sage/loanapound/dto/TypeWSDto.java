package com.sage.loanapound.dto;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.entity.TypeWs;

/**
 * The Class TypeWSDto.
 */
public class TypeWSDto {

	/** The name. */
	private String name;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
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
		return "TypeWSDto [name=" + name + "]";
	}

	/**
	 * To entity.
	 *
	 * @return the type ws
	 */
	public TypeWs toEntity() {
		return new ModelMapper().map(this, TypeWs.class);
	}
}
