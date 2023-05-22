package utils.fileReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

    private Properties configurationProperties;
    private Properties emailProperties;
    private String filePath;



    public void readConfigurationProperties() {
        filePath = "src/test/resources/testDataFiles/configuration.properties";
        configurationProperties = readProperties(filePath);
    }

    public void readEmailProperties() {
        filePath = "src/test/resources/testDataFiles/emailConfiguration.properties";
        emailProperties = readProperties(filePath);
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

