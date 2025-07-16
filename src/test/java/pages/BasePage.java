package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ("//div[@class='panel wrapper']//a[text()='Create an Account']"))
    public WebElement createAccountBtn;

    @FindBy(xpath = ("//header//a[contains(text(),'Sign In')]"))
    public WebElement signInBtn;




}
