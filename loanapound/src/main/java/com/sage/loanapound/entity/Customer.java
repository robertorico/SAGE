package com.sage.loanapound.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.sage.loanapound.dto.CustomerDto;

/**
 * The Class Customer.
 */
@Entity
@Table(name = "customer")
public class Customer {

	/** The id. */
	@Id
	@NotNull
	@Column(name = "id", unique = true, nullable = false, length = 10)
	private String id;

	/** The name. */
	@NotNull
	@Column(name = "name", nullable = false, length = 45)
	private String name;

	/** The surname. */
	@Column(name = "lastname", nullable = false, length = 45)
	private String lastname;

	/** The birthday. */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "birthday", nullable = false)
	private LocalDate birthday;

	/** The email. */
	@Column(name = "email", nullable = false, length = 128)
	private String email;

	/** The phone. */
	@Column(name = "phone", nullable = false, length = 16)
	private String phone;

	/** The country. */
	@Column(name = "country", nullable = false, length = 20)
	private String country;

	/** The street. */
	@Column(name = "name_street", nullable = false, length = 45)
	private String street;

	/** The number. */
	@Column(name = "number", nullable = false, length = 6)
	private String number;

	/** The postalcode. */
	@Column(name = "postalCode", nullable = false, length = 8)
	private String postalcode;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return the postalcode
	 */
	public String getPostalcode() {
		return postalcode;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @param postalcode
	 *            the postalcode to set
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", lastname=" + lastname + ", birthday=" + birthday
				+ ", email=" + email + ", phone=" + phone + ", country=" + country + ", street=" + street + ", number="
				+ number + ", postalcode=" + postalcode + "]";
	}

	/**
	 * To dto.
	 *
	 * @return the customer dto
	 */
	public CustomerDto toDto() {
		return new ModelMapper().map(this, CustomerDto.class);
	}
}
