package com.hexaware.exception;

/**
 * Exception for handling cases when a customer was not found.
 */
public class CustomerNotFoundException extends Exception {

	/**
	 * Constructs a new CustomerNotFoundException with the specified detail message.
	 * 
	 * @param message the detail message.
	 */
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
