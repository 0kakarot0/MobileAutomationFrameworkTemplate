package utils.commonComponents.editTextFields;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.seleniumUtils.SeleniumUtils;

public class EditText extends SeleniumUtils {
    private String stepResult = "";
    public EditText(AndroidDriver driver) {
        super(driver);
    }

    public String click(){
        By numberNine = new AppiumBy.ByAccessibilityId("com.veronicaapps.veronica.simplecalculator:id/button_nine");
        try {
            click(numberNine);
            stepResult = "Pass, User able to enter the value";
        } catch (Exception e) {
            stepResult = "Fail, User unable to enter the value";
            e.printStackTrace();
        }
        return stepResult;
    }
}
