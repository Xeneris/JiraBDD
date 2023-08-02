package DriverManager;

import ConfigReader.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DriverManager {
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private String runMode;
    private String remoteWebDriverUrl;
    private String browserType;

    public DriverManager() {
        setUp();
    }

    public void setUp() {
        runMode = ConfigReader.getRunMode();
        remoteWebDriverUrl = ConfigReader.getRemoteUrl();
        browserType = ConfigReader.getBrowserType();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("start-maximized");

        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if ("LOCAL".equalsIgnoreCase(runMode)) {
            switch (browserType) {
                case "FIREFOX":
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "CHROME":
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }
        } else if ("REMOTE".equalsIgnoreCase(runMode)) {
            try {
                if (Objects.equals(browserType, "CHROME")) {
                    driver = new RemoteWebDriver(new URL(remoteWebDriverUrl), chromeOptions);
                } else {
                    driver = new RemoteWebDriver(new URL(remoteWebDriverUrl), firefoxOptions);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize remote webdriver", e);
            }
        }

        if (driver != null) {
            wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Wait<WebDriver> getWait() {
        return wait;
    }

    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver = null;
                wait = null;
            }
        }
    }

    public void goTo(String url){
        driver.get(url);
    }
}