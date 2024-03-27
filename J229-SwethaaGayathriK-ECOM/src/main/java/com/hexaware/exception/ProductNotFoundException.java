package com.hexaware.exception;

/**
 * Exception handling cases when a product was not found.
 */
public class ProductNotFoundException extends Exception {

	/**
	 * Constructs a new ProductNotFoundException with the specified detail message.
	 * 
	 * @param message detail message.
	 */
	public ProductNotFoundException(String message) {
		super(message);
	}
}
