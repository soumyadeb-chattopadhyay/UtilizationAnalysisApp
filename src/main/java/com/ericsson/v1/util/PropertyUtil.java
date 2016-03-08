package com.ericsson.v1.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertyUtil {

	private static PropertyUtil propertyUtil = new PropertyUtil();
	private static Properties properties;
	InputStream inputStream = null;
	private PropertyUtil() {
		
		try {
			properties = new Properties();
			String propFileName = "application.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public  static PropertyUtil getInstance() {
		return propertyUtil;
	}

	public  String getValue(String key) {
		return properties.getProperty(key);
	}
	
}
