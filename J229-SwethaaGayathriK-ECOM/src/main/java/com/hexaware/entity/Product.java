package com.hexaware.entity;

/**
 * Represents a Product entity with its attributes.
 */
public class Product {
	private long productId;
	private String name;
	private double price;
	private String description;
	private int stockQuantity;

	/**
	 * Constructs a Product object with the given parameters.
	 * 
	 * @param productId     The ID of the product.
	 * @param name          The name of the product.
	 * @param price         The price of the product.
	 * @param description   The description of the product.
	 * @param stockQuantity The stock quantity of the product.
	 */
	public Product(long productId, String name, double price, String description, int stockQuantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.stockQuantity = stockQuantity;
	}

	/**
	 * Retrieves the ID of the product.
	 * 
	 * @return The product ID.
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * Sets the ID of the product.
	 * 
	 * @param productId The product ID to set.
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * Retrieves the name of the product.
	 * 
	 * @return The product name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the product.
	 * 
	 * @param name The product name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the price of the product.
	 * 
	 * @return The product price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the product.
	 * 
	 * @param price The product price to set.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Retrieves the description of the product.
	 * 
	 * @return The product description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the product.
	 * 
	 * @param description The product description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieves the stock quantity of the product.
	 * 
	 * @return The stock quantity.
	 */
	public int getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * Sets the stock quantity of the product.
	 * 
	 * @param stockQuantity The stock quantity to set.
	 */
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**
	 * Returns a string representation of the Product object.
	 * 
	 * @return A string representation containing the product's details.
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", description="
				+ description + ", stockQuantity=" + stockQuantity + "]";
	}
}
