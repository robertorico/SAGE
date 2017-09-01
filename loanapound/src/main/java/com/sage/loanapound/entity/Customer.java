package com.sage.loanapound.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
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
	@Column(name = "id", unique = true, nullable = false, length = 9)
	private String id;

	/** The name. */
	@NotNull
	@Column(name = "name", nullable = false, length = 45)
	private String name;

	/** The surname. */
	@NotNull
	@Column(name = "surname", nullable = false, length = 45)
	private String surname;

	/** The birthday. */
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "birthday", nullable = false)
	private LocalDate birthday;

	/** The email. */
	@NotNull
	@Email
	@Column(name = "email", nullable = false, length = 128)
	private String email;

	/** The phone. */
	@NotNull
	@Column(name = "phone", nullable = false, length = 15)
	private String phone;

	/** The country. */
	@NotNull
	@Column(name = "country", nullable = false, length = 20)
	private String country;

	/** The street. */
	@NotNull
	@Column(name = "name_street", nullable = false, length = 45)
	private String street;

	/** The number. */
	@NotNull
	@Column(name = "number", nullable = false, length = 6)
	private String number;

	/** The postalcode. */
	@NotNull
	@Column(name = "postalCode", nullable = false, length = 8)
	private String postalcode;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the idCard to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the birthday.
	 *
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * Sets the birthday.
	 *
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the street.
	 *
	 * @return the nameStreet
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street
	 *            the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Gets the postalcode.
	 *
	 * @return the postalCode
	 */
	public String getPostalcode() {
		return postalcode;
	}

	/**
	 * Sets the postalcode.
	 *
	 * @param postalcode
	 *            the postalCode to set
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
		return "CustomerEntity [idcard=" + id + ", name=" + name + ", surname=" + surname + ", birthday=" + birthday
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
