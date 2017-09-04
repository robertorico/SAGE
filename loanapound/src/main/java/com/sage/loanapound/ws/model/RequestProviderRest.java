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

	/** The lastname. */
	private String lastname;
	
	/**
	 * Instantiates a new request provider rest.
	 */
	RequestProviderRest(){}
	
	/**
	 * Instantiates a new request provider rest.
	 *
	 * @param id the id
	 * @param name the name
	 * @param lastname the lastname
	 */
	public RequestProviderRest(String id, String name, String lastname) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
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
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
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
	 * Sets the lastname.
	 *
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
