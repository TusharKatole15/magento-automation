package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdHandler {

    public static void closeAdIfPresent(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        if (tryToCloseAd(driver, null, "aswift_3", By.id("dismiss-button"), wait)) return;
        if (tryToCloseAd(driver, "aswift_3", "ad_iframe", By.id("dismiss-button"), wait)) return;
        if (tryToCloseAd(driver, null, "ad_iframe", By.id("dismiss-button"), wait)) return;
        if (tryToCloseAd(driver, null, "aswift_1", By.id("dismiss-button"), wait)) return;
        tryToCloseAd(driver, null, "ad_iframe", By.xpath("//div[contains(text(),'Close ad')]"), wait);
    }

    private static boolean tryToCloseAd(WebDriver driver, String outerIframeId, String innerIframeId,
                                        By closeBtnLocator, WebDriverWait wait) {
        try {
            driver.switchTo().defaultContent();

            if (outerIframeId != null) {
                WebElement outerIframe = driver.findElement(By.id(outerIframeId));
                driver.switchTo().frame(outerIframe);
            }

            WebElement innerIframe = driver.findElement(By.id(innerIframeId));
            driver.switchTo().frame(innerIframe);

            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(closeBtnLocator));
            closeBtn.click();

            System.out.println("Closed ad in iframe: " +
                    (outerIframeId != null ? outerIframeId + " → " : "") + innerIframeId);
            return true;

        } catch (TimeoutException | NoSuchElementException | NoSuchFrameException e) {
            System.out.println("Ad not found or not clickable in iframe: " +
                    (outerIframeId != null ? outerIframeId + " → " : "") + innerIframeId);
            return false;
        }
    }
}
