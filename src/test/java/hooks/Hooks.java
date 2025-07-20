package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private static boolean isAllurePathSet = false;

    @BeforeAll
    public static void cleanAllureResults() throws IOException {
        FileUtils.deleteDirectory(new File("test-output/allure-results"));
        FileUtils.deleteDirectory(new File("test-output/allure-report"));
    }


    @Before
    public void setUp(Scenario scenario) {
        if (!isAllurePathSet) {
            System.setProperty("allure.results.directory", "test-output/allure-results");
            logger.info("Allure results directory set to: test-output/allure-results");
            isAllurePathSet = true;
        }

        logger.info("========== Starting Scenario: " + scenario.getName() + " ==========");
        BaseTest.launchBrowser();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario Failed: " + scenario.getName());
            takeScreenshot(scenario);
        } else {
            logger.info("Scenario Passed: " + scenario.getName());
        }

        BaseTest.quitDriver();
        logger.info("========== Ending Scenario ==========");
    }

    private void takeScreenshot(Scenario scenario) {
        File screenshot = ((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.FILE);

        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new java.util.Date());

        String sanitizedScenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
        String screenshotName = "test-output/screenshots/" + sanitizedScenarioName + "_" + timestamp + ".png";

        File screenshotDir = new File("test-output/screenshots/");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        try {
            Files.copy(screenshot.toPath(), new File(screenshotName).toPath(), StandardCopyOption.REPLACE_EXISTING);
            logger.info("Screenshot saved at: " + screenshotName);
        } catch (IOException e) {
            logger.error("Error saving screenshot: " + e.getMessage());
        }
    }
}
