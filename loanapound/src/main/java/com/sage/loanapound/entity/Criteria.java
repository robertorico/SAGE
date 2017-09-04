package com.sage.loanapound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class TypeLoan.
 */
@Entity
@Table(name = "criteria")
public class Criteria {

	/** The name. */
	
	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	/** The name. */
	@Column(name = "name", nullable = false, length = 32)
	private String name;
	
	/** The value. */
	@Column(name = "value", nullable = false, length = 32)
	private int value;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}	
}
