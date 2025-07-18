package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    public static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static WebDriver driver;

    public static void launchBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        long implicitWait;
        long pageLoadTimeout;
        try {
            implicitWait = Long.parseLong(ConfigReader.getProperty("implicit.wait"));
            pageLoadTimeout = Long.parseLong(ConfigReader.getProperty("page.load.timeout"));
        } catch (NumberFormatException e) {
            logger.error("Invalid timeout value in config.properties", e);
            throw e;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        logger.info("Launched Chrome Browser and maximized the window.");

    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            logger.info("Closed the browser and quit the WebDriver.");
        }
    }

}
