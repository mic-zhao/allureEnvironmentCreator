package beforeSuiteExample;

import com.miczhao.github.allureTools.environmentCreator.AllurePropertiesEnvironmentWriter;
import com.miczhao.github.allureTools.environmentCreator.AllureXmlEnvironmentWriter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;

public class FixturesTwo {

    public WebDriver chromeDriver;

    @BeforeMethod
    public void beforeMethod(){
        chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.google.com/");
    }

    @AfterMethod
    public void afterMethod(){
        chromeDriver.quit();
    }

    @BeforeSuite
    public void afterSuite(){
        HashMap<String,String> allureEnvironment = new HashMap<>();
        allureEnvironment.put("Browser","Chrome");
        allureEnvironment.put("Version","102.648.743");
        allureEnvironment.put("Stand_URL","https://www.google.com/");

        AllurePropertiesEnvironmentWriter allurePropertiesEnvironmentWriter = new AllurePropertiesEnvironmentWriter();
        allurePropertiesEnvironmentWriter.createAllureEnvironment(allureEnvironment);
    }
}