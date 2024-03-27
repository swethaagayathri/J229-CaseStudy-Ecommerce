package com.hexaware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.dao.OrderProcessorRepository;
import com.hexaware.dao.OrderProcessorRepositoryImpl;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class contains test cases for the OrderProcessorRepositoryImpl class.
 */
public class OrderProcessorRepositoryImplTest {

	private OrderProcessorRepository orderProcessor;
	private Customer customer;
	private Product product;
	private Product product1;
	private int quantity;
	private List<Map<Product, Integer>> productsQuantities;
	private String shippingAddress;
	private long invalidCustomerId;
	private long invalidProductId;

	/**
	 * Set up initial state before executing each test case.
	 */
	@Before
	public void setUp() {
		orderProcessor = new OrderProcessorRepositoryImpl();
		customer = new Customer(1, "SNEHA", "sneha@gmail.com", "sneha123");
		product = new Product(1001, "Sample Product", 100.0, "Sample Description", 10);
		product1 = new Product(21, "Sample Product", 100.0, "Sample Description", 10);
		quantity = 2;
		productsQuantities = new ArrayList<>();
		productsQuantities.add(Map.of(product, quantity));
		shippingAddress = "Paris";
		invalidCustomerId = 999;
		invalidProductId = 1999;
	}

	/**
	 * Test case for creating a product.
	 */
	@Test
	public void testCreateProduct() {
		assertTrue("Product should be created successfully", orderProcessor.createProduct(product1));
	}

	/**
	 * Test case for adding a product to the cart.
	 */
	@Test
	public void testAddToCart() {
		assertTrue("Product should be added to the cart successfully",
				orderProcessor.addToCart(customer, product, quantity));
	}

	/**
	 * Test case for placing an order.
	 */
	@Test
	public void testPlaceOrder() {
		assertTrue("Order should be placed successfully",
				orderProcessor.placeOrder(customer, productsQuantities, shippingAddress));
	}

	/**
	 * Test case for handling CustomerNotFoundException.
	 */
	@Test(expected = CustomerNotFoundException.class)
	public void testCustomerNotFoundException() throws CustomerNotFoundException {
		orderProcessor.deleteCustomer(invalidCustomerId);
	}

	/**
	 * Test case for handling ProductNotFoundException.
	 */
	@Test(expected = ProductNotFoundException.class)
	public void testProductNotFoundException() throws ProductNotFoundException {
		orderProcessor.deleteProduct(invalidProductId);
	}

	/**
	 * Cleans up resources after executing each test case.
	 */
	@After
	public void tearDown() {
		orderProcessor = null;
		customer = null;
		product = null;
		productsQuantities = null;
		shippingAddress = null;
	}
}
