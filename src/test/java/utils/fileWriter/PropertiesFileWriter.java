package utils.fileWriter;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileWriter {

    private static final Logger logger = LogManager.getLogger(PropertiesFileWriter.class);
    private Properties properties;


    private String configurationFilePath = "src/test/resources/testDataFiles/configuration.properties";
    private String emailConfigurationFilePath = "src/test/resources/testDataFiles/emailConfiguration.properties";


    // This method writes properties to the specified file.
    private void writeProperties(String filePath, Properties properties) {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            properties.store(outputStream, null);
            logger.info("Properties written to file: " + filePath);
        } catch (IOException e) {
            logger.error("Error writing properties to file: " + filePath, e);
        }
    }

    // This method updates or adds a property to the specified file.
    private void updateProperty(String filePath, String key, String value) {
         properties = new Properties();
        // Load the existing properties from the file
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Error loading properties from file: " + filePath, e);
        }
        // Update or add the property
        properties.setProperty(key, value);
        // Write the properties back to the file
        writeProperties(filePath, properties);
        logger.info("Updated property: " + key + " in file: " + filePath);
    }

    /****************************************************************
     Specific methods to update properties in your configuration files
    ****************************************************************/

    // Updates the 'userName' property in the configuration file
    public void updateUserName(String userName) {
        writeProperties(configurationFilePath,properties);
        updateProperty(configurationFilePath, "userName", userName);
    }

    // Updates the 'passwordPins' property in the configuration file
    public void updatePasswordPins(String passwordPins) {
        writeProperties(configurationFilePath,properties);
        updateProperty(configurationFilePath, "passwordPins", passwordPins);
    }

    // Updates the 'host' property in the email configuration file
    public void updateHost(String host) {
        writeProperties(emailConfigurationFilePath,properties);
        updateProperty(emailConfigurationFilePath, "host", host);
    }

    // Updates the 'port' property in the email configuration file
    public void updatePort(String port) {
        writeProperties(emailConfigurationFilePath,properties);
        updateProperty(emailConfigurationFilePath, "port", port);
    }

    // Updates the 'userEmail' property in the email configuration file
    public void updateUserEmail(String userEmail) {
        writeProperties(emailConfigurationFilePath,properties);
        updateProperty(emailConfigurationFilePath, "userEmail", userEmail);
    }

    // Updates the 'passwordEmail' property in the email configuration file
    public void updatePasswordEmail(String passwordEmail) {
        writeProperties(emailConfigurationFilePath,properties);
        updateProperty(emailConfigurationFilePath, "passwordEmail", passwordEmail);
    }
}
