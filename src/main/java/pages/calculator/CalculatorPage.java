package pages.calculator;
/*
* This is just a example page class you can modify the package and class according to the your mobile app
*
* */

import io.appium.java_client.android.AndroidDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import utils.driverUtils.SeleniumUtils;


// This class represents the example page.
public class CalculatorPage extends SeleniumUtils {
    private static final Logger logger = LogManager.getLogger(CalculatorPage.class);
    private  String stepResult;
    /**
     * Constructor for Login.
     * Initializes the driver.
     *
     * @param driver The AndroidDriver instance.
     */
    public CalculatorPage(AndroidDriver driver) {
        super(driver);
    }

    /**
     * Clicks on the number nine button and returns the result.
     *
     * @return The result of the operation.
     */
    public String click(){
        By numberNine = By.id("com.veronicaapps.veronica.simplecalculator:id/button_nine");
        try {
            logger.info("Attempting to click on number nine button...");
            clickOnElement(numberNine);
            stepResult = "Pass, User able to click on the nine button";
            logger.info("Click successful.");
        } catch (Exception e) {
            stepResult = "Fail, User unable to click on the nine button";
            logger.error("Click failed.", e);
            e.printStackTrace();
        }
        return stepResult;
    }
}
