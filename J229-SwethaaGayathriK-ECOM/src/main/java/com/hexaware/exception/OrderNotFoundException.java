package com.hexaware.exception;

/**
 * Exception for handling cases when an order was not found.
 */
public class OrderNotFoundException extends Exception {

	/**
	 * Constructs a new OrderNotFoundException with the specified detail message.
	 * 
	 * @param message detail message.
	 */
	public OrderNotFoundException(String message) {
		super(message);
	}
}
