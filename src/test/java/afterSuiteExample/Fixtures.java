package afterSuiteExample;

import com.miczhao.github.allureTools.environmentCreator.AllureXmlEnvironmentWriter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class Fixtures {

    @BeforeMethod
    public void beforeMethod(){

    }

    @AfterMethod
    public void afterMethod(){

    }

    @AfterSuite
    public void afterSuite(){
        AllureXmlEnvironmentWriter allureXmlEnvironmentWriter = new AllureXmlEnvironmentWriter();
    }
}
