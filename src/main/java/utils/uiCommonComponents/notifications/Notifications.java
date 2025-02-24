package utils.uiCommonComponents.notifications;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.driverUtils.MobileElementAttributes;
import utils.driverUtils.SeleniumUtils;

public class Notifications extends SeleniumUtils {
    public Notifications(AndroidDriver driver) {
        super(driver);
    }

    public boolean isNotificationDisplayed(By notificationLocator) {
        return isElementDisplayed(notificationLocator);
    }

    public String getNotificationText(By notificationLocator, MobileElementAttributes attribute) {
        return getAttribute(notificationLocator, attribute);
    }

    public void tapOnNotificationButton(By notificationButtonLocator) {
        clickOnElement(notificationButtonLocator);
    }
}
