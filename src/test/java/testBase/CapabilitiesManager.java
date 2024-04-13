package testBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import utils.fileReader.DeviceData;
import utils.fileReader.DeviceDataReader;

import java.io.FileNotFoundException;
import java.util.List;


// This class sets up the DesiredCapabilities for the AndroidDriver based on the device name.
public class CapabilitiesManager {
    private static final Logger logger = LogManager.getLogger(CapabilitiesManager.class);


    // This method returns the UiAutomator2Options Capabilities for the specified device name.
    public static Capabilities getDeviceCapabilities(String deviceName, AppiumDriver driver) throws FileNotFoundException {
        logger.info("Setting up UiAutomator2Options Capabilities for device: {}", deviceName);


        // Fetch the device data based on the deviceName parameter from DeviceDataReader
        List<DeviceData> deviceDataList = DeviceDataReader.getDeviceData();
        DeviceData.Device device = null;
        for (DeviceData deviceData : deviceDataList) {
            if (deviceData.getDevice().getDeviceName().equals(deviceName)) {
                device = deviceData.getDevice();
                break;
            }
        }

        if (device == null) {
            logger.error("Device not found: {}", deviceName);
            throw new FileNotFoundException("Device not found: " + deviceName);
        }

        // Set the desired capabilities based on the device data

        logger.info("DesiredCapabilities set up complete.");
        if (driver instanceof AndroidDriver) {
            return getUiAutomator2Options(device);
        } else {
            return getXCUITestOptions(device);
        }
    }

    private static UiAutomator2Options getUiAutomator2Options(DeviceData.Device device) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(device.getDeviceName());
        options.setUdid(device.getUdid());
        options.setAutomationName(device.getAutomationName());
        options.setPlatformName(device.getPlatformName());
        options.setPlatformVersion(device.getPlatformVersion());

        //Use this if you want to install app, if app is not installed
        options.setApp(device.getApp());


        // Set the appPackage, appActivity, If app is already installed
        options.setAppActivity("com.veronicaapps.veronica.simplecalculator.MainActivity");
        options.setAppPackage("com.veronicaapps.veronica.simplecalculator.MainActivity");
        return options;
    }

    private static XCUITestOptions getXCUITestOptions(DeviceData.Device device) {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(device.getDeviceName());
        options.setUdid(device.getUdid());
        options.setAutomationName(device.getAutomationName());
        options.setPlatformName(device.getPlatformName());
        options.setPlatformVersion(device.getPlatformVersion());

        //Use this if you want to install app, if app is not installed
        options.setApp(device.getApp());


        // Set the appPackage, appActivity, If app is already installed
        return options;
    }

}
