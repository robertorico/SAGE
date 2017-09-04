package com.sage.loanapound.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.sage.loanapound.dto.ConfigProviderDto;

/**
 * The Class ConfigProvider.
 */
@Entity
@Table(name = "configProvider")
public class ConfigProvider implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The type ws. */
	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeWs", referencedColumnName = "name", nullable = false)
	private TypeWs typeWs;

	/** The provider score. */
	@EmbeddedId
	@ManyToOne
	@JoinColumn(name = "providerScore", referencedColumnName = "id", nullable = false)
	private ProviderScore providerScore;

	/** The url. */
	@Column(name = "url", length = 128, nullable = true)
	private String url;

	/** The user. */
	@Column(name = "user", length = 64, nullable = true)
	private String user;

	/** The password. */
	@Column(name = "password", length = 128, nullable = true)
	private String password;

	/**
	 * Gets the type ws.
	 *
	 * @return the typeWs
	 */
	public TypeWs getTypeWs() {
		return typeWs;
	}

	/**
	 * Gets the provider score.
	 *
	 * @return the providerScore
	 */
	public ProviderScore getProviderScore() {
		return providerScore;
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
	 * Sets the type ws.
	 *
	 * @param typeWs
	 *            the typeWs to set
	 */
	public void setTypeWs(TypeWs typeWs) {
		this.typeWs = typeWs;
	}

	/**
	 * Sets the provider score.
	 *
	 * @param providerScore
	 *            the providerScore to set
	 */
	public void setProviderScore(ProviderScore providerScore) {
		this.providerScore = providerScore;
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

	/**
	 * To dto.
	 *
	 * @return the config provider dto
	 */
	public ConfigProviderDto toDto() {
		return new ModelMapper().map(this, ConfigProviderDto.class);
	}
}
