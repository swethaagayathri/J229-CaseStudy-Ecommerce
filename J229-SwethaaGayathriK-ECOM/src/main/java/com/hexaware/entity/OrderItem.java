package com.hexaware.entity;

/**
 * Represents an OrderItem entity with its attributes.
 */
public class OrderItem {
	private long orderItemId;
	private long orderId;
	private long productId;
	private int quantity;

	/**
	 * Constructs an OrderItem object with the given parameters.
	 * 
	 * @param orderItemId The ID of the order item.
	 * @param orderId     The ID of the order to which the item belongs.
	 * @param productId   The ID of the product in the order item.
	 * @param quantity    The quantity of the product in the order item.
	 */
	public OrderItem(long orderItemId, long orderId, long productId, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}

	/**
	 * Retrieves the ID of the order item.
	 * 
	 * @return The order item ID.
	 */
	public long getOrderItemId() {
		return orderItemId;
	}

	/**
	 * Sets the ID of the order item.
	 * 
	 * @param orderItemId The order item ID to set.
	 */
	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * Retrieves the ID of the order to which the item belongs.
	 * 
	 * @return The order ID.
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the ID of the order to which the item belongs.
	 * 
	 * @param orderId The order ID to set.
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Retrieves the ID of the product in the order item.
	 * 
	 * @return The product ID.
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * Sets the ID of the product in the order item.
	 * 
	 * @param productId The product ID to set.
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * Retrieves the quantity of the product in the order item.
	 * 
	 * @return The quantity.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the product in the order item.
	 * 
	 * @param quantity The quantity to set.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Returns a string representation of the OrderItem object.
	 * 
	 * @return A string representation containing the order item's details.
	 */
	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", productId=" + productId
				+ ", quantity=" + quantity + "]";
	}
}
