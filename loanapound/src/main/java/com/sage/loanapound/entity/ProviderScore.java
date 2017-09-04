package com.sage.loanapound.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;

import com.sage.loanapound.dto.ProviderScoreDto;

/**
 * The Class ProviderScore.
 */
@Entity
@Table(name = "providerScore")
public class ProviderScore {

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	/** The name. */
	@Column(name = "name", nullable = false, length = 64)
	private String name;

	/** The config provider. */
	@OneToMany(mappedBy = "providerScore")
	private Set<ConfigProvider> configProvider;

	/** The criterias. */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "configCriteria", joinColumns = @JoinColumn(name = "providerId"), inverseJoinColumns = @JoinColumn(name = "criteriaId"))
	public List<Criteria> criterias;

	/**
	 * Instantiates a new provider score.
	 */
	public ProviderScore() {
		Hibernate.initialize(criterias);
	}

	/**
	 * Gets the config provider.
	 *
	 * @return the configProvider
	 */
	public Set<ConfigProvider> getConfigProvider() {
		return configProvider;
	}

	/**
	 * Sets the config provider.
	 *
	 * @param configProvider
	 *            the configProvider to set
	 */
	public void setConfigProvider(Set<ConfigProvider> configProvider) {
		this.configProvider = configProvider;
	}

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
	 * Gets the criterias.
	 *
	 * @return the criterias
	 */
	public List<Criteria> getCriterias() {
		return criterias;
	}

	/**
	 * Sets the criterias.
	 *
	 * @param criterias
	 *            the criterias to set
	 */
	public void setCriterias(List<Criteria> criterias) {
		this.criterias = criterias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProviderScore [id=" + id + ", name=" + name + "]";
	}

	/**
	 * To dto.
	 *
	 * @return the provider score dto
	 */
	public ProviderScoreDto toDto() {
		return new ModelMapper().map(this, ProviderScoreDto.class);
	}
}
