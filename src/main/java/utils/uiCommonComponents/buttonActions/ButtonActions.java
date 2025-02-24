package utils.uiCommonComponents.buttonActions;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.config_loader.ConfigLoader;
import utils.driverUtils.SeleniumUtils;

public class ButtonActions extends SeleniumUtils {

    public ButtonActions(AndroidDriver driver) {
        super(driver);
    }

    // Method to click on any button based on its enum value
    public String clickButtonByName(ButtonName buttonName) {
        try {
            String language = ConfigLoader.getLanguage();

            String locator = getButtonXpath(buttonName, language);
            boolean isTrue = false;
            if (isTrue /*performCommonChecks()*/) {
                return "Fail, Unexpected error detected on the screen";
            }

            if (!isElementDisplayed(By.xpath(locator))) {
                waitForClickAble(By.xpath(locator));
            }
            clickOnElement(By.xpath(locator));
//            loader.waitForLoader();
            return "Pass, Clicked on the " + buttonName.name().replace("_", " ").toLowerCase();
        } catch (Exception e) {
            return "Fail, Unable to click on the " + buttonName.name() + " button";
        }
    }

    private static String getButtonXpath(ButtonName buttonName, String language) {
        return getButton(buttonName, language);
    }


    //modify this method to add your own definition of button
    private static String getButton(ButtonName buttonName, String language) {
        if (buttonName == ButtonName.HOME_ICON_BUTTON) {
            System.out.println("its home button");
            return "(//android.widget.ImageView[contains(@content-desc,'" + buttonName.getName(language) + "') or contains(@content-desc,'" + buttonName.getName(language) + "')])";
        }
        return "//android.view.View[contains(@content-desc,'" + buttonName.getName(language) + "') or contains(@content-desc,'" + buttonName.englishAlt + "') or contains(@content-desc,'" + buttonName.arabic + "') or contains(@content-desc,'" + buttonName.arabicAlt + "')]";
//                : "//android.widget.Button[contains(@content-desc,'" + buttonName.getName(language) + "') or contains(@content-desc,'" + buttonName.englishAlt + "') or contains(@content-desc,'" + buttonName.arabic + "') or contains(@content-desc,'" + buttonName.arabicAlt + "')]";
    }


    //example methods
    public void clickOnTheHomeButton(){
        clickButtonByName(ButtonName.HOME_ICON_BUTTON);
    }
}
