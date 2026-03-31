package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import core.DriverFactory;

import org.testng.annotations.Listeners;
import core.listeners.AllureListener;

@Listeners({AllureListener.class})
public class BaseTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        System.setProperty("browser", browser);
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
