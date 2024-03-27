package com.hexaware.dao;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Product;
import com.hexaware.exception.*;
import com.hexaware.exception.ProductNotFoundException;
import com.hexaware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of OrderProcessorRepository interface for handling database
 * operations.
 */
public class OrderProcessorRepositoryImpl implements OrderProcessorRepository {
	private static final Connection conn = DBConnection.getDBConnection();
	private static int cartId = getLastUsedCartIdFromDB();
	private static int orderId = getLastUsedOrderIdFromDB();
	private static int order_ItemId = getLastUsedOrderItemIdFromDB();;

	/**
	 * Creates a new product in the database.
	 * 
	 * @param product The product to be created.
	 * @return True if the product is created successfully, false otherwise.
	 */
	@Override
	public boolean createProduct(Product product) {
		try {
			String query = "INSERT INTO products (product_id, name, price, description, stock_quantity) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, product.getProductId());
			ps.setString(2, product.getName());
			ps.setDouble(3, product.getPrice());
			ps.setString(4, product.getDescription());
			ps.setInt(5, product.getStockQuantity());
			int numberOfRows = ps.executeUpdate();
			return numberOfRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Creates a new customer in the database.
	 * 
	 * @param customer The customer to be created.
	 * @return True if the customer is created successfully, false otherwise.
	 */
	@Override
	public boolean createCustomer(Customer customer) {
		try {
			String query = "INSERT INTO customers (customer_id, name, email, password) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, customer.getCustomerId());
			ps.setString(2, customer.getName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPassword());
			int numberOfRows = ps.executeUpdate();
			return numberOfRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes a product from the database.
	 * 
	 * @param productId The ID of the product to be deleted.
	 * @return True if the product is deleted successfully, false otherwise.
	 * @throws ProductNotFoundException if the product is not found.
	 */
	@Override
	public boolean deleteProduct(long productId) throws ProductNotFoundException {
		try {
			String query = "DELETE FROM products WHERE product_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, productId);
			int numberOfRows = ps.executeUpdate();
			if (numberOfRows == 0) {
				throw new ProductNotFoundException("Product with ID " + productId + " not found.");
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes a customer from the database.
	 * 
	 * @param customerId The ID of the customer to be deleted.
	 * @return True if the customer is deleted successfully, false otherwise.
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	@Override
	public boolean deleteCustomer(long customerId) throws CustomerNotFoundException {
		try {
			String query = "DELETE FROM customers WHERE customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, customerId);
			int numberOfRows = ps.executeUpdate();
			if (numberOfRows == 0) {
				throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Adds a product to the cart in the database.
	 * 
	 * @param customer The customer adding the product to the cart.
	 * @param product  The product to be added to the cart.
	 * @param quantity The quantity of the product to be added.
	 * @return True if the product is added to the cart successfully, false
	 *         otherwise.
	 */
	@Override
	public boolean addToCart(Customer customer, Product product, int quantity) {
		try {
			String query = "INSERT INTO cart (cart_id, customer_id, product_id, quantity) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			int newCartId = cartId++;
			ps.setLong(1, newCartId);
			ps.setLong(2, customer.getCustomerId());
			ps.setLong(3, product.getProductId());
			ps.setInt(4, quantity);
			int numberOfRows = ps.executeUpdate();
			return numberOfRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Removes a product from the cart in the database.
	 * 
	 * @param customer The customer removing the product from the cart.
	 * @param product  The product to be removed from the cart.
	 * @return True if the product is removed from the cart successfully, false
	 *         otherwise.
	 * @throws ProductNotFoundException  if the product is not found.
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	@Override
	public boolean removeFromCart(Customer customer, Product product)
			throws ProductNotFoundException, CustomerNotFoundException {
		try {
			String query = "DELETE FROM cart WHERE customer_id = ? AND product_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, customer.getCustomerId());
			ps.setLong(2, product.getProductId());
			int numberOfRows = ps.executeUpdate();
			if (numberOfRows > 0) {
				return true;
			} else {
				throw new ProductNotFoundException("Product or Customer not found in cart.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves all products from the cart for a given customer.
	 * 
	 * @param customer The customer whose cart is being retrieved.
	 * @return List of products in the cart for the given customer.
	 * @throws CustomerNotFoundException if the customer is not found.
	 */
	@Override
	public List<Product> getAllFromCart(Customer customer) throws CustomerNotFoundException {
		List<Product> productList = new ArrayList<>();
		try {
			String query = "SELECT p.* FROM products p INNER JOIN cart c ON p.product_id = c.product_id WHERE c.customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, customer.getCustomerId());
			boolean customerExists = false;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getInt("stock_quantity"));
				productList.add(product);
				customerExists = true;
			}
			if (!customerExists) {
				throw new CustomerNotFoundException("Customer not found in cart.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	/**
	 * Places an order for the customer in the database.
	 * 
	 * @param customer           The customer placing the order.
	 * @param productsQuantities The map containing products and their quantities.
	 * @param shippingAddress    The shipping address for the order.
	 * @return True if the order is placed successfully, false otherwise.
	 */
	@Override
	public boolean placeOrder(Customer customer, List<Map<Product, Integer>> productsQuantities,
			String shippingAddress) {
		try {
			String query = "INSERT INTO orders (order_id, customer_id, order_date, shipping_address, total_price) VALUES (?, ?, NOW(), ?, ?)";
			PreparedStatement orderps = conn.prepareStatement(query);
			int newOrderId = orderId++;
			orderps.setLong(1, newOrderId);
			orderps.setLong(2, customer.getCustomerId());
			orderps.setString(3, shippingAddress);

			double totalPrice = 0;
			for (Map<Product, Integer> entry : productsQuantities) {
				for (Map.Entry<Product, Integer> mapProductQuantity : entry.entrySet()) {
					Product product = mapProductQuantity.getKey();
					int quantity = mapProductQuantity.getValue();
					totalPrice += getPriceByProductID(product.getProductId()) * quantity;
				}
			}
			orderps.setDouble(4, totalPrice);

			int rowsAffected = orderps.executeUpdate();

			if (rowsAffected == 0) {
				return false;
			}

			String oi_query = "INSERT INTO order_items (ORDER_ITEM_ID, order_id, product_id, quantity) VALUES (?, ?, ?, ?)";
			PreparedStatement oi_ps = conn.prepareStatement(oi_query);
			for (Map<Product, Integer> entry : productsQuantities) {
				for (Map.Entry<Product, Integer> map : entry.entrySet()) {
					int newOrderItemId = order_ItemId++;
					Product product = map.getKey();
					int quantity = map.getValue();
					reduceProductQuantity(product.getProductId(), quantity);
					oi_ps.setLong(1, newOrderItemId);
					oi_ps.setLong(2, newOrderId);
					oi_ps.setLong(3, product.getProductId());
					oi_ps.setInt(4, quantity);
					oi_ps.executeUpdate();
				}
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves all products in the database.
	 * 
	 * @return List of all products.
	 */
	@Override
	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<>();
		try {
			String query = "SELECT * FROM products";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getLong("product_id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getInt("stock_quantity"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	/**
	 * Retrieves orders placed by a customer from the database.
	 * 
	 * @param customerId The ID of the customer.
	 * @return List of orders placed by the customer.
	 * @throws OrderNotFoundException if no orders are found for the customer.
	 */
	@Override
	public List<Map<Product, Integer>> getOrdersByCustomer(long customerId) throws OrderNotFoundException {
		List<Map<Product, Integer>> orders = new ArrayList<>();
		try {
			String query = "SELECT o.order_id, oi.product_id, oi.quantity FROM orders o "
					+ "INNER JOIN order_items oi ON o.order_id = oi.order_id " + "WHERE o.customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, customerId);
			ResultSet rs = ps.executeQuery();
			boolean foundOrder = false;
			while (rs.next()) {
				long orderId = rs.getLong("order_id");
				long productId = rs.getLong("product_id");
				int quantity = rs.getInt("quantity");
				Product product = getProductById(productId);
				Map<Product, Integer> orderItem = new HashMap<>();
				orderItem.put(product, quantity);
				orders.add(orderItem);
				foundOrder = true;
			}
			if (!foundOrder) {
				throw new OrderNotFoundException("No orders found for customer with ID " + customerId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	/**
	 * Retrieves a product by its ID from the database.
	 * 
	 * @param productId The ID of the product to retrieve.
	 * @return The product with the specified ID.
	 */
	public Product getProductById(long productId) {
		try {
			String query = "SELECT * FROM products WHERE product_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, productId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return new Product(rs.getLong("product_id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getInt("stock_quantity"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Updates the stock quantity of a product after an order is placed.
	 * 
	 * @param productId The ID of the product.
	 * @param quantity  The quantity of the product to be reduced.
	 * @throws SQLException if a database access error occurs.
	 */
	private void reduceProductQuantity(long productId, int quantity) throws SQLException {
		String updateQuery = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE product_id = ?";
		PreparedStatement updatePs = conn.prepareStatement(updateQuery);
		updatePs.setInt(1, quantity);
		updatePs.setLong(2, productId);
		updatePs.executeUpdate();
	}

	/**
	 * Retrieves the last used cart ID from the database.
	 * 
	 * @return The last used cart ID.
	 */
	public static int getLastUsedCartIdFromDB() {
		int cartId = 0;
		try {
			String query = "SELECT MAX(cart_Id) AS LastCartId FROM cart";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				cartId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Math.max(2000, cartId + 1);
	}

	/**
	 * Retrieves the last used order ID from the database.
	 * 
	 * @return The last used order ID.
	 */
	public static int getLastUsedOrderIdFromDB() {
		int orderId = 0;
		try {
			String query = "SELECT MAX(Order_Id) AS LastOrderId FROM Orders";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				orderId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Math.max(3000, orderId + 1);
	}

	/**
	 * Retrieves the last used order item ID from the database.
	 * 
	 * @return The last used order item ID.
	 */
	public static int getLastUsedOrderItemIdFromDB() {
		int orderItemId = 0;
		try {
			String query = "SELECT MAX(Order_Item_Id) AS LastOrderItemId FROM Order_Items";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				orderItemId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Math.max(4000, orderItemId + 1);
	}

	/**
	 * Retrieves the price of a product by its ID from the database.
	 * 
	 * @param productId The ID of the product.
	 * @return The price of the product.
	 */
	public double getPriceByProductID(long productId) {
		double productPrice = 0.0;
		try {
			String query = "SELECT price FROM products WHERE product_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setLong(1, productId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				productPrice = rs.getDouble("price");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productPrice;
	}
}
