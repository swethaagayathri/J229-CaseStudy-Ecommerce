package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility class for managing database connections.
 */
public class DBConnection {
	private static Connection conn;

	/**
	 * Retrieves a database connection.
	 * 
	 * @return Connection object representing the database connection.
	 */
	public static Connection getDBConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				Properties prop = PropertyUtil.loadProperties();
				String connectionString = prop.getProperty("connectionString");
				String username = prop.getProperty("username");
				String password = prop.getProperty("password");
				conn = DriverManager.getConnection(connectionString, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
