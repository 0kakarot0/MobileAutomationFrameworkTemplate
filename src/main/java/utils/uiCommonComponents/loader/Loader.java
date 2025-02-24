package utils.uiCommonComponents.loader;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import utils.driverUtils.SeleniumUtils;

public class Loader extends SeleniumUtils {
    // add your own loader xpath here to handle the loader behavior in your app
    By loader = By.xpath("");


    public Loader(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLoader() {
        if (isElementDisplayed(loader))
            waitForInvisibility(loader);
    }

    public boolean isLoaderDisplayed() {
        return isElementDisplayed(loader);
    }
}
