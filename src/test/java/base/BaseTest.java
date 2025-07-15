package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static WebDriver driver;

    public static void launchBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        logger.info("Launched Chrome Browser and maximized the window.");

        driver.get("https://magento.softwaretestingboard.com/");
        logger.info("Navigated to the Magento homepage.");
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
