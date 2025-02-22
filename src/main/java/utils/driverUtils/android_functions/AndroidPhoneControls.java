package utils.driverUtils.android_functions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidPhoneControls {

    private final AndroidDriver driver;

    public AndroidPhoneControls(AndroidDriver driver) {
        this.driver = driver;
    }

    public void turnOnWifi() {
        if (!driver.getConnection().isWiFiEnabled()) {
            driver.toggleWifi();
        }
    }

    public void turnOffWifi() {
        if (driver.getConnection().isWiFiEnabled()) {
            driver.toggleWifi();
        }
    }

    public void turnOnAirplaneMode() {
        if (!driver.getConnection().isAirplaneModeEnabled()) {
            driver.toggleAirplaneMode();
        }
    }

    public void turnOffAirplaneMode() {
        if (driver.getConnection().isAirplaneModeEnabled()) {
            driver.toggleAirplaneMode();
        }
    }

    public void turnOnData() {
        if (!driver.getConnection().isDataEnabled()) {
            driver.toggleData();
        }
    }

    public void turnOffData() {
        if (driver.getConnection().isDataEnabled()) {
            driver.toggleData();
        }
    }

    public void turnOnLocation() {
        if (!driver.isLocationServicesEnabled()) {
            driver.toggleLocationServices();
        }
    }

    public void turnOffLocation() {
        if (driver.isLocationServicesEnabled()) {
            driver.toggleLocationServices();
        }
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void hideKeyboard() {
        if (isKeyboardVisible()) {
            navigateBack();
        }
    }

    private boolean isKeyboardVisible() {
        return driver.isKeyboardShown();
    }


    public void unlockPhone() {
        driver.unlockDevice();
    }

    public String unlockDevice(String pin) {
        if (driver.isDeviceLocked()) {
            driver.unlockDevice();
            for (char digit : pin.toCharArray()) {
                AndroidKey key = AndroidKey.valueOf("DIGIT_" + digit);
                driver.pressKey(new KeyEvent(key));
            }
            return "Done";
        } else {
            return "Not unlocked";
        }
    }

    public void bringAppPackageToFront(String appPackage) {
        if (driver.queryAppState(appPackage).equals("STOPPED")) {
            driver.activateApp(appPackage);
        } else if (driver.queryAppState(appPackage).equals("RUNNING_IN_BACKGROUND")) {
            driver.activateApp(appPackage);
        } else if (driver.queryAppState(appPackage).equals("RUNNING_IN_FOREGROUND")) {
            driver.activateApp(appPackage);
        } else {
            throw new RuntimeException("App is in unknown state");
        }
    }
}
