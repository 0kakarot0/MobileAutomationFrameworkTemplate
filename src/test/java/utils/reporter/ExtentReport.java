package utils.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentReport {

    private ExtentReports extentReports;
    private ExtentSparkReporter extentSparkReporter;
    private ExtentTest extentTest;

    public ExtentReport() {
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
            String screenshotPath = "src/main/resources/screenShots/screenShot.png";
            extentTest.fail(failInfo, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            // Delete the screenshot file
            File screenshotFile = new File(screenshotPath);
            if (screenshotFile.delete()) {
                System.out.println("Screenshot file deleted successfully.");
            } else {
                System.out.println("Failed to delete the screenshot file.");
            }
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
        if (stepResult.equalsIgnoreCase("pass")){
            testPass(stepResult);
        } else if (stepResult.equalsIgnoreCase("fail")) {
            testFail(stepResult);
        }else {
            testInfo(stepResult);
        }
    }

    public void flushReport(){
        extentReports.flush();
    }
}
