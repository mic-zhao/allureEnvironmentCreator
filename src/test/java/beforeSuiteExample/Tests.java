package beforeSuiteExample;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Tests extends FixturesTwo {

    @Test(description = "Test writer in afterSuite")
    public void testOne() {
        (new WebDriverWait(chromeDriver, Duration.ofSeconds(10), Duration.ofSeconds(1)))
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//span[text()='Нулевой баланс выбросов углекислого газа с 2007 года']")));
        chromeDriver.findElement(By.xpath("//span[text()='Нулевой баланс выбросов углекислого газа с 2007 года']")).click();
        (new WebDriverWait(chromeDriver, Duration.ofSeconds(20), Duration.ofSeconds(1)))
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//h3[contains(text(),'Лидерство в борьбе с изменением климата')]")));
    }

}
