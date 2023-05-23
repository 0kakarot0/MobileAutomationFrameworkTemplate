package utils.commonComponents.scrollMethods;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pageBase.PageDriver;

import java.util.Map;

public class ScrollingMethods extends PageDriver {
    private static final Logger logger = LogManager.getLogger(ScrollingMethods.class);

    private AndroidDriver driver;

    public ScrollingMethods(AndroidDriver driver) {
        super(driver);
    }

    public void swipeUp() {
        logger.info("Swiping up...");
        getDriver().executeScript("mobile: swipe", Map.of("direction", "up"));
    }

    public void swipeDown() {
        logger.info("Swiping down...");
        getDriver().executeScript("mobile: swipe", Map.of("direction", "down"));
    }

    public void swipeLeft() {
        logger.info("Swiping left...");
        getDriver().executeScript("mobile: swipe", Map.of("direction", "left"));
    }

    public void swipeRight() {
        logger.info("Swiping right...");
        getDriver().executeScript("mobile: swipe", Map.of("direction", "right"));
    }
}
