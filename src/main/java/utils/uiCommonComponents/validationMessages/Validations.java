package utils.uiCommonComponents.validationMessages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.driverUtils.MobileElementAttributes;
import utils.driverUtils.SeleniumUtils;

public class Validations extends SeleniumUtils {
    public Validations(AndroidDriver driver) {
        super(driver);
    }


    public String getValidationMessage(By validationMessageLocator, MobileElementAttributes mobileElementAttributes) {
        return getAttribute(validationMessageLocator, mobileElementAttributes);
    }
}
