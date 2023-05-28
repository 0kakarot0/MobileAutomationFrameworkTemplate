package testBase;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import pages.calculator.CalculatorPage;
import pages.logout.Logout;
import utils.commonComponents.buttonActions.ButtonActions;
import utils.commonComponents.dropDowns.DropDown;
import utils.commonComponents.editTextFields.EditText;
import utils.commonComponents.notifications.Notifications;
import utils.commonComponents.validationMessages.Validations;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

// This class initializes the page objects before each test class.
public class PageObjects extends BaseClass {
    protected CalculatorPage calculatorPage;
    protected Logout logout;
    protected ButtonActions buttonActions;
    protected DropDown dropDown;
    protected EditText editText;
    protected Notifications notifications;
    protected Validations validations;

    private static final Logger logger = LogManager.getLogger(PageObjects.class);
    // ... existing code ...

    // This method initializes the page objects before each test class.
    @BeforeClass
    public void getLatestDriver() throws MalformedURLException, FileNotFoundException {
        logger.info("Initializing page objects...");

        calculatorPage = new CalculatorPage(DriverManager.getDriver(deviceName));
        logout = new Logout(DriverManager.getDriver(deviceName));
        buttonActions = new ButtonActions(DriverManager.getDriver(deviceName));
        dropDown = new DropDown(DriverManager.getDriver(deviceName));
        editText = new EditText(DriverManager.getDriver(deviceName));
        notifications = new Notifications(DriverManager.getDriver(deviceName));
        validations = new Validations(DriverManager.getDriver(deviceName));

        logger.info("Page objects initialized.");
    }
}
