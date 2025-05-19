package pageaction;

import Base.BaseTest;
import io.github.cdimascio.dotenv.Dotenv;
import objectrepository.LoginObject;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseTest {

    private LoginObject loginObject;

    /**
     * Constructor to initialize the LoginPage and its elements.
     */
    public LoginPage() {
        PageFactory.initElements(driver, this);
        loginObject = new LoginObject(driver);
        PageFactory.initElements(driver, loginObject);
    }

    /**
     * Performs the login action using credentials from environment variables.
     */
    public void loginAction() {
        loginPage = new LoginPage();
        sideBarPage = new SideBarPage();

        // Navigate to the Login page
        sideBarPage.clickOnItem("Login");

        // Load credentials from environment variables
        Dotenv dotenv = Dotenv.configure().load();
        String username = dotenv.get("username");
        String password = dotenv.get("password");

        // Input credentials and click login
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
    }

    /**
     * Inputs the username into the username field.
     *
     * @param username The username to input.
     */
    public void inputUsername(String username) {
        loginObject.usernameField.clear();
        loginObject.usernameField.sendKeys(username);
    }

    /**
     * Inputs the password into the password field.
     *
     * @param password The password to input.
     */
    public void inputPassword(String password) {
        loginObject.passwordField.clear();
        loginObject.passwordField.sendKeys(password);
    }

    /**
     * Clicks the login button. If the button is not clickable, it scrolls to the button and retries.
     */
    public void clickOnLoginButton() {
        try {
            loginObject.loginButton.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                    loginObject.loginButton);
            loginObject.loginButton.click();
        }
    }

    /**
     * Clicks the "Add New User" button.
     */
    public void clickOnAddNewUserButton() {
        try {
            loginObject.addNewUserButton.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                loginObject.addNewUserButton);
        loginObject.addNewUserButton.click();
        }
    }

    /**
     * Retrieves the error message displayed on the login page.
     *
     * @return The error message as a String.
     */
    public String errorMessage() {
        return loginObject.error.getText();
    }

    /**
     * Checks if the error message is displayed on the login page.
     *
     * @return True if the error message is displayed, false otherwise.
     */
    public boolean isErrorDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(loginObject.error));
            return loginObject.error.isDisplayed();
        } catch (Exception e) {
            return false; // Element not found or not displayed
        }
    }

    /**
     * Checks if the username field is empty.
     *
     * @return True if the username field is empty, false otherwise.
     */
    public boolean isUsernameFieldEmpty() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(loginObject.usernameField));
            return loginObject.usernameField.getText().isEmpty();
        } catch (Exception e) {
            return true; // Element not found or empty
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
            wait.until(ExpectedConditions.visibilityOf(loginObject.passwordField));
            return loginObject.passwordField.getText().isEmpty();
        } catch (Exception e) {
            return true; // Element not found or empty
        }
    }

    /**
     * Counts the number of invalid fields (empty username or password fields).
     *
     * @return The number of invalid fields.
     */
    public int invalidFields() {
        int invalidFields = 0;
        if (isUsernameFieldEmpty()) {
            invalidFields++;
        }
        if (isPasswordFieldEmpty()) {
            invalidFields++;
        }
        return invalidFields;
    }
}
