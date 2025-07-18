package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.BasePage;
import pages.DashboardPage;
import pages.SignInPage;
import pages.SignUpPage;
import utils.AdHandler;
import utils.EmailGenerator;
import utils.WaitUtils;

public class SignUpAndSignIn extends BaseTest {

    BasePage basePage = new BasePage(driver);
    SignUpPage signUpPage = new SignUpPage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    SignInPage signInPage = new SignInPage(driver);

    String email;
    String firstname;
    String lastname;


    @When("User selects create an account option")
    public void clickCreateAccountBtn(){
        basePage.createAccountBtn.click();
        logger.info("Clicked on create account button.");
    }

    @Then("User should be navigated to create a new account page")
    public void verifyCreateAccountPage() throws InterruptedException {
        String title = driver.getTitle();

        if(!title.equals("Create New Customer Account")){
            AdHandler.closeAdIfPresent(driver);
            driver.switchTo().defaultContent();
            title = driver.getTitle();
        }

        try {
            Assert.assertEquals(title, "Create New Customer Account");
            logger.info("Verified page title successfully.");
        } catch (AssertionError e) {
            logger.error("Page title mismatch. Found: " + title, e);
            throw e;
        }
    }

    @Then("User enters first name as {string} and last name as {string}")
    public void enterFirstAndLastName(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;

        if (!signUpPage.submitBtn.isDisplayed()){
            AdHandler.closeAdIfPresent(driver);
            driver.switchTo().defaultContent();
        }

        signUpPage.firstname.sendKeys(firstname);
        logger.info("Entered First name");
        signUpPage.lastname.sendKeys(lastname);
        logger.info("Entered Last name");
    }

    @Then("User enters email, password as {string} and confirm password as {string}")
    public void enterEmailAndPasswords(String password, String confirmPassword){
        email = EmailGenerator.generateRandomEmail();

        signUpPage.email.sendKeys(email);
        logger.info("Entered email");
        signUpPage.password.sendKeys(password);
        logger.info("Entered password");
        signUpPage.confirmPassword.sendKeys(confirmPassword);
        logger.info("Entered confirm password");
    }

    @Then("User submits the sign-up form")
    public void submitSignUpForm(){
        signUpPage.submitBtn.click();
        logger.info("Clicked on submit button.");
    }

    @Then("User should be registered and logged in")
    public void verifyLogin(){
        String title = driver.getTitle();
        try{
            Assert.assertEquals(title,"My Account");
            logger.info("Verified Login successfully.");
        } catch (AssertionError e){
            logger.error("Page title mismatch. Found: " + title, e);
            throw e;
        }
    }

    @When("User Signs out")
    public void signOut(){
        WaitUtils.waitForElementToBeClickable(driver, dashboardPage.expandOptions, 10).click();
        dashboardPage.signOutBtn.click();
        logger.info("Clicked on Sign Out button");
    }

    @Then("User is redirected to Home Page")
    public void verifyHomePage() throws InterruptedException {
        WaitUtils.waitForTitleContains(driver, "Home Page", 10);
        String title = driver.getTitle();
        try {
            Assert.assertEquals(title, "Home Page");
            logger.info("Verified Home Page successfully.");
        } catch (AssertionError e) {
            logger.error("Page title mismatch. Found: " + title, e);
            throw e;
        }
    }

    @When("User selects Sign In option")
    public void clickOnSignInBtn(){
        WaitUtils.waitForElementToBeClickable(driver, basePage.signInBtn, 10).click();
        logger.info("Clicked on Sign In Button.");
    }

    @Then("User is redirected to Sign In Page")
    public void verifySignInPage(){
        String title = driver.getTitle();
        try{
            Assert.assertEquals(title,"Customer Login");
            logger.info("Verified Sign In Page successfully.");
        } catch (AssertionError e){
            logger.error("Page title mismatch. Found: " + title, e);
            throw e;
        }
    }

    @When("User enters email and password as {string} and submits the form")
    public void enterCredentials(String password){
        signInPage.email.sendKeys(email);
        logger.info("Entered email.");
        signInPage.password.sendKeys(password);
        logger.info("Entered password.");
        signInPage.SignInBtn.click();
    }

    @Then("User is logged in successfully")
    public void verifySignIn(){
        try {
            String welcomeMessage = basePage.welcomeMessage.getText();
            Assert.assertEquals(welcomeMessage, "Welcome, " + firstname + " " + lastname + "!");
            logger.info("User login verification successful: " + welcomeMessage);
        } catch (NoSuchElementException e) {
            logger.error("Welcome message not found on the page.", e);
            throw e;
        } catch (AssertionError e) {
            logger.error("Welcome message mismatch.", e);
            throw e;
        }
    }

    @Then("User enters blank email, password as {string} and confirm password as {string}")
    public void enterBlankEmailWithPassword(String password, String confirmPassword){
        signUpPage.password.sendKeys(password);
        logger.info("Entered password");
        signUpPage.confirmPassword.sendKeys(confirmPassword);
        logger.info("Entered confirm password");
    }

    @Then("User faces error message")
    public void errorMessageOnInvalidSignUp() {
        int errorCount = signUpPage.errorMessages.size();
        Assert.assertTrue(errorCount > 0, "Expected at least one error message, but found none.");
        logger.info("Error messages found: " + errorCount);
    }



}
