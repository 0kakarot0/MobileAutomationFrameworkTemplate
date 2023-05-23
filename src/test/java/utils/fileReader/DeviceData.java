package utils.fileReader;

// This class represents the data for a device.
public class DeviceData {
    private Device device;

    // Return the device properties
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    // Add getters and setters for the device properties
    public static class Device {
        private String firstName;
        private String automationName;
        private String platformName;
        private String platformVersion;
        private String deviceName;
        private String udid;
        private String autoGrantPermissions;
        private String app;

        // Add getters and setters for the device properties

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getAutomationName() {
            return automationName;
        }

        public void setAutomationName(String automationName) {
            this.automationName = automationName;
        }

        public String getPlatformName() {
            return platformName;
        }

        public void setPlatformName(String platformName) {
            this.platformName = platformName;
        }

        public String getPlatformVersion() {
            return platformVersion;
        }

        public void setPlatformVersion(String platformVersion) {
            this.platformVersion = platformVersion;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getUdid() {
            return udid;
        }

        public void setUdid(String udid) {
            this.udid = udid;
        }

        public String getAutoGrantPermissions() {
            return autoGrantPermissions;
        }

        public void setAutoGrantPermissions(String autoGrantPermissions) {
            this.autoGrantPermissions = autoGrantPermissions;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }
    }
}
