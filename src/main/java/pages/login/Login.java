package pages.login;


import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.LogManager;
import org.openqa.selenium.By;
import utils.seleniumUtils.SeleniumUtils;
import org.apache.log4j.Logger;


// This class represents the Login page.
public class Login extends SeleniumUtils {
    private static final Logger logger = LogManager.getLogger(Login.class);

    /**
     * Constructor for Login.
     * Initializes the driver.
     *
     * @param driver The AndroidDriver instance.
     */
    public Login(AndroidDriver driver) {
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
            click(numberNine);
            stepResult = "Pass, User able to enter the value";
            logger.info("Click successful.");
        } catch (Exception e) {
            stepResult = "Fail, User unable to enter the value";
            logger.error("Click failed.", e);
            e.printStackTrace();
        }
        return stepResult;
    }
}
