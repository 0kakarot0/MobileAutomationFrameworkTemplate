package utils.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import utils.JsonUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class AppiumServer {

    private static AppiumDriverLocalService appiumService;
    private static final int PORT = 4723; // Define the Appium Port
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String CONFIG_FILE_PATH = "src/test/resources/testDataFiles/appium_config/server-config.json";
    private static final String APPIUM_CONFIG_FILE_PATH = "src/test/resources/testDataFiles/appium_config/appium_config.json";

    public static void startAppiumServer() {
        System.out.println("Starting Appium Server...");

        // Handle any existing process on the designated port.
        handleExistingProcessOnPort(PORT);

        try {
            AppiumConfigModel config = loadAppiumConfig();

            // Build and configure the Appium service.
            AppiumServiceBuilder serviceBuilder = configureServiceBuilder(config);

            // Start the Appium service.
            startService(serviceBuilder);
            System.out.println("Appium Server Started on Port: " + appiumService.getUrl().getPort());

            // Add a shutdown hook to stop the Appium service when the JVM exits.
            addShutdownHook();
        } catch (Exception e) {
            throw new RuntimeException("Failed to start Appium server", e);
        }
    }

    public static void stopAppiumServer() {
        if (appiumService != null && appiumService.isRunning()) {
            appiumService.stop();
            System.out.println("Appium Server Stopped.");
        }
    }

    // Checks if the designated port is in use and handles any running process.
    private static void handleExistingProcessOnPort(int port) {
        if (isPortInUse(port)) {
            String pid = getProcessIdUsingPort(port);
            if (pid != null) {
                killProcess(pid);
                System.out.println("Killed existing Appium process with PID: " + pid);
            }
        }
    }

    // Checks if a port is in use.
    private static boolean isPortInUse(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false; // Port is available
        } catch (IOException e) {
            return true; // Port is in use
        }
    }

    // Returns the process ID using the specified port.
    private static String getProcessIdUsingPort(int port) {
        String command = OS.contains("win") ? "netstat -ano" : "lsof -i :" + port;
        try {
            Process process = Runtime.getRuntime().exec(command);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (OS.contains("win") && line.contains(":" + port)) {
                        String[] parts = line.trim().split("\\s+");
                        return parts[parts.length - 1]; // PID is the last element in Windows
                    } else if (!OS.contains("win") && line.contains("LISTEN")) {
                        String[] parts = line.trim().split("\\s+");
                        return parts[1]; // PID is the second element in Mac/Linux
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Kills the process with the specified process ID.
    private static void killProcess(String pid) {
        String command = OS.contains("win") ? "taskkill /F /PID " + pid : "kill -9 " + pid;
        try {
            Runtime.getRuntime().exec(command);
            System.out.println("Process " + pid + " terminated.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads the Appium configuration and sets the appropriate paths based on the OS.
    private static AppiumConfigModel loadAppiumConfig() throws IOException {
        AppiumConfigModel config = JsonUtils.readJson(APPIUM_CONFIG_FILE_PATH, AppiumConfigModel.class);
        return config;
    }

    // Configures the AppiumServiceBuilder with the loaded configuration.
    private static AppiumServiceBuilder configureServiceBuilder(AppiumConfigModel config) {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        if (OS.equals("win")) {
            builder.withAppiumJS(new File(config.getJsFilePath().getWindows()));
            builder.usingDriverExecutable(new File(config.getNodePath().getWindows()));
        } else {
            builder.withAppiumJS(new File(config.getJsFilePath().getMacos()));
            builder.usingDriverExecutable(new File(config.getNodePath().getMac()));
        }
        // builder.usingPort(PORT);
        builder.usingAnyFreePort();
        // Pass the config file to Appium
        builder.withArgument(() -> "--config", CONFIG_FILE_PATH);
        // Activate the device-farm plugin
        builder.withArgument(() -> "--use-plugins", "device-farm");
        // Set the base path for Appium (ensuring both server and client match)
        builder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub");
        // Optional: if you want to specify the base path explicitly
        builder.withArgument(() -> "-pa", "/wd/hub");

        builder.withIPAddress("127.0.0.1");
//        builder.withArgument(() -> "--config", CONFIG_FILE_PATH);
        return builder;
    }

    // Starts the Appium service with the given builder.
    private static void startService(AppiumServiceBuilder builder) {
        appiumService = AppiumDriverLocalService.buildService(builder);
        appiumService.start();
    }

    // Adds a shutdown hook to ensure the Appium service is stopped when the JVM exits.
    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(AppiumServer::stopAppiumServer));
    }

    // Returns details of the running Appium server.
    public static Map<String, String> getAppiumServerDetails() {
        Map<String, String> serverDetails = new HashMap<>();
        if (appiumService != null) {
            serverDetails.put("URL", appiumService.getUrl().toString());
            serverDetails.put("PORT", String.valueOf(appiumService.getUrl().getPort()));
        }
        return serverDetails;
    }
}
