package utils.reporter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class JsonMerger {

    /**
     * Entry point to merge all JSON reports.
     * Adjust the paths as needed.
     */

    public static String setMergeReport() {
        String reportDir = "src/test/resources/reports/json_files";
        String mergedJsonPath = "src/test/resources/reports/merged_report.json";
        mergeJsonReports(reportDir, mergedJsonPath);
        return mergedJsonPath;
    }


    /**
     * Merges individual JSON report files into a single JSON file.
     * <p>
     * The merging process uses a HashSet to ensure that duplicate test results
     * (based on a unique identifier combining test name and startTime) are not included.
     *
     * @param directoryPath  The directory containing individual JSON report files.
     * @param outputFilePath The output file path for the merged JSON.
     */
    private static void mergeJsonReports(String directoryPath, String outputFilePath) {
        File jsonDirectory = new File(directoryPath);
        // Use a set to track unique test cases based on a combined identifier
        HashSet<String> uniqueTests = new HashSet<>();
        JsonArray mergedTestArray = new JsonArray();

        if (jsonDirectory.exists() && jsonDirectory.isDirectory()) {
            File[] jsonFiles = jsonDirectory.listFiles();
            if (jsonFiles != null) {
                for (File jsonFile : jsonFiles) {
                    if (jsonFile.isFile() && jsonFile.getName().endsWith(".json")) {
                        try (FileReader reader = new FileReader(jsonFile)) {
                            JsonArray testArray = JsonParser.parseReader(reader).getAsJsonArray();

                            // Process each test entry in the JSON file
                            for (JsonElement testElement : testArray) {
                                JsonObject testObject = testElement.getAsJsonObject();

                                // Construct a unique identifier using test name and start time
                                String testName = testObject.get("name").getAsString().trim();
                                String testTime = testObject.get("startTime").getAsString();
                                String uniqueIdentifier = testName + "_" + testTime;

                                // Skip duplicate tests based on the identifier
                                if (uniqueTests.contains(uniqueIdentifier)) {
                                    continue;
                                }

                                uniqueTests.add(uniqueIdentifier);
                                mergedTestArray.add(testObject);
                            }
                        } catch (IOException e) {
                            System.err.println("Error reading JSON file: " + jsonFile.getName());
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            System.err.println("Directory does not exist or is not a directory: " + directoryPath);
        }

        // Write the merged JSON array to the output file with pretty printing
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(mergedTestArray, writer);
            System.out.println("Merged JSON saved to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error writing merged JSON file.");
            e.printStackTrace();
        }
    }
}

