package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

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
        extentTest.fail(failInfo);
    }

    public void testSkip(String reasonToSkip){
        extentTest.skip(reasonToSkip);
    }

    public void testInfo(String testInfo){
        extentTest.info(testInfo);
    }

    public void flushReport(){
        extentReports.flush();
    }
}
