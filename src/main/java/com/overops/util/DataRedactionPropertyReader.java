package com.overops.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataRedactionPropertyReader {

	private final static Logger log = LoggerFactory.getLogger(DataRedactionPropertyReader.class);

	private static Properties properties = null;
	private static final String propertiesFile = "/DataRedaction.properties";
	
	//Private Constructor to avoid initiation
	private DataRedactionPropertyReader() {
    }

	public static Properties getInstance() {
		if (properties == null) {
			init();
		}
		return properties;
	}

	private static void init() {
		InputStream inputStream = null;
		try {
			log.info("Loading properties from file: " + propertiesFile);
			inputStream = DataRedactionPropertyReader.class.getResourceAsStream(propertiesFile);
			properties = new Properties();
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataRedactionException(e);
		}
		finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				throw new DataRedactionException(e);
			}
		}
	}
}