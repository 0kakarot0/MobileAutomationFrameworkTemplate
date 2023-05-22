package testBase;

import org.testng.annotations.BeforeClass;
import utils.commonComponents.editTextFields.EditText;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class PageObjects extends BaseClass{
    protected EditText editText;

    @BeforeClass
    public void getLatestDriver() throws MalformedURLException, FileNotFoundException {
      editText = new EditText(DriverManager.getDriver(deviceName));
    }
}
