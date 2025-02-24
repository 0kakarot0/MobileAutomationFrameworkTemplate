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
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ExtentReport {

    private static final Logger logger = LogManager.getLogger(ExtentReport.class);

    private ExtentReports extentReports;
    private ExtentTest currentTest; // Pointer to the currently active test node

    // Maps for hierarchical nodes
    private final Map<String, ExtentTest> parentTestsMap = new HashMap<>();
    private final Map<String, ExtentTest> childTestsMap = new HashMap<>();
    private final Map<String, ExtentTest> grandChildTestsMap = new HashMap<>();

    // Report paths and configuration
    private static final String JSON_REPORT_DIRECTORY = System.getProperty("user.dir") + "/src/test/resources/reports/json_files";
    private static final String HTML_REPORT_PATH = System.getProperty("user.dir") + "/src/test/resources/reports/final_report.html";
    private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/src/main/resources/screenShots/screenShot.png";

    // Formatter for unique timestamp generation
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    // Constructor initializes the ExtentReports instance
    public ExtentReport() {
        extentReports = new ExtentReports();
    }

    /**
     * Creates a JSON report file.
     * <p>
     * The report will be stored in a dedicated JSON folder with a unique timestamp.
     */
    public void createReport() {
        File reportDir = new File(JSON_REPORT_DIRECTORY);
        if (reportDir.exists() && reportDir.isDirectory()) {
            System.out.println("Yes, the JSON report directory exists.");
        } else {
            System.out.println("No, the JSON report directory does not exist. Creating it...");
            if (!reportDir.mkdirs()) {
                logger.error("Failed to create JSON report directory: " + JSON_REPORT_DIRECTORY);
            }
        }
        // Generate a unique timestamp and JSON filename
        String timestamp = dateFormat.format(new Date());
        String jsonFileName = "extent_" + timestamp + ".json";
        String jsonPath = JSON_REPORT_DIRECTORY + "/" + jsonFileName;

        // Initialize the JsonFormatter with the JSON path
        JsonFormatter jsonFormatter = new JsonFormatter(jsonPath);
        try {
            // This line ensures that a domain is created from the JSON file if needed
            extentReports.createDomainFromJsonArchive(jsonPath);
        } catch (IOException e) {
            logger.error("Error creating domain from JSON archive", e);
            throw new RuntimeException(e);
        }
        // Attach the JsonFormatter to the ExtentReports instance
        extentReports.attachReporter(jsonFormatter);
    }

    /**
     * Merges all JSON reports into a single HTML report.
     * <p>
     * It reads all JSON files from the designated directory, creates a merged report using ExtentSparkReporter,
     * and applies custom configurations (CSS/JS).
     */
    public void mergeReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("src/test/resources/reports/final_report.html");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        String mergedJsonPath = JsonMerger.setMergeReport();

        try {
            extentReports.createDomainFromJsonArchive(mergedJsonPath);
        } catch (IOException e) {
            System.err.println("Error reading merged JSON file.");
            e.printStackTrace();
        }

        extentReports.flush();
        System.out.println("Extent Report generated: final_report.html");
    }

    /**
     * Configures the HTML report appearance and view order.
     *
     * @param mergedSpark the ExtentSparkReporter instance to be configured.
     */
    private void extentReportConfig(ExtentSparkReporter mergedSpark) {
        String cssFilePath = "extentReport.css";
        String jsFilePath = "reportConfigJs.js";
        String cssContent = createStringFromFileContent(cssFilePath);
        String jsContent = createStringFromFileContent(jsFilePath);
        mergedSpark.config().setDocumentTitle("Jazz 3.00 Automation Report");
        mergedSpark.config().setTheme(Theme.DARK);
        mergedSpark.config().setCss(cssContent);
        mergedSpark.config().setJs(jsContent);
        mergedSpark.viewConfigurer().viewOrder().as(new ViewName[]{
                ViewName.DASHBOARD,
                ViewName.TEST,
                ViewName.CATEGORY,
                ViewName.DEVICE
        }).apply();
    }

    /**
     * Reads a file and returns its content as a String.
     *
     * @param filePath the path to the file.
     * @return file content as a String.
     */
    private String createStringFromFileContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            logger.error("Error reading file: " + filePath, e);
            throw new RuntimeException(e);
        }
    }

    // ---------------- Hierarchical Test Node Methods ----------------

    /**
     * Creates a parent test node.
     *
     * @param testName        the name of the parent test.
     * @param testDescription the description of the parent test.
     */
    public void createParentTest(String testName, String testDescription) {
        if (!parentTestsMap.containsKey(testName)) {
            ExtentTest parentTest = extentReports.createTest(testName, testDescription)
                    .assignCategory("Parent");
            parentTestsMap.put(testName, parentTest);
            currentTest = parentTest; // Set as active test node
        }
    }

    /**
     * Creates a child test node under an existing parent.
     *
     * @param parentTestName  the parent test node name.
     * @param childTestName   the child test node name.
     * @param testDescription the description for the child test.
     */
    public void createChildTest(String parentTestName, String childTestName, String testDescription) {
        if (parentTestsMap.containsKey(parentTestName)) {
            if (!childTestsMap.containsKey(childTestName)) {
                ExtentTest parentTest = parentTestsMap.get(parentTestName);
                ExtentTest childTest = parentTest.createNode(childTestName, testDescription)
                        .assignCategory("Child");
                childTestsMap.put(childTestName, childTest);
                currentTest = childTest;
            }
        } else {
            logger.warn("Parent test '{}' does not exist. Create it first.", parentTestName);
        }
    }

    /**
     * Creates a grandchild test node under an existing child.
     *
     * @param parentTestName     the parent test node name.
     * @param childTestName      the child test node name.
     * @param grandChildTestName the grandchild test node name.
     * @param testDescription    the description for the grandchild test.
     */
    public void createGrandChildTest(String parentTestName, String childTestName, String grandChildTestName, String testDescription) {
        if (parentTestsMap.containsKey(parentTestName) && childTestsMap.containsKey(childTestName)) {
            if (!grandChildTestsMap.containsKey(grandChildTestName)) {
                ExtentTest childTest = childTestsMap.get(childTestName);
                ExtentTest grandChildTest = childTest.createNode(grandChildTestName, testDescription)
                        .assignCategory("GrandChild");
                grandChildTestsMap.put(grandChildTestName, grandChildTest);
                currentTest = grandChildTest;
            }
        } else {
            logger.error("Parent '{}' or Child '{}' not found for grandchild '{}'.", parentTestName, childTestName, grandChildTestName);
        }
    }

    // ---------------- Logging Methods ----------------

    public void testInfo(String message) {
        if (currentTest != null) {
            currentTest.info(message);
        } else {
            logger.warn("No active test node to log info: {}", message);
        }
    }

    public void testPass(String message) {
        if (currentTest != null) {
            currentTest.pass(message);
        } else {
            logger.warn("No active test node to log pass: {}", message);
        }
    }

    public void testFail(String message) {
        if (currentTest != null) {
            try {
                File screenshotFile = new File(SCREENSHOT_PATH);
                if (screenshotFile.exists()) {
                    FileInputStream fis = new FileInputStream(screenshotFile);
                    byte[] imageBytes = IOUtils.toByteArray(fis);
                    String base64 = Base64.getEncoder().encodeToString(imageBytes);
                    currentTest.fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
                } else {
                    currentTest.fail(message);
                }
            } catch (IOException e) {
                currentTest.fail(message + " (Screenshot attachment failed: " + e.getMessage() + ")");
                logger.error("Error attaching screenshot", e);
            }
        } else {
            logger.warn("No active test node to log failure: {}", message);
        }
    }

    public void testSkip(String message) {
        if (currentTest != null) {
            currentTest.skip(message);
        } else {
            logger.warn("No active test node to log skip: {}", message);
        }
    }

    /**
     * Logs a test step by examining the first word of the input.
     * <p>
     * Expected formats:
     * <ul>
     *   <li>"Pass &lt;message&gt;"</li>
     *   <li>"Fail &lt;message&gt;"</li>
     *   <li>"Skip &lt;message&gt;"</li>
     *   <li>"Info &lt;message&gt;" or any other prefix defaults to info.</li>
     * </ul>
     *
     * @param stepResult the test step string.
     */
    public void logStepResult(String stepResult) {
        if (stepResult == null || stepResult.trim().isEmpty()) {
            logger.warn("Empty step result provided.");
            return;
        }
        String[] parts = stepResult.trim().split("\\s+", 2);
        String prefix = parts[0].toLowerCase();
        String message = parts.length > 1 ? parts[1] : "";

        switch (prefix) {
            case "pass":
                testPass(message);
                break;
            case "fail":
                testFail(message);
                break;
            case "skip":
                testSkip(message);
                break;
            case "info":
                testInfo(message);
                break;
            default:
                // If the prefix isn't recognized, log the entire step as info.
                testInfo(stepResult);
                break;
        }
    }

    /**
     * Flushes the current ExtentReports instance.
     */
    public void flushReport() {
        extentReports.flush();
    }

    // ---------------- Additional Enhancements ----------------

    /**
     * Adds system-level information to the report.
     *
     * @param key   the info key.
     * @param value the info value.
     */
    public void addSystemInfo(String key, String value) {
        extentReports.setSystemInfo(key, value);
    }

    /**
     * Adds metadata to a parent test.
     *
     * @param testName the name of the parent test.
     * @param key      the metadata key.
     * @param value    the metadata value.
     */
    public void addTestMetadata(String testName, String key, String value) {
        if (parentTestsMap.containsKey(testName)) {
            parentTestsMap.get(testName).info("Metadata - " + key + ": " + value);
        } else {
            logger.warn("Parent test '{}' not found for adding metadata.", testName);
        }
    }
}