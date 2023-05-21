package pageBase.driver;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;

public class PageDriver {

    private AndroidDriver driver;

    public PageDriver(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AndroidDriver getDriver(){
        return driver;
    }
}
