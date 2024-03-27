package com.hexaware.entity;

/**
 * Represents a Customer entity with its attributes.
 */
public class Customer {
	private long customerId;
	private String name;
	private String email;
	private String password;

	/**
	 * Constructs a Customer object with the given parameters.
	 * 
	 * @param customerId The ID of the customer.
	 * @param name       The name of the customer.
	 * @param email      The email address of the customer.
	 * @param password   The password of the customer.
	 */
	public Customer(long customerId, String name, String email, String password) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Retrieves the ID of the customer.
	 * 
	 * @return The customer ID.
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the ID of the customer.
	 * 
	 * @param customerId The customer ID to set.
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * Retrieves the name of the customer.
	 * 
	 * @return The customer's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the customer.
	 * 
	 * @param name The customer's name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the email address of the customer.
	 * 
	 * @return The customer's email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the customer.
	 * 
	 * @param email The customer's email address to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the password of the customer.
	 * 
	 * @return The customer's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the customer.
	 * 
	 * @param password The customer's password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns a string representation of the Customer object.
	 * 
	 * @return A string representation containing the customer's details.
	 */
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", password=" + password
				+ "]";
	}
}
