package utils.fileReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;



// This class reads device data from a JSON file.
public class DeviceDataReader {
    private static final Logger logger = LogManager.getLogger(DeviceDataReader.class);

    private static final String DEVICES_JSON_FILE_PATH = "D:\\MobileAutomationFramewokTemplate\\src\\test\\resources\\testDataFiles\\devices.json"; // Update with the correct file path

    public static List<DeviceData> getDeviceData() throws FileNotFoundException {
        logger.info("Reading device data from JSON file.");
        Gson gson = new Gson();
        File jsonFile = new File(DEVICES_JSON_FILE_PATH);
        List<DeviceData> deviceDataList = gson.fromJson(new FileReader(jsonFile), new TypeToken<List<DeviceData>>(){}.getType());

        logger.info("Device data read successfully.");
        return deviceDataList;
    }
}
