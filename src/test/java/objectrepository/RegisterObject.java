package objectrepository;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterObject {
    public RegisterObject(WebDriver driver) {
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

    @FindBy(id = "firstname")
    public WebElement firstnameField;

    @FindBy(id = "lastname")
    public WebElement lastnameField;

    @FindBy(id = "userName")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "register")
    public WebElement registerButton;

    @FindBy(id = "name")
    public WebElement recaptchaError;

    @FindBy(css = ".mr-sm-2.is-invalid.form-control")
    public List<WebElement> invalidFields;

}
