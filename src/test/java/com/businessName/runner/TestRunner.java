package com.businessName.runner;

import com.businessName.poms.LoginPom;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
        glue = "com/businessName/steps"
       // plugin = {"pretty","html:src/test/resources/reports/html-e2e-report.html"}
)


public class TestRunner {

    public static WebDriver driver;
    public static LoginPom loginPom;

    @BeforeClass
    public static void setup() {
        File file = new File("src/test/resources/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver",file.getAbsolutePath());
        driver = new FirefoxDriver();
        loginPom = new LoginPom(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterClass
    public static void tearDown() {driver.quit();}

}
