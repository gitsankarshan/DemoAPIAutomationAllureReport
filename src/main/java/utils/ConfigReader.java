package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    public static void loadProperties() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
            logger.info("Configuration properties loaded.");
        } catch (Exception e) {
        				logger.error("Error loading configuration properties: " + e.getMessage());
          //  e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
