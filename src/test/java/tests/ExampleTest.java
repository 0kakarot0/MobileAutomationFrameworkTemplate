package tests;

import org.testng.annotations.Test;
import testBase.PageObjects;

public class ExampleTest extends PageObjects {

    // These are the example test first one is with correct id and second one with incorrect id
    @Test
    public void tapOnNineOnCalculator() {
        extentReport.createTest("Tap On Calculator Digit Nine", "Tap on the Digit Nine in the Calculator app ");
        String stepResult = calculatorPage.click();
        extentReport.logStepResult(stepResult);
    }

    @Test
    public void tapOnEightOnCalculator() {
        extentReport.createTest("Tap On Calculator Digit Eight", "Tap on the Digit Eight in the Calculator app ");
        String stepResult = calculatorPage.clickWithIncorrectID();
        extentReport.logStepResult(stepResult);
    }
}
