package com.sage.loanapound.dto;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.entity.ProviderScore;
import com.sage.loanapound.entity.TypeWs;

/**
 * The Class ProviderScoreDto.
 */
public class ProviderScoreDto {

	/** The id. */
	private int id;

	/** The name. */
	private String name;

	/** The url ws. */
	private String urlWs;

	/** The type WS. */
	private TypeWs typeWS;

	/** The description. */
	private String description;

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
	 * Gets the url ws.
	 *
	 * @return the urlWs
	 */
	public String getUrlWs() {
		return urlWs;
	}

	/**
	 * Gets the type WS.
	 *
	 * @return the typeWS
	 */
	public TypeWs getTypeWS() {
		return typeWS;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Sets the url ws.
	 *
	 * @param urlWs
	 *            the urlWs to set
	 */
	public void setUrlWs(String urlWs) {
		this.urlWs = urlWs;
	}

	/**
	 * Sets the type WS.
	 *
	 * @param typeWS
	 *            the typeWS to set
	 */
	public void setTypeWS(TypeWs typeWS) {
		this.typeWS = typeWS;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProviderScoreDto [id=" + id + ", name=" + name + ", urlWs=" + urlWs + ", typeWS=" + typeWS
				+ ", description=" + description + "]";
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
