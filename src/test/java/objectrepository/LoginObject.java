package objectrepository;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginObject {
    public LoginObject(WebDriver driver) {
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

    @FindBy(id = "userName")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login")
    public WebElement loginButton;

    @FindBy(id = "newUser")
    public WebElement addNewUserButton;

    @FindBy(id = "name")
    public WebElement error;

}
