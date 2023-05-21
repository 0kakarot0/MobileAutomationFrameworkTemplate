package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageBase.driver.PageDriver;

import java.time.Duration;

public class SeleniumUtils extends PageDriver {
    private final WebDriverWait wait;

    public SeleniumUtils(AndroidDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void click(By locator) {
        try {
            returnWebElement(locator).click();
        } catch (NullPointerException e) {
            // Handle the exception (e.g., logging, reporting, or throwing a custom exception)
            // You can modify this part based on your specific error handling needs
            e.printStackTrace();
            System.out.println("Element not found. Cannot perform click.");
        }
    }

    public void sendKeys(By locator, String text) {
        try {
            returnWebElement(locator).sendKeys(text);
        } catch (NullPointerException e) {
            // Handle the exception (e.g., logging, reporting, or throwing a custom exception)
            // You can modify this part based on your specific error handling needs
            e.printStackTrace();
            System.out.println("Element not found. Cannot perform sendKeys.");
        }
    }


    public boolean isDisplayed(By locator) {
        return returnWebElement(locator).isDisplayed();
    }


    private WebElement returnWebElement(By locator) {
        try {
            waitForVisibility();
            return getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            // Handle the exception (e.g., logging, reporting, or throwing a custom exception)
            // You can modify this part based on your specific error handling needs
            e.printStackTrace();
            return null; // or throw a custom exception as per your requirements
        }
    }



    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
