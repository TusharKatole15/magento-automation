package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    WebDriver driver;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ("//div[@class='panel wrapper']//span[@class='logged-in']"))
    public WebElement welcomeMessage;

    @FindBy(xpath = ("//div[@class='panel header']//button[@class='action switch']"))
    public WebElement expandOptions;

    @FindBy(xpath = ("(//li[@class=\"authorization-link\"])[1]"))
    public WebElement signOutBtn;

}
