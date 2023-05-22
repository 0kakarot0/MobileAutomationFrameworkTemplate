package utils.listener;

import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.DriverManager;
import utils.reporter.ExtentReport;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Map;

import static testBase.BaseClass.driver;

public class TestListener implements ITestListener {

    private ExtentReport extentReport;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Starting");
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}

