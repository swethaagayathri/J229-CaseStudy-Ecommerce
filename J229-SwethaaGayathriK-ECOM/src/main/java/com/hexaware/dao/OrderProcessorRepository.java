package com.hexaware.dao;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;
import com.hexaware.exception.CustomerNotFoundException;
import com.hexaware.exception.OrderNotFoundException;
import com.hexaware.exception.ProductNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * Interface defining methods for an OrderProcessorRepositoryImpl.
 */
public interface OrderProcessorRepository {

	/**
	 * Creates a new product.
	 * 
	 * @param product The product to be created.
	 * @return True if the product is created successfully, false otherwise.
	 */
	boolean createProduct(Product product);

	/**
	 * Creates a new customer.
	 * 
	 * @param customer The customer to be created.
	 * @return True if the customer is created successfully, false otherwise.
	 */
	boolean createCustomer(Customer customer);

	/**
	 * Deletes a product.
	 * 
	 * @param productId The ID of the product to be deleted.
	 * @return True if the product is deleted successfully, false otherwise.
	 * @throws ProductNotFoundException if the product is not found.
	 */
	boolean deleteProduct(long productId) throws ProductNotFoundException;

	/**
	 * Deletes a customer.
	 * 
	 * @param customerId The ID of the customer to be deleted.
	 * @return True if the customer is deleted successfully, false otherwise.
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	boolean deleteCustomer(long customerId) throws CustomerNotFoundException;

	/**
	 * Adds a product to the cart.
	 * 
	 * @param customer The customer adding the product.
	 * @param product  The product to be added to the cart.
	 * @param quantity The quantity of the product to be added.
	 * @return True if the product is added to the cart successfully, false
	 *         otherwise.
	 */
	boolean addToCart(Customer customer, Product product, int quantity);

	/**
	 * Removes a product from the cart.
	 * 
	 * @param customer The customer removing the product.
	 * @param product  The product to be removed from the cart.
	 * @return True if the product is removed from the cart successfully, false
	 *         otherwise.
	 * @throws ProductNotFoundException  if the product is not found.
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	boolean removeFromCart(Customer customer, Product product)
			throws ProductNotFoundException, CustomerNotFoundException;

	/**
	 * Retrieves all products from the cart for a given customer.
	 * 
	 * @param customer The customer whose cart is being retrieved.
	 * @return List of products in the cart for the given customer.
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	List<Product> getAllFromCart(Customer customer) throws CustomerNotFoundException;

	/**
	 * Places an order for the customer.
	 * 
	 * @param customer              The customer placing the order.
	 * @param productsAndQuantities The products and their quantities to be ordered.
	 * @param shippingAddress       The shipping address for the order.
	 * @return True if the order is placed successfully, false otherwise.
	 */
	boolean placeOrder(Customer customer, List<Map<Product, Integer>> productsAndQuantities, String shippingAddress);

	/**
	 * Retrieves orders for a given customer.
	 * 
	 * @param customerId The ID of the customer whose orders are being retrieved.
	 * @return List of orders for the given customer.
	 * @throws OrderNotFoundException if no orders are found for the customer.
	 */
	List<Map<Product, Integer>> getOrdersByCustomer(long customerId) throws OrderNotFoundException;

	/**
	 * Retrieves all products.
	 * 
	 * @return List of all products.
	 */
	List<Product> getAllProducts();
}
