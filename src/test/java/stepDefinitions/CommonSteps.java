package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.Given;
import utils.ConfigReader;
import utils.WaitUtils;

public class CommonSteps extends BaseTest {

    @Given("User opens the base website")
    public void openMagentoWebsite(){
        if (driver == null) {
            launchBrowser();
        }
        driver.get(ConfigReader.getProperty("base.url"));
        logger.info("Navigated to: " + ConfigReader.getProperty("base.url"));

        WaitUtils.waitForTitleContains(driver,"Home Page",20);
        logger.info("Home Page loaded successfully.");
    }


}
