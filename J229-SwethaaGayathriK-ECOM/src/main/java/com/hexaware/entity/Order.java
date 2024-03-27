package com.hexaware.entity;

/**
 * Represents an Order entity with its attributes.
 */
public class Order {
	private long orderId;
	private long customerId;
	private String orderDate;
	private double totalPrice;
	private String shippingAddress;

	/**
	 * Constructs an Order object with the given parameters.
	 * 
	 * @param orderId         The ID of the order.
	 * @param customerId      The ID of the customer who placed the order.
	 * @param orderDate       The date of the order.
	 * @param totalPrice      The total price of the order.
	 * @param shippingAddress The shipping address for the order.
	 */
	public Order(long orderId, long customerId, String orderDate, double totalPrice, String shippingAddress) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.shippingAddress = shippingAddress;
	}

	/**
	 * Retrieves the ID of the order.
	 * 
	 * @return The order ID.
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the ID of the order.
	 * 
	 * @param orderId The order ID to set.
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Retrieves the ID of the customer who placed the order.
	 * 
	 * @return The customer ID.
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the ID of the customer who placed the order.
	 * 
	 * @param customerId The customer ID to set.
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * Retrieves the date of the order.
	 * 
	 * @return The order date.
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * Sets the date of the order.
	 * 
	 * @param orderDate The order date to set.
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * Retrieves the total price of the order.
	 * 
	 * @return The total price.
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets the total price of the order.
	 * 
	 * @param totalPrice The total price to set.
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Retrieves the shipping address for the order.
	 * 
	 * @return The shipping address.
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * Sets the shipping address for the order.
	 * 
	 * @param shippingAddress The shipping address to set.
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * Returns a string representation of the Order object.
	 * 
	 * @return A string representation containing the order's details.
	 */
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", orderDate=" + orderDate + ", totalPrice="
				+ totalPrice + ", shippingAddress=" + shippingAddress + "]";
	}
}
