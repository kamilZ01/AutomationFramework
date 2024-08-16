package org.automation.framework.utils;

import org.automation.framework.enums.BrowserType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public void initProp() {
        String workingDir = System.getProperty("user.dir");
        String propertiesPath = String.format("%s%s", workingDir, "\\src\\test\\resources\\config\\config.properties");
        try (FileInputStream input = new FileInputStream(propertiesPath)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public BrowserType getBrowser() {
        String browserName = properties.getProperty("browser");
        if (browserName != null) {
            return BrowserType.valueOf(browserName.toUpperCase());
        } else {
            throw new IllegalArgumentException("Browser property not found.");
        }
    }

    public String getStringProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null)
            throw new IllegalArgumentException(String.format("Property [%s] not found in config.properties", key));

        return value;
    }

    public int getNumberProperty(String key) {
        String value = properties.getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Invalid format for [%s] parameter.", key));
        }
    }

}
