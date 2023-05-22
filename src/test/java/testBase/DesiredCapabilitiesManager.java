package testBase;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.fileReader.DeviceData;
import utils.fileReader.DeviceDataReader;

import java.io.FileNotFoundException;
import java.util.List;
public class DesiredCapabilitiesManager {
    public static DesiredCapabilities getDesiredCapabilities(String deviceName) throws FileNotFoundException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // Fetch the device data based on the deviceName parameter from DeviceDataReader
        List<DeviceData> deviceDataList = DeviceDataReader.getDeviceData();
        DeviceData.Device device = null;
        for (DeviceData deviceData : deviceDataList) {
            if (deviceData.getDevice().getDeviceName().equals(deviceName)) {
                device = deviceData.getDevice();
                break;
            }
        }

        // Set the desired capabilities based on the device data
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, device.getAutomationName());
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, device.getPlatformName());
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformVersion());
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceName());
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, device.getUdid());

        // Set other desired capabilities as needed
        // Set the appPackage, appActivity
        desiredCapabilities.setCapability("appPackage", "com.veronicaapps.veronica.simplecalculator");
        desiredCapabilities.setCapability("appActivity", "com.veronicaapps.veronica.simplecalculator.MainActivity");

        return desiredCapabilities;
    }

}
