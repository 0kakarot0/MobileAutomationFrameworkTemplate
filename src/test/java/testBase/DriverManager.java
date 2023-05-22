package testBase;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageBase.driver.PageDriver;
import utils.server.AppiumServer;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class DriverManager extends PageDriver{

    public DriverManager(AndroidDriver driver) {
        super(driver);
    }

    public static AndroidDriver getDriver(String deviceName) throws MalformedURLException, FileNotFoundException {
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

        return getDriver();
    }
}

