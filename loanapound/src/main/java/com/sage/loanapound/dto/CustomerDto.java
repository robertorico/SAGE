package com.sage.loanapound.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.sage.loanapound.entity.Customer;

/**
 * The Class CustomerDto.
 */
public class CustomerDto {
	
	/** The id. */
	@NotEmpty @Length(max = 10)
	private String id;

	/** The name. */
	@NotEmpty @Length(max = 32)
	private String name;

	/** The surname. */
	@NotEmpty @Length(max = 32)
	private String surname;

	/** The birthday. */
	@NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") 
	private LocalDate birthday;

	/** The email. */
	@NotEmpty @Email
	private String email;

	/** The phone. */
	@NotEmpty @Length(max = 16)	
	@Pattern(regexp = "(\\+)?(\\s?\\d)*")
	private String phone;

	/** The country. */
	@NotEmpty @Length(max = 16)
	private String country;

	/** The street. */
	@NotEmpty @Length(max = 32)
	private String street;

	/** The number. */
	@NotEmpty @Length(max = 5)
	private String number;

	/** The postalcode. */
	@NotEmpty @Length(max = 8)
	private String postalcode;

	/**
	 * Instantiates a new customer dto.
	 */
	public CustomerDto() {
	}

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
	 * @param id            the idCard to set
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
	 * @param name            the name to set
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
	 * @param surname            the surname to set
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
	 * @param birthday            the birthday to set
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
	 * @param email            the email to set
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
	 * @param phone            the phone to set
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
	 * @param country            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street            the street to set
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
	 * @param number            the number to set
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
	 * @param postalCode            the postalCode to set
	 */
	public void setPostalcode(String postalCode) {
		this.postalcode = postalCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", name=" + name + ", surname=" + surname + ", birthday=" + birthday
				+ ", email=" + email + ", phone=" + phone + ", country=" + country + ", street=" + street + ", number="
				+ number + ", postalcode=" + postalcode + "]";
	}
	
	/**
	 * To entity.
	 *
	 * @return the customer
	 */
	public Customer toEntity(){
		return new ModelMapper().map(this, Customer.class);
	}
}
