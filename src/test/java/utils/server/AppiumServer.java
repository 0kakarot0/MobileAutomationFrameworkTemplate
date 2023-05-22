package utils.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class AppiumServer {

    private static AppiumDriverLocalService appiumService;

    public static void startAppiumServer() {

        // Check if the port is already in use
        int portNumber = 4700;  // You can use any port
        if (checkIfServerIsRunning(portNumber)) {
            // Find the PID of the process using the port
            String pid = findProcessIdUsingPort(portNumber);
            if (pid != null) {
                // Terminate the process using the port
                killProcess(pid);
            }
        }

        String userHome = System.getProperty("user.home");
        String appiumPath = userHome + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingPort(portNumber);
        serviceBuilder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
        serviceBuilder.withAppiumJS(new File(appiumPath));

//        File logFile logFile = new File(userHome + "\\IdeaProjects\\MobileAutomationFramework\\appiumlogs.txt");
//        serviceBuilder.withLogFile(logFile);

        appiumService = AppiumDriverLocalService.buildService(serviceBuilder);
        appiumService.start();
    }


    private static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        }
        return isServerRunning;
    }

    private static String findProcessIdUsingPort(int port) {
        String pid = null;
        try {
            Process p = Runtime.getRuntime().exec("netstat -ano");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(":" + port)) {
                    String[] parts = line.split("\\s+");
                    pid = parts[parts.length - 1];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pid;
    }

    private static void killProcess(String pid) {
        try {
            Runtime.getRuntime().exec("taskkill /F /PID " + pid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopAppiumServer() throws IOException {

        if (appiumService != null) {
            appiumService.stop();
        }
    }

    public static Map<String, String> getAppiumServerDetails() {
        Map<String, String> serverDetails = new HashMap<>();
        if (appiumService != null) {
            int port = appiumService.getUrl().getPort();
            String url = appiumService.getUrl().toString();

            serverDetails.put("port", String.valueOf(port));
            serverDetails.put("url", url);
        }
        return serverDetails;
    }

}
