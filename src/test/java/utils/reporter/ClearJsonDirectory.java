package utils.reporter;

import java.io.File;

public class ClearJsonDirectory {
    public static void clearJsonDirectory() {
        String jsonDirectoryPath = "src/test/resources/reports/json_files";
        File jsonDirectory = new File(jsonDirectoryPath);
        File[] jsonFiles = jsonDirectory.listFiles();
        if (jsonFiles != null) {
            for (File file : jsonFiles) {
                file.delete();
            }
        }
    }
}
