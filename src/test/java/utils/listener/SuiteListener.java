package utils.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utils.JsonUtils;
import utils.reporter.ClearJsonDirectory;
import utils.server.AppiumConfigModel;
import utils.server.AppiumServer;

import java.io.IOException;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);

        AppiumServer.startAppiumServer();
        AppiumConfigModel configModel = new AppiumConfigModel();

        try {
            configModel = JsonUtils.readJson("src/test/resources/testDataFiles/appium_config/appium_config.json", AppiumConfigModel.class);
            configModel.setUrl(AppiumServer.getAppiumServerDetails().get("URL"));
            JsonUtils.writeJson("src/test/resources/testDataFiles/appium_config/appium_config.json", configModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
        ClearJsonDirectory.clearJsonDirectory();
    }
}
