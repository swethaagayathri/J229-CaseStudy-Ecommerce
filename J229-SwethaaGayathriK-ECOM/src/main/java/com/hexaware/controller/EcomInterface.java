package com.hexaware.controller;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.ProductNotFoundException;

import java.util.List;

/**
 * Interface defining methods for an EcomController.
 */
public interface EcomInterface {

	/**
	 * Creates a new product.
	 */
	void createProduct();

	/**
	 * Creates a new customer.
	 */
	void createCustomer();

	/**
	 * Deletes a product.
	 * 
	 * @throws ProductNotFoundException if the product is not found.
	 */
	void deleteProduct() throws ProductNotFoundException;

	/**
	 * Deletes a customer.
	 * 
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	void deleteCustomer() throws CustomerNotFoundException;

	/**
	 * Adds a product to the cart.
	 */
	void addToCart();

	/**
	 * Removes a product from the cart.
	 * 
	 * @throws ProductNotFoundException  if the product is not found.
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	void removeFromCart() throws ProductNotFoundException, CustomerNotFoundException;

	/**
	 * Retrieves all products from the cart for a given customer.
	 * 
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	void getAllFromCart() throws CustomerNotFoundException;

	/**
	 * Places an order for the customer.
	 */
	void placeOrder();

	/**
	 * Retrieves orders for a given customer.
	 * 
	 * @throws OrderNotFoundException if no orders are found for the customer.
	 */
	void getOrdersByCustomer() throws OrderNotFoundException;
}
