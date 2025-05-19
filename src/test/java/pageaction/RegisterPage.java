package pageaction;

import Base.BaseTest;
import objectrepository.RegisterObject;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.WebElement;

public class RegisterPage extends BaseTest {

    private final RegisterObject registerObject;

    public RegisterPage() {
        // Initialize page objects
        PageFactory.initElements(driver, this);
        registerObject = new RegisterObject(driver);
        PageFactory.initElements(driver, registerObject);
    }

    public void inputFirstName(String firstName) {
        clearAndType(registerObject.firstnameField, firstName);
    }

    public void inputLastName(String lastName) {
        clearAndType(registerObject.lastnameField, lastName);
    }

    public void inputUserName(String userName) {
        clearAndType(registerObject.usernameField, userName);
    }

    public void inputPassword(String password) {
        clearAndType(registerObject.passwordField, password);
    }

    public void clickOnRegisterButton() {
        clickOnElement(registerObject.registerButton);
    }

    public String recaptchaErrorMessage() {
        return registerObject.recaptchaError.getText();
    }

    // Utility method to clear and type into a field
    private void clearAndType(WebElement field, String value) {
        field.clear();
        field.sendKeys(value);
    }

    /**
     * Counts the number of invalid fields (empty username or password fields).
     *
     * @return The number of invalid fields.
     */
    public int invalidFields() {
        System.out.println("Invalid fields: " + isPasswordFieldEmpty());
        return registerObject.invalidFields.size();
    }

    /**
     * Checks if the username field is empty.
     *
     * @return True if the username field is empty, false otherwise.
     */
    public boolean isUsernameFieldEmpty() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(registerObject.usernameField));

            if (registerObject.usernameField == null) {
                return true; // Field is null
            }

            String cssClass = registerObject.usernameField.getAttribute("class");
            if (cssClass != null && cssClass.contains("is-invalid")) {
                return true; // Field contains the invalid CSS class
            }

            return registerObject.usernameField.getAttribute("value").isEmpty(); // Check if the field is empty
        } catch (Exception e) {
            return true; // Handle exceptions by assuming the field is invalid
        }
    }
    /**
     * Checks if the password field is empty.
     *
     * @return True if the password field is empty, false otherwise.
     */
    public boolean isPasswordFieldEmpty() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(registerObject.passwordField));

            if (registerObject.passwordField == null) {
                return true; // Field is null
            }

            String cssClass = registerObject.passwordField.getAttribute("class");
            if (cssClass != null && cssClass.contains("is-invalid")) {
                return true; // Field contains the invalid CSS class
            }

            return registerObject.passwordField.getAttribute("value").isEmpty(); // Check if the field is empty
        } catch (Exception e) {
            return true; // Handle exceptions by assuming the field is invalid  
        }
    }

    public boolean isFirstNameFieldEmpty() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(registerObject.firstnameField));

            if (registerObject.firstnameField == null) {
                return true; // Field is null
            }

            String cssClass = registerObject.firstnameField.getAttribute("class");
            if (cssClass != null && cssClass.contains("is-invalid")) {
                return true; // Field contains the invalid CSS class
            }

            return registerObject.firstnameField.getAttribute("value").isEmpty(); // Check if the field is empty
        } catch (Exception e) {
            return true; // Handle exceptions by assuming the field is invalid  
        }
    }

    public boolean isLastNameFieldEmpty() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(registerObject.lastnameField));

            if (registerObject.lastnameField == null) {
                return true; // Field is null
            }

            String cssClass = registerObject.lastnameField.getAttribute("class");
            if (cssClass != null && cssClass.contains("is-invalid")) {
                return true; // Field contains the invalid CSS class
            }

            return registerObject.lastnameField.getAttribute("value").isEmpty(); // Check if the field is empty
        } catch (Exception e) {
            return true; // Handle exceptions by assuming the field is invalid  
        }
    }

    
}
