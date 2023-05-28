package utils.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


public class ExtentReport {

    private static final Logger logger = LogManager.getLogger(ExtentReport.class);

    private ExtentReports extentReports;
    private ExtentSparkReporter extentSparkReporter;
    private ExtentTest extentTest;

    // Constructor
    public ExtentReport(ThreadLocal<AndroidDriver> driver) {
        extentReports = new ExtentReports();
    }

    // Creates the report
    public void createReport() {
        extentSparkReporter = new ExtentSparkReporter("src/test/resources/reports/spark.html");
        extentReports.attachReporter(extentSparkReporter);
    }

    // Creates a new test in the report
    public void createTest(String testName, String testDescription){
        extentTest = extentReports.createTest(testName,testDescription);
    }

    // Logs a passed test step
    public void testPass(String passInfo){
        extentTest.pass(passInfo);
    }

    // Logs a failed test step
    public void testFail(String failInfo){
        try {
            String screenshotPath = "src/main/resources/screenShots/screenShot.png";
            InputStream in = new FileInputStream(screenshotPath);
            byte[] imageBytes = IOUtils.toByteArray(in);
            String base64 = Base64.getEncoder().encodeToString(imageBytes);

            extentTest.fail(failInfo, MediaEntityBuilder.createScreenCaptureFromPath("data:image/png;base64,"+base64).build());
        } catch (IOException e) {
            logger.error("Error while adding screenshot for failed test", e);
        }
    }

    // Logs a skipped test step
    public void testSkip(String reasonToSkip){
        extentTest.skip(reasonToSkip);
    }

    // Logs an informational message
    public void testInfo(String testInfo){
        extentTest.info(testInfo);
    }

    // Logs the result of a test step
    public void logStepResult(String stepResult){
        if (stepResult.contains("Pass")){
            testPass(stepResult);
        } else if (stepResult.contains("Fail")) {
            testFail(stepResult);
        }else {
            testInfo(stepResult);
        }
    }

    // Flushes the report and deletes the screenshot file
    public void flushReport(){
        extentReports.flush();
        String screenshotPath = "src/main/resources/screenShots/screenShot.png";
        // Delete the screenshot file
        File screenshotFile = new File(screenshotPath);
        if (screenshotFile.delete()) {
            logger.info("Screenshot file deleted successfully.");
        } else {
            logger.error("Failed to delete the screenshot file.");
        }
    }
}
