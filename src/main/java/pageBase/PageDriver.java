package pageBase;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

/**
 * This class is used to manage the AndroidDriver instance.
 */
public class PageDriver {

    private static AndroidDriver driver;
    private static final Logger logger = LogManager.getLogger(PageDriver.class);

    /**
     * Constructor for PageDriver.
     * Initializes the driver and PageFactory elements.
     *
     * @param driver The AndroidDriver instance.
     */
    public PageDriver(AndroidDriver driver) {
        logger.info("Initializing PageDriver...");
        PageDriver.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Sets the AndroidDriver instance.
     *
     * @param driver The AndroidDriver instance.
     */
    public static void setDriver(AndroidDriver driver) {
        logger.info("Setting driver...");
        PageDriver.driver = driver;
    }

    /**
     * Returns the AndroidDriver instance.
     *
     * @return The AndroidDriver instance.
     */
    public static AndroidDriver getDriver(){
        logger.info("Getting driver...");
        return driver;
    }
}
