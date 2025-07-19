package utils;

import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AdHandler {

    private static final Logger logger = LoggerFactory.getLogger(AdHandler.class);

    public static boolean closeAdIfPresent(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            logger.info("Switched to default content to scan for ad iframes.");

            List<WebElement> adIframes = driver.findElements(By.xpath("//iframe[@aria-label='Advertisement']"));
            logger.info("Found {} advertisement iframe(s).", adIframes.size());

            for (WebElement iframe : adIframes) {
                if (iframe.isDisplayed()) {
                    logger.info("Attempting to switch to visible ad iframe.");
                    driver.switchTo().frame(iframe);

                    if (searchAndCloseButton(driver)) {
                        logger.info("Ad dismissed successfully.");
                        return true;
                    }

                    driver.switchTo().parentFrame();
                    logger.info("Returning to parent frame after checking ad iframe.");
                } else {
                    logger.info("Skipping non-visible ad iframe.");
                }
            }

        } catch (Exception e) {
            logger.error("Error while trying to close ad: {}", e.getMessage(), e);
        }

        logger.info("No dismissible ad was found.");
        return false;
    }

    private static boolean searchAndCloseButton(WebDriver driver) {
        try {
            List<WebElement> buttons = driver.findElements(By.id("dismiss-button"));
            for (WebElement btn : buttons) {
                if (btn.isDisplayed()) {
                    btn.click();
                    logger.info("Dismiss button clicked.");
                    return true;
                }
            }

            List<WebElement> nestedIframes = driver.findElements(By.tagName("iframe"));
            logger.info("Found {} nested iframe(s) inside current frame.", nestedIframes.size());

            for (WebElement nested : nestedIframes) {
                if (nested.isDisplayed()) {
                    logger.info("Switching into visible nested iframe.");
                    driver.switchTo().frame(nested);

                    boolean found = searchAndCloseButton(driver);

                    driver.switchTo().parentFrame();
                    logger.info("Returned to parent frame after nested iframe check.");

                    if (found) return true;
                }
            }

        } catch (NoSuchElementException | ElementNotInteractableException e) {
            logger.warn("Dismiss button or iframe not interactable: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while searching inside iframe: {}", e.getMessage(), e);
        }

        return false;
    }
}
