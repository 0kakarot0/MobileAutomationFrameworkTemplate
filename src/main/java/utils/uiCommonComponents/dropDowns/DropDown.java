package utils.uiCommonComponents.dropDowns;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.driverUtils.SeleniumUtils;
import utils.random.MyRandomUtils;

public class DropDown extends SeleniumUtils {
    public DropDown(AndroidDriver driver) {
        super(driver);
    }

    public void selectElementFromDropDownList(By dropDownLocator) {
       WebElement element = MyRandomUtils.getRandomElement(getListOfWebElement(dropDownLocator));
       element.click();
    }

    public void selectMultipleElementsFromDropDownList(By dropDownLocator, int numberOfElements) {
        for (int i = 0; i < numberOfElements; i++) {
            WebElement element = MyRandomUtils.getRandomElement(getListOfWebElement(dropDownLocator));
            element.click();
        }
    }
}
