package utils.driverUtils;

import exception.ElementNotFoundException;
import exception.MobileElementAttributeException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driverUtils.android_functions.AndroidPhoneControls;

import java.time.Duration;
import java.util.List;

public class SeleniumUtils {
    private WebDriverWait driverWait;
    protected final AppiumDriver driver;
    private final AndroidDriver androidDriver;
    private final AndroidPhoneControls androidPhoneControls;
    private final static int TIME_TO_WAIT = 180;

    public SeleniumUtils(AppiumDriver driver) {
        this.driver = driver;
        this.androidDriver = (AndroidDriver) driver;
        androidPhoneControls = new AndroidPhoneControls(androidDriver);
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT));
    }

    private WebElement returnMobileElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> getListOfWebElement(By locator) {
        return driver.findElements(locator);
    }


    public boolean isElementDisplayed(By locator) {
        try {
            return returnMobileElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }


    public void clickAndHoldElement(By locator) {
        WebElement element = returnMobileElement(locator);
        new Actions(driver).clickAndHold(element).perform();
    }

    private void clearElementField(By locator) {
        clickOnElement(locator);
        returnMobileElement(locator).clear();
    }

    public void clickOnElement(Object element) {
        try {
            if (element instanceof By) {
                waitForClickAble(element);
                returnMobileElement((By) element).click();
            } else if (element instanceof WebElement) {
                waitForClickAble(element);
                ((WebElement) element).click();
            } else {
                throw new IllegalArgumentException("Element must be of type By or WebElement");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Element must be of type By or WebElement");
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            throw new ElementNotFoundException("Element not found", e);
        }
    }


    public void sendValueOnElement(Object element, String valueToSend) {
        try {
            if (element instanceof By) {
                clickOnElement(element);
                clearElementField((By) element);
                returnMobileElement((By) element).sendKeys(valueToSend);
            } else if (element instanceof WebElement) {
                clickOnElement(element);
                ((WebElement) element).clear();
                ((WebElement) element).sendKeys(valueToSend);
            } else {
                throw new IllegalArgumentException("Element must be of type By or WebElement");
            }
            androidPhoneControls.hideKeyboard();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Element must be of type By or WebElement");
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            throw new ElementNotFoundException("Element not found", e);
        }
    }


    public void waitForVisibility(Object element) {
        if (element instanceof By) {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } else if (element instanceof WebElement) {
            driverWait.until(ExpectedConditions.visibilityOf((WebElement) element));
        } else {
            throw new IllegalArgumentException("Element must be of type By or WebElement");
        }
    }

    public void waitForVisibility(Object element, int timeToWait) {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
        if (element instanceof By) {
            localWait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
        } else if (element instanceof WebElement) {
            localWait.until(ExpectedConditions.visibilityOf((WebElement) element));
        } else {
            throw new IllegalArgumentException("Element must be of type By or WebElement");
        }
    }

    public void waitForClickAble(Object element) {
        if (element instanceof By) {
            driverWait.until(ExpectedConditions.elementToBeClickable((By) element));
        } else if (element instanceof WebElement) {
            driverWait.until(ExpectedConditions.elementToBeClickable((WebElement) element));
        } else {
            throw new IllegalArgumentException("Element must be of type By or WebElement");
        }
    }

    public void waitForClickAble(Object element, int timeToWait) {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
        if (element instanceof By) {
            localWait.until(ExpectedConditions.elementToBeClickable((By) element));
        } else if (element instanceof WebElement) {
            localWait.until(ExpectedConditions.elementToBeClickable((WebElement) element));
        } else {
            throw new IllegalArgumentException("Element must be of type By or WebElement");
        }
    }


    public void waitForInvisibility(Object element) {
        if (element instanceof By) {
            driverWait.until(ExpectedConditions.invisibilityOfElementLocated((By) element));
        } else if (element instanceof WebElement) {
            driverWait.until(ExpectedConditions.invisibilityOf((WebElement) element));
        } else {
            throw new IllegalArgumentException("Element must be of type By or WebElement");
        }

    }

    public void waitForInvisibility(Object element, int timeToWait) {
        WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
        if (element instanceof By) {
            driverWait.until(ExpectedConditions.invisibilityOfElementLocated((By) element));
        } else if (element instanceof WebElement) {
            driverWait.until(ExpectedConditions.invisibilityOf((WebElement) element));
        } else {
            throw new IllegalArgumentException("Element must be of type By or WebElement");
        }
    }

    public String getAttribute(Object element, MobileElementAttributes mobileElementAttributes) {
        try {
            if (element instanceof By) {
                return returnMobileElement((By) element).getAttribute(mobileElementAttributes.getName());
            } else if (element instanceof WebElement) {
                return ((WebElement) element).getAttribute(mobileElementAttributes.getName());
            }
            throw new IllegalArgumentException("Element must be of type By or WebElement");

        } catch (NoSuchElementException | StaleElementReferenceException e) {
            throw new MobileElementAttributeException("Attribute not found", e);
        }
    }


    public String refreshDOM() {
        return driver.getPageSource();
    }
}
