package utils.driverUtils.android_functions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidKeysFunctions {
    private final AndroidDriver driver;

    public AndroidKeysFunctions(AndroidDriver driver) {
        this.driver = driver;
    }

    public void pressDigitsAndroidKeyboardKeys(String... passcodes) {
        for (String pin : passcodes) {
            try {
                AndroidKey key = AndroidKey.valueOf("DIGIT_" + pin);
                driver.pressKey(new KeyEvent(key));
                Thread.sleep(10);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid key: " + pin, e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    public void pressDoneAndroidKeyboardKey() {
        try {
            AndroidKey key = AndroidKey.ENTER;
            driver.pressKey(new KeyEvent(key));
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public void pressSearchAndroidKeyboardKey() {
        try {
            AndroidKey key = AndroidKey.SEARCH;
            driver.pressKey(new KeyEvent(key));
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
