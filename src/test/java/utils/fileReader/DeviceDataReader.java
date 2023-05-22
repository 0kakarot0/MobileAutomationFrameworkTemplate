package utils.fileReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class DeviceDataReader {
    private static final String DEVICES_JSON_FILE_PATH = "D:\\MobileAutomationFramewokTemplate\\src\\test\\resources\\testDataFiles\\devices.json"; // Update with the correct file path

    public static List<DeviceData> getDeviceData() throws FileNotFoundException {
        Gson gson = new Gson();
        File jsonFile = new File(DEVICES_JSON_FILE_PATH);
        List<DeviceData> deviceDataList = gson.fromJson(new FileReader(jsonFile), new TypeToken<List<DeviceData>>(){}.getType());

        return deviceDataList;
    }
}
