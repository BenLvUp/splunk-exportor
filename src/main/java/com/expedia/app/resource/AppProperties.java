package com.expedia.app.resource;

/**
 *
 * AppProperties.java
 * 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
	private static Properties prop = null;

	static {
		prop = new Properties();
		try {
			// 通过类加载器来加载资源：先找到这个类，在找到这个类加载器不用在关心文�?config.properties"的具体位�?
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			// 这样也可以得到当前的ClassLoader
			// ClassLoader classLoader = AppProperties.class.getClassLoader();
			InputStream inputStream = classLoader.getResourceAsStream("config.properties");

			prop.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperties(String key) {
		String value = prop.getProperty(key);
		if (value == null) {
			value = "";
		}
		return value.trim();
	}
}