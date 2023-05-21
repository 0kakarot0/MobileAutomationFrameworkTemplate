package tesBase;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.email.EmailSender;

import java.io.File;

public class BaseClass {
    private EmailSender emailSender;

    @BeforeSuite
    public void setUp(){

    }



    @AfterSuite
    public void tearDown(){
        File reportPath = new File("src/test/resources/reports/spark.html");
        emailSender.sendEmail("","",reportPath);
    }
}
