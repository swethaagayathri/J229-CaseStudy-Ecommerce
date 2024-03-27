package com.hexaware.entity;

/**
 * Represents a Cart entity with its attributes.
 */
public class Cart {
	private long cartId;
	private long customerId;
	private long productId;
	private int quantity;

	/**
	 * Constructs a Cart object with the given parameters.
	 * 
	 * @param cartId     The ID of the cart.
	 * @param customerId The ID of the customer associated with the cart.
	 * @param productId  The ID of the product added to the cart.
	 * @param quantity   The quantity of the product in the cart.
	 */
	public Cart(long cartId, long customerId, long productId, int quantity) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
	}

	/**
	 * Retrieves the ID of the cart.
	 * 
	 * @return The cart ID.
	 */
	public long getCartId() {
		return cartId;
	}

	/**
	 * Sets the ID of the cart.
	 * 
	 * @param cartId The cart ID to set.
	 */
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	/**
	 * Retrieves the ID of the customer associated with the cart.
	 * 
	 * @return The customer ID.
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the ID of the customer associated with the cart.
	 * 
	 * @param customerId The customer ID to set.
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * Retrieves the ID of the product added to the cart.
	 * 
	 * @return The product ID.
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * Sets the ID of the product added to the cart.
	 * 
	 * @param productId The product ID to set.
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * Retrieves the quantity of the product in the cart.
	 * 
	 * @return The quantity.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the product in the cart.
	 * 
	 * @param quantity The quantity to set.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Returns a string representation of the Cart object.
	 * 
	 * @return A string representation containing the cart's details.
	 */
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", productId=" + productId + ", quantity="
				+ quantity + "]";
	}
}
