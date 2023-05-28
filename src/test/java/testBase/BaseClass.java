package testBase;

import io.appium.java_client.android.AndroidDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.email.EmailSender;
import utils.reporter.ExtentReport;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Map;

// This class sets up and tears down the test environment before and after each suite.
public class BaseClass {
    public static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
    public String deviceName;
    public ExtentReport extentReport;
    private EmailSender emailSender;
    private static final Logger logger = LogManager.getLogger(BaseClass.class);


    // This method sets up the test environment before each suite.
    @BeforeSuite
    public void setUp(ITestContext context) throws MalformedURLException, FileNotFoundException {
        logger.info("Setting up test suite...");

        // Retrieve test parameters from the TestNG XML file
        Map<String, String> testParams = context.getCurrentXmlTest().getAllParameters();

        // Extract the deviceName parameter
        deviceName = testParams.get("deviceName");

        // Set up the AndroidDriver using the DriverManager class
        driver.set(DriverManager.getDriver(deviceName));
        context.setAttribute("driver", driver);

        extentReport = new ExtentReport(driver);
        emailSender = new EmailSender(driver);
        extentReport.createReport();

        // Set the implicit wait timeout to 10 seconds
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        logger.info("Test suite setup complete.");
    }

    // This method tears down the test environment after each suite.
    @AfterSuite
    public void tearDown() throws MalformedURLException, FileNotFoundException {
        logger.info("Tearing down test suite...");

        // Finalize and generate the extent report by flushing any pending logs and resources.
        extentReport.flushReport();

        /* *********************
         * Uncomment the below code when you need to send the extent report via email
         * */

        File reportPath = new File("src/test/resources/reports/spark.html");
        emailSender.sendEmail(
                "Automation Test Report",
                "Please find the attach spark.html file in attachments"
                , reportPath);


        // Close the application and terminate the driver session
        DriverManager.getDriver(deviceName).quit();
        logger.info("Test suite teardown complete.");
    }
}