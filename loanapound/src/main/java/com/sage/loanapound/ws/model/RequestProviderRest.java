package com.sage.loanapound.ws.model;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestProviderRest.
 */
public class RequestProviderRest {
	
	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The surname. */
	private String surname;
	
	/**
	 * Instantiates a new request provider rest.
	 */
	RequestProviderRest(){}
	
	/**
	 * Instantiates a new request provider rest.
	 *
	 * @param id the id
	 * @param name the name
	 * @param surname the surname
	 */
	public RequestProviderRest(String id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(String id) {
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
	 * Sets the surname.
	 *
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
