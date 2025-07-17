package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.BasePage;
import pages.SignUpPage;
import utils.AdHandler;
import utils.EmailGenerator;

public class SignUpAndSignIn extends BaseTest {

    BasePage basePage = new BasePage(driver);
    SignUpPage signUpPage = new SignUpPage(driver);

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
        signUpPage.firstname.sendKeys(firstname);
        logger.info("Entered First name");
        signUpPage.lastname.sendKeys(lastname);
        logger.info("Entered Last name");
    }

    @Then("User enters email, password as {string} and confirm password as {string}")
    public void enterEmailAndPasswords(String password, String confirmPassword){
        String email = EmailGenerator.generateRandomEmail();

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
            logger.info("Verified title successfully.");
        } catch (AssertionError e){
            logger.error("Page title mismatch. Found: " + title, e);
            throw e;
        }

    }


}
