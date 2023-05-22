package utils.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

public class ExtentReport {

    private ExtentReports extentReports;
    private ExtentSparkReporter extentSparkReporter;
    private ExtentTest extentTest;

    public ExtentReport(ThreadLocal<AndroidDriver> driver) {
        extentReports = new ExtentReports();

    }

    public void createReport() {
        extentSparkReporter = new ExtentSparkReporter("src/test/resources/reports/spark.html");
        extentReports.attachReporter(extentSparkReporter);
    }

    public void createTest(String testName, String testDescription){
        extentTest = extentReports.createTest(testName,testDescription);
    }


    public void testPass(String passInfo){
        extentTest.pass(passInfo);
    }

    public void testFail(String failInfo){
        try {
            String screenshotPath = "src/main/resources/screenShots/screenShot.png";  // Added dot at the start
            InputStream in = new FileInputStream(screenshotPath);
            byte[] imageBytes = IOUtils.toByteArray(in);
            String base64 = Base64.getEncoder().encodeToString(imageBytes);

            extentTest.fail(failInfo, MediaEntityBuilder.createScreenCaptureFromPath("data:image/png;base64,"+base64).build());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., logging, reporting, or throwing a custom exception)
            // You can modify this part based on your specific error handling needs
        }
    }

    public void testSkip(String reasonToSkip){
        extentTest.skip(reasonToSkip);
    }

    public void testInfo(String testInfo){
        extentTest.info(testInfo);
    }


    public void logStepResult(String stepResult){
        if (stepResult.contains("Pass")){
            testPass(stepResult);
        } else if (stepResult.contains("Fail")) {
            testFail(stepResult);
        }else {
            testInfo(stepResult);
        }
    }

    public void flushReport(){
        extentReports.flush();
        String screenshotPath = "src/main/resources/screenShots/screenShot.png";
        // Delete the screenshot file
        File screenshotFile = new File(screenshotPath);
        if (screenshotFile.delete()) {
            System.out.println("Screenshot file deleted successfully.");
        } else {
            System.out.println("Failed to delete the screenshot file.");
        }
    }
}
