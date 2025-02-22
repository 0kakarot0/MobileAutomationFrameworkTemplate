package utils.driverUtils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ElementBounds extends SeleniumUtils {
    public ElementBounds(AppiumDriver driver) {
        super(driver);
    }

    private List<Integer> returnCoordinateList(String getBound) {
        List<Integer> arrToReturn = new ArrayList<>();
        String[] arrToGet = getBound.split("[\\[\\],]");

        for (int i = 1; i < arrToGet.length - 1; i += 2) { // Skip the first and last element
            try {
                arrToReturn.add(Integer.parseInt(arrToGet[i].trim()));
                arrToReturn.add(Integer.parseInt(arrToGet[i + 1].trim()));
            } catch (NumberFormatException e) {
                System.err.println("Invalid coordinate format: " + arrToGet[i] + "," + arrToGet[i + 1]);
            }
        }
        return arrToReturn;
    }

    public List<Integer> getCoordinates(By locator) {
        waitForClickAble(locator);
        String coordinateString = getAttribute(locator, MobileElementAttributes.MOBILE_ATTRIBUTE_BOUNDS);
        return returnCoordinateList(coordinateString);
    }
}
