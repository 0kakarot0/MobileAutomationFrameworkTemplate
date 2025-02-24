package testBase;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.remote.options.BaseOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.JsonUtils;
import utils.server.AppiumConfigModel;
import utils.server.AppiumServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


// This class manages the AndroidDriver.
public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    private static AndroidDriver driver;

    // This method returns the AndroidDriver for the specified device name.
    public static AndroidDriver getDriver(String deviceName) throws MalformedURLException, FileNotFoundException {
        logger.info("Getting driver for device: " + deviceName);

        // Check if the driver is already initialized
        if (driver == null) {
            // Get the desired capabilities for the device
            BaseOptions capabilities = DesiredCapabilitiesManager.getDesiredCapabilities(deviceName);

//            // Start the Appium server
//            AppiumServer.startAppiumServer();
//
//
//            // Get the details of the started Appium server
//            Map<String, String> serverDetails = AppiumServer.getAppiumServerDetails();
//            String serverUrl = serverDetails.get("URL").trim();

            // Set the device name capability
            capabilities.setCapability("deviceName", deviceName);

            // Example additional capability
            AppiumConfigModel configModel = null;
            try {
                configModel = JsonUtils.readJson("src/test/resources/testDataFiles/appium_config/appium_config.json", AppiumConfigModel.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            URL fullUrl = new URL(configModel.getUrl());

            // Create and assign a new AndroidDriver instance
            driver = new AndroidDriver(fullUrl, capabilities);

        }

        logger.info("Driver obtained.");
        return driver;
    }
}

