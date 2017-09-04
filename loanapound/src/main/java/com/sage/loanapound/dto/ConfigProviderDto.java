package com.sage.loanapound.dto;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.entity.ConfigProvider;

/**
 * The Class ConfigProvider.
 */
public class ConfigProviderDto {

	/** The type ws dto. */
	private TypeWSDto typeWsDto;

	/** The provider score dto. */
	private ProviderScoreDto providerScoreDto;

	/** The url. */
	private String url;

	/** The user. */
	private String user;

	/** The password. */
	private String password;

	/**
	 * Gets the type ws dto.
	 *
	 * @return the typeWsDto
	 */
	public TypeWSDto getTypeWsDto() {
		return typeWsDto;
	}

	/**
	 * Gets the provider score dto.
	 *
	 * @return the providerScoreDto
	 */
	public ProviderScoreDto getProviderScoreDto() {
		return providerScoreDto;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the type ws dto.
	 *
	 * @param typeWsDto
	 *            the typeWsDto to set
	 */
	public void setTypeWsDto(TypeWSDto typeWsDto) {
		this.typeWsDto = typeWsDto;
	}

	/**
	 * Sets the provider score dto.
	 *
	 * @param providerScoreDto
	 *            the providerScoreDto to set
	 */
	public void setProviderScoreDto(ProviderScoreDto providerScoreDto) {
		this.providerScoreDto = providerScoreDto;
	}

	/**
	 * Sets the url.
	 *
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfigProviderDto [typeWsDto=" + typeWsDto + ", providerScoreDto=" + providerScoreDto + ", url=" + url
				+ ", user=" + user + ", password=" + password + "]";
	}

	/**
	 * To entity.
	 *
	 * @return the config provider
	 */
	public ConfigProvider toEntity() {
		return new ModelMapper().map(this, ConfigProvider.class);
	}
}
