package utils.config_loader;


public class ConfigLoader {
    // ThreadLocal variable to hold the language setting for each thread
    private static final ThreadLocal<String> language = ThreadLocal.withInitial(() -> "en"); // Default value

    private static final ThreadLocal<String> currentDevice = ThreadLocal.withInitial(() -> "defaultValue"); // Default value

    private static final ThreadLocal<String> platformName = ThreadLocal.withInitial(() -> "defaultValue"); // Default value

    private static final ThreadLocal<String> platformVersion = ThreadLocal.withInitial(() -> "defaultValue"); // Default value

    private static final ThreadLocal<String> udID = ThreadLocal.withInitial(() -> "defaultValue"); // Default value

    private static final ThreadLocal<String> deviceName = ThreadLocal.withInitial(() -> "defaultValue"); // Default value

    // Getter for the ThreadLocal language
    public static String getLanguage() {
        return language.get();
    }

    // Setter for the ThreadLocal language
    public static void setLanguage(String lang) {
        language.set(lang);
    }  // Getter for the ThreadLocal language


    public static String getCurrentDevice() {
        return currentDevice.get();
    }

    public static void setCurrentDevice(String device) {
        currentDevice.set(device);
    }


    public static String getPlatformName() {
        return platformName.get();
    }


    public static void setPlatformName(String platform) {
        platformName.set(platform);
    }

    public static String getPlatformVersion() {
        return platformVersion.get();
    }


    public static void setPlatformVersion(String platform) {
        platformVersion.set(platform);
    }

    public static String getUdID() {
        return udID.get();
    }


    public static void setUdID(String UDID) {
        udID.set(UDID);
    }

    public static String getDeviceName() {
        return deviceName.get();
    }


    public static void setDeviceName(String deviceName1) {
        deviceName.set(deviceName1);
    }
}
