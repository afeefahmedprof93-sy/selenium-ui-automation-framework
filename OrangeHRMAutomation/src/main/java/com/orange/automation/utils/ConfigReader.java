package com.orange.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);

    // Properties object holds all key=value pairs from the file
    private static Properties properties;

    // Static block — runs ONCE when class is first loaded
    static {
        loadProperties();
    }

    // Private constructor — utility class, no objects needed. We will use static method
    private ConfigReader() {}

    private static void loadProperties() {
        properties = new Properties();

        // This path points to your config.properties file
        String configPath = "src/main/resources/config.properties";

        try {
            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            logger.info("Config file loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load config file: " + e.getMessage());
            throw new RuntimeException("config.properties not found at: " + configPath);
        }
    }

    // ---- Getter Methods ----

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getUrl() {
        return getProperty("url");
    }

    public static String getUsername() {
        return getProperty("username");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    // Central method — all getters call this
    private static String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            logger.error("Property '" + key + "' not found in config.properties");
            throw new RuntimeException("Property '" + key + "' not found!");
        }

        return value.trim();
    }
}
