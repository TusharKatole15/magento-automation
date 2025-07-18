package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    WebDriver driver;

    public SignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "pass")
    public WebElement password;

    @FindBy(id = "send2")
    public WebElement SignInBtn;

}
