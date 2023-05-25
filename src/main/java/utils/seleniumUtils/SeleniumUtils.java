package utils.seleniumUtils;

import com.google.common.io.Files;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SeleniumUtils {
    private final WebDriverWait wait;
    public String stepResult = "";
    private static final Logger logger = Logger.getLogger(SeleniumUtils.class);
    private AndroidDriver driver;

    public SeleniumUtils(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(By locator) {
        try {
            WebElement element = returnWebElement(locator);
            waitForClickable(locator);
            element.click();
        } catch (Exception e) {
            logger.error("Element not found. Cannot perform click.", e);
            throw e;
        }
    }

    public void sendKeys(By locator, String text) {
        try {
            WebElement element = returnWebElement(locator);
            element.sendKeys(text);
        } catch (NoSuchElementException e) {
            logger.error("Element not found. Cannot perform sendKeys.", e);
            throw e;
        }
    }

    public boolean isDisplayed(By locator) {
        return returnWebElement(locator).isDisplayed();
    }


    public String returnAttribute(By locator, String attribute) {
        try {
            WebElement element = returnWebElement(locator);
            return element.getAttribute(attribute);
        } catch (NoSuchElementException e) {
            logger.error("Element not found. Cannot return attribute.", e);
            throw e;
        }
    }

    public String returnText(By locator) {
        try {
            WebElement element = returnWebElement(locator);
            return element.getText();
        } catch (NoSuchElementException e) {
            logger.error("Element not found. Cannot return text.", e);
            throw e;
        }
    }

    public String returnContentDesc(By locator) {
        return returnAttribute(locator, "contentDescription");
    }

    public List<WebElement> returnList(By locator) {
        try {
            waitForVisibility(locator);
            return driver.findElements(locator);
        } catch (NoSuchElementException e) {
            logger.error("Elements not found.", e);
            throw e;
        }
    }


    private WebElement returnWebElement(By locator) {
        try {
            waitForVisibility(locator);
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            getFailedElementScreenShot();
            logger.error("Element not found.", e);
            throw e;
        }
    }

    public void getFailedElementScreenShot() throws IOException {
        String fileName = "screenShot.png";
        String pathToSaveFile = "src/main/resources/screenShots/" + fileName;
        try {
            var getScreenShot = (TakesScreenshot) driver;
            File screenShot = getScreenShot.getScreenshotAs(OutputType.FILE);
            BufferedImage image = ImageIO.read(screenShot);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(new Color(255, 0, 0, 128));
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
            graphics.dispose();
            Files.move(screenShot, new File(pathToSaveFile));
        } catch (Exception exception) {
            logger.error("Failed to capture screenshot.", exception);
            throw exception;
        }
    }

    public void waitForClickable(By locator) {
        try {
            waitForVisibility(locator);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            getFailedElementScreenShot();
            logger.error("Element not clickable after waiting.", e);
            throw e;
        }
    }

    public void waitForVisibility(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            getFailedElementScreenShot();
            logger.error("Element not visible after waiting.", e);
            throw e;
        }
    }
}
