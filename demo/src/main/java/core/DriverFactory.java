package core;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");
        String gridUrl = System.getProperty("grid.url");
        boolean isCI = System.getenv("CI") != null;
        WebDriver driver;

        if (gridUrl != null && !gridUrl.trim().isEmpty()) {
            try {
                driver = createRemoteDriver(browser, gridUrl);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Failed to create remote driver", e);
            }
        } else {
            driver = createLocalDriver(browser, isCI);
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    private static WebDriver createLocalDriver(String browser, boolean isCI) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isCI) {
                    chromeOptions.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
                }
                return new ChromeDriver(chromeOptions);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isCI) {
                    firefoxOptions.addArguments("-headless");
                }
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createRemoteDriver(String browser, String gridUrl) throws MalformedURLException {
        MutableCapabilities capabilities;
        switch (browser.toLowerCase()) {
            case "chrome":
                capabilities = new ChromeOptions();
                break;
            case "firefox":
                capabilities = new FirefoxOptions();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser for remote execution: " + browser);
        }
        return new RemoteWebDriver(new URL(gridUrl), capabilities);
    }
}
