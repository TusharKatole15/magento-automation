package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignUpPage {

    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstname")
    public WebElement firstname;

    @FindBy(id = "lastname")
    public WebElement lastname;

    @FindBy(id = "email_address")
    public WebElement email;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "password-confirmation")
    public WebElement confirmPassword;

    @FindBy(xpath = "//button[@title='Create an Account']")
    public WebElement submitBtn;

    @FindBy(xpath = "//div[@class=\"mage-error\"]")
    public List<WebElement> errorMessages;
}
