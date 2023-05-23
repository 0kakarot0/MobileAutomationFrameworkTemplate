package utils.fileReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;


// This class reads properties from configuration and email configuration files.
public class PropertiesFileReader {
    private static final Logger logger = Logger.getLogger(PropertiesFileReader.class);

    private Properties configurationProperties;
    private Properties emailProperties;
    private String filePath;



    // This method reads properties from the configuration file.
    public void readConfigurationProperties() {
        filePath = "src/test/resources/testDataFiles/configuration.properties";
        logger.info("Reading configuration properties from file: " + filePath);

        configurationProperties = readProperties(filePath);
        logger.info("Configuration properties read successfully.");

    }

    // This method reads properties from the email configuration file.
    public void readEmailProperties() {
        filePath = "src/test/resources/testDataFiles/emailConfiguration.properties";
        logger.info("Reading email configuration properties from file: " + filePath);

        emailProperties = readProperties(filePath);
        logger.info("Email configuration properties read successfully.");
    }

    public String getUserName( ) {
        return getProperty(configurationProperties, "userName");
    }
    public String getUserPassword( ) {
        return getProperty(configurationProperties, "passwordPins");
    }

    public String getEmail() {
        return getProperty(emailProperties, "userEmail");
    }

    public String getPort() {
        return getProperty(emailProperties, "port");
    }
    public String getHost() {
        return getProperty(emailProperties, "host");
    }
    public String getEmailPassword() {
        return getProperty(emailProperties, "passwordEmail");
    }









    private Properties readProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private String getProperty(Properties properties, String propertyName) {
        if (properties != null) {
            return properties.getProperty(propertyName);
        }
        return null;
    }
}

