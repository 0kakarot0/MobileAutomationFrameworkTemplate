package utils.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.reporter.ExtentReport;

public class TestListener implements ITestListener {

    private ExtentReport extentReport;

    @Override
    public void onStart(ITestContext context) {
        extentReport = new ExtentReport();
        extentReport.createReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        String testDescription = result.getMethod().getDescription();
        extentReport.createTest(testName, testDescription);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentReport.testPass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentReport.testFail("Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentReport.testSkip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flushReport();
    }
}

