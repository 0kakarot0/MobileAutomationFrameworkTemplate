package utils.seleniumUtils;

import com.google.common.io.Files;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageBase.driver.PageDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;

public class SeleniumUtils extends PageDriver {
    private final WebDriverWait wait;

    public SeleniumUtils(AndroidDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void click(By locator) {
        try {
            WebElement element = returnWebElement(locator);
            element.click();
        } catch (Exception e) {
            // Handle the exception (e.g., logging, reporting, or throwing a custom exception)
            // You can modify this part based on your specific error handling needs
            System.out.println("Element not found. Cannot perform sendKeys." + e);
            throw e;
        }

    }

    public void sendKeys(By locator, String text) {
        WebElement element = returnWebElement(locator);
        if (element != null) {
            try {
                element.sendKeys(text);
            } catch (NoSuchElementException e) {
                // Handle the exception (e.g., logging, reporting, or throwing a custom exception)
                // You can modify this part based on your specific error handling needs
                e.printStackTrace();
            }
        } else {
            System.out.println("Element not found. Cannot perform sendKeys.");
        }
    }


    public boolean isDisplayed(By locator) {
        return returnWebElement(locator).isDisplayed();
    }


    private WebElement returnWebElement(By locator) {
        try {
            waitForVisibility(locator);
            return getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            // Handle the exception (e.g., logging, reporting, or throwing a custom exception)
            // You can modify this part based on your specific error handling needs
            getFailedElementScreenShot();
            e.printStackTrace();
            return null; // or throw a custom exception as per your requirements
        }
    }


    public void getFailedElementScreenShot() {

        String fileName = "screenShot.png";
        String pathToSaveFile = "src/main/resources/screenShots/" + fileName;
        try {
            // Capture screenshot of the entire screen
            var getScreenShot = (TakesScreenshot) getDriver();
            File screenShot = getScreenShot.getScreenshotAs(OutputType.FILE);

            // Load the screenshot as an image
            BufferedImage image = ImageIO.read(screenShot);

            // Apply red overlay to the image
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(new Color(255, 0, 0, 128)); // Red color with transparency
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
            graphics.dispose();

            // Save the modified image
            Files.move(screenShot, new File(pathToSaveFile));
        } catch (Exception exception) {
            exception.getCause();
            exception.getStackTrace();
            exception.getMessage();
        }

    }

    public void waitForVisibility(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            getFailedElementScreenShot();
        }
    }

}
