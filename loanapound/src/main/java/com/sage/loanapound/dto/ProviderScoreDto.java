package com.sage.loanapound.dto;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.entity.ProviderScore;

/**
 * The Class ProviderScoreDto.
 */
public class ProviderScoreDto {

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
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProviderScoreDto [name=" + name + "]";
	}

	/**
	 * To entity.
	 *
	 * @return the provider score
	 */
	public ProviderScore toEntity() {
		return new ModelMapper().map(this, ProviderScore.class);
	}
}
