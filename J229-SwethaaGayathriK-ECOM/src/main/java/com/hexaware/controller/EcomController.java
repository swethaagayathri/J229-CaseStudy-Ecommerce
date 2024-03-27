package com.hexaware.controller;

import com.hexaware.dao.OrderProcessorRepository;
import com.hexaware.dao.OrderProcessorRepositoryImpl;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represents the controller for the E-commerce application.
 */
public class EcomController implements EcomInterface {
	private final OrderProcessorRepository orderProcessor = new OrderProcessorRepositoryImpl();
	Scanner sc = new Scanner(System.in);

	/**
	 * Creates a new product.
	 */
	public void createProduct() {
		System.out.println("Enter product details:");
		System.out.println("Product ID:");
		long productId = sc.nextLong();
		System.out.println("Name:");
		String name = sc.next();
		System.out.println("Price:");
		double price = sc.nextDouble();
		System.out.println("Description:");
		String description = sc.next();
		System.out.println("Stock Quantity:");
		int stockQuantity = sc.nextInt();

		Product product = new Product(productId, name, price, description, stockQuantity);
		boolean success = orderProcessor.createProduct(product);
		if (success) {
			System.out.println("Product created successfully.");
		} else {
			System.out.println("Failed to create product.");
		}

	}

	/**
	 * Creates a new customer.
	 */
	public void createCustomer() {

		System.out.println("Enter customer details:");
		System.out.println("Customer ID:");
		long customerId = sc.nextLong();
		System.out.println("Name:");
		String name = sc.next();
		System.out.println("Email:");
		String email = sc.next();
		System.out.println("Password:");
		String password = sc.next();

		Customer customer = new Customer(customerId, name, email, password);
		boolean success = orderProcessor.createCustomer(customer);
		if (success) {
			System.out.println("Customer created successfully.");
		} else {
			System.out.println("Failed to create customer.");
		}

	}

	/**
	 * Deletes a product.
	 * 
	 * @throws ProductNotFoundException if the product is not found.
	 */
	public void deleteProduct() throws ProductNotFoundException {
		System.out.println("Enter product ID to delete:");
		long productId = sc.nextLong();
		boolean success = orderProcessor.deleteProduct(productId);
		if (success) {
			System.out.println("Product deleted successfully.");
		} else {
			System.out.println("Failed to delete product.");
		}
	}

	/**
	 * Deletes a customer.
	 * 
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	public void deleteCustomer() throws CustomerNotFoundException {
		System.out.println("Enter customer ID to delete:");
		long customerId = sc.nextLong();
		boolean success = orderProcessor.deleteCustomer(customerId);
		if (success) {
			System.out.println("Customer deleted successfully.");
		} else {
			System.out.println("Failed to delete customer.");
		}

	}

	/**
	 * Adds a product to the cart.
	 */
	public void addToCart() {
		System.out.println("Enter customer ID:");
		long customerId = sc.nextLong();
		System.out.println("Enter product ID to add to cart:");
		long productId = sc.nextLong();
		System.out.println("Enter quantity:");
		int quantity = sc.nextInt();
		Customer customer = new Customer(customerId, "", "", "");
		Product product = new Product(productId, "", 0.0, "", 0);
		boolean success = orderProcessor.addToCart(customer, product, quantity);
		if (success) {
			System.out.println("Product added to cart successfully.");
		} else {
			System.out.println("Failed to add product to cart.");
		}

	}

	/**
	 * Removes a product from the cart.
	 * 
	 * @throws ProductNotFoundException  if the product is not found.
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	public void removeFromCart() throws ProductNotFoundException, CustomerNotFoundException {
		System.out.println("Enter customer ID:");
		long customerId = sc.nextLong();
		System.out.println("Enter product ID to remove from cart:");
		long productId = sc.nextLong();
		Customer customer = new Customer(customerId, "", "", "");
		Product product = new Product(productId, "", 0.0, "", 0);
		boolean success = orderProcessor.removeFromCart(customer, product);
		if (success) {
			System.out.println("Product removed from cart successfully.");
		} else {
			System.out.println("Failed to remove product from cart.");
		}
	}

	/**
	 * Retrieves all products from the cart for a given customer.
	 * 
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	public void getAllFromCart() throws CustomerNotFoundException {
		System.out.println("Enter customer ID:");
		int customerId = sc.nextInt();
		Customer customer = new Customer(customerId, "", "", "");
		List<Product> cartProducts = orderProcessor.getAllFromCart(customer);
		System.out.println("Products in the cart for customer ID " + customerId + " :");
		for (Product product : cartProducts) {
			System.out.println(product);
		}

	}

	/**
	 * Places an order for the customer.
	 */
	public void placeOrder() {
		List<Product> allProducts = orderProcessor.getAllProducts();
		System.out.println("Available Products:");
		for (Product product : allProducts) {
			System.out.println(product);
		}
		System.out.println("Enter customer ID:");
		long customerId = sc.nextLong();
		Customer customer = new Customer(customerId, "", "", "");

		System.out.println("Enter number of products to order:");
		int numProducts = sc.nextInt();
		List<Map<Product, Integer>> productsQuantities = new ArrayList<>();
		for (int i = 0; i < numProducts; i++) {
			Map<Product, Integer> productQuantityMap = new HashMap<>();
			System.out.println("Enter product ID:");
			long productId = sc.nextLong();
			Product product = new Product(productId, "", 0.0, "", 0);
			System.out.println("Enter quantity:");
			int quantity = sc.nextInt();
			productQuantityMap.put(product, quantity);
			productsQuantities.add(productQuantityMap);
		}

		sc.nextLine();
		System.out.println("Enter shipping address:");
		String shippingAddress = sc.nextLine();

		boolean success = orderProcessor.placeOrder(customer, productsQuantities, shippingAddress);
		if (success) {
			System.out.println("Order placed successfully.");
		} else {
			System.out.println("Failed to place order.");
		}

	}

	/**
	 * Retrieves orders for a given customer.
	 * 
	 * @throws OrderNotFoundException if no orders are found for the customer.
	 */
	@Override
	public void getOrdersByCustomer() throws OrderNotFoundException {
		System.out.println("Enter customer ID:");
		long customerId = sc.nextLong();
		Customer customer = new Customer(customerId, "", "", "");
		List<Map<Product, Integer>> orders = orderProcessor.getOrdersByCustomer(customerId);
		if (orders.isEmpty()) {
			System.out.println("No orders found for customer with ID " + customerId);
		} else {
			System.out.println("Orders for customer with ID " + customerId + ":");
			for (Map<Product, Integer> order : orders) {
				System.out.println("Order:");
				for (Map.Entry<Product, Integer> entry : order.entrySet()) {
					Product product = entry.getKey();
					int quantity = entry.getValue();
					System.out.println("Product: " + product.getName() + ", Quantity: " + quantity);
				}
				System.out.println("-------------------------------------");
			}
		}

	}
}
