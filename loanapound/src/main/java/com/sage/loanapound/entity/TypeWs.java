package com.sage.loanapound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.dto.TypeWSDto;

/**
 * The Class TypeWS.
 */
@Entity
@Table(name = "typeWs")
public class TypeWs {

	/** The name. */
	@Id
	@Column(name = "name", unique = true, nullable = false, length = 4)
	private String name;

	/**
	 * Instantiates a new type ws.
	 */
	public TypeWs() {}
	
	/**
	 * Instantiates a new type ws.
	 *
	 * @param name the name
	 */
	public TypeWs(String name) {
		super();
		this.name = name;
	}

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

	/**
	 * To dto.
	 *
	 * @return the type ws dto
	 */
	public TypeWSDto toDto() {
		return new ModelMapper().map(this, TypeWSDto.class);
	}
}
