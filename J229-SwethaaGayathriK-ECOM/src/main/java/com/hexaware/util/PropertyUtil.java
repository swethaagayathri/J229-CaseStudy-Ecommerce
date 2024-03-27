package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for loading properties from a file.
 */
public class PropertyUtil {
	/**
	 * Loads properties from the "db.properties" file.
	 * 
	 * @return Properties object containing the loaded properties.
	 */
	public static Properties loadProperties() {
		Properties prop = new Properties();
		try (FileInputStream fis = new FileInputStream("db.properties")) {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Retrieves the property value.
	 * 
	 * @param key the key of the property to retrieve.
	 * @return the value of the property associated with the key, or null if the key
	 *         is not found.
	 */
	public static String getPropertyString(String key) {
		Properties prop = loadProperties();
		return prop.getProperty(key);
	}
}
