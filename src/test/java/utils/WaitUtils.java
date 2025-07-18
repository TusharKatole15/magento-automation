package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriverWait getWait(WebDriver driver, long timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public static void waitForTitleContains(WebDriver driver, String partialTitle, long timeoutInSeconds) {
        getWait(driver, timeoutInSeconds).until(ExpectedConditions.titleContains(partialTitle));
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, long timeoutInSeconds) {
        return getWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
    }

}
