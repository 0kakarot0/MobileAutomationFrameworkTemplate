package testBase;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageBase.PageDriver;

import utils.server.AppiumServer;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import org.apache.log4j.Logger;


// This class manages the AndroidDriver.
public class DriverManager extends PageDriver {
    private static final Logger logger = Logger.getLogger(DriverManager.class);

    public DriverManager(AndroidDriver driver) {
        super(driver);
    }

    // This method returns the AndroidDriver for the specified device name.
    public static AndroidDriver getDriver(String deviceName) throws MalformedURLException, FileNotFoundException {
        logger.info("Getting driver for device: " + deviceName);

        // Check if the driver is already initialized
        if (getDriver() == null) {
            // Get the desired capabilities for the device
            DesiredCapabilities capabilities = DesiredCapabilitiesManager.getDesiredCapabilities(deviceName);

            // Start the Appium server
            AppiumServer.startAppiumServer();


            // Get the details of the started Appium server
            Map<String, String> serverDetails = AppiumServer.getAppiumServerDetails();
            String serverUrl = serverDetails.get("url").trim();

            // Set the device name capability
            capabilities.setCapability("deviceName", deviceName);

            // Example additional capability
            URL fullUrl = new URL(serverUrl);

            // Create and assign a new AndroidDriver instance
            AndroidDriver driver = new AndroidDriver(fullUrl, capabilities);
            setDriver(driver);
        }

        logger.info("Driver obtained.");
        return getDriver();
    }
}

