package com.sage.loanapound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sage.loanapound.dto.ProviderScoreDto;

/**
 * The Class ProviderScore.
 */
@Entity
@Table(name = "provider_score")
public class ProviderScore {

	/** The name. */
	@Id
	@NotNull
	@Column(name = "name", unique = true, nullable = false, length = 64)
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
		return "ProviderScore [name=" + name + "]";
	}

	/**
	 * To dto.
	 *
	 * @return the provider score dto
	 */
	public ProviderScoreDto toDto() {
		ProviderScoreDto dto = new ProviderScoreDto();
		dto.setName(name);
		return dto;
	}

}
