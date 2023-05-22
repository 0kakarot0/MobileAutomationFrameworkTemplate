package pageBase.driver;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;

public class PageDriver {

    private static AndroidDriver driver;

    public PageDriver(AndroidDriver driver) {
        PageDriver.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void setDriver(AndroidDriver driver) {
        PageDriver.driver = driver;
    }

    public static AndroidDriver getDriver(){
        return driver;
    }
}
