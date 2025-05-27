package Tests.BookStoreTests;

import Base.BaseTest;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import io.github.cdimascio.dotenv.Dotenv;
import objectrepository.RegisterObject;
import pageaction.LoginPage;
import pageaction.RegisterPage;

public class RegisterAccountTest extends BaseTest {

    private LoginPage loginPage;
    private RegisterObject registerObject;
    private RegisterPage registerPage;
    private Dotenv dotenv;

    // Test data fields
    private String firstName;
    private String lastName;
    private String password;

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize page objects
        loginPage = new LoginPage();
        registerObject = new RegisterObject(driver);
        registerPage = new RegisterPage();

        // Load environment variables
        dotenv = Dotenv.configure().load();
        firstName = dotenv.get("firstName");
        lastName = dotenv.get("lastName");
        password = dotenv.get("password");
    }

    private void fillRegistrationForm(String firstName, String lastName, String username, String password) {
        loginPage.clickOnAddNewUserButton();
        registerPage.inputFirstName(firstName);
        registerPage.inputLastName(lastName);
        registerPage.inputUserName(username);
        registerPage.inputPassword(password);
        registerPage.clickOnRegisterButton();
    }

    @Test(priority = 1)
    public void userCannotRegisterWhenRecaptchaIsNotVerified() {
        String username = dotenv.get("username") + "123";
        fillRegistrationForm(firstName, lastName, username, password);

        wait.until(ExpectedConditions.visibilityOf(registerObject.recaptchaError));
        Assert.assertEquals(registerPage.recaptchaErrorMessage(), "Please verify reCaptcha to register!");
    }

    @Test(priority = 2)
    public void userCannotRegisterWithMissingDetails() {
        String username = "";
        fillRegistrationForm(firstName, lastName, username, password);

        Assert.assertEquals(registerPage.invalidFields(), 1, "Invalid fields count mismatch!");
        Assert.assertTrue(registerPage.isUsernameFieldEmpty(), "Username field expected to be empty!");
        Assert.assertFalse(registerPage.isPasswordFieldEmpty(), "Password field expected not be empty!");
        Assert.assertFalse(registerPage.isFirstNameFieldEmpty(), "First name field expected not to be empty!");
        Assert.assertFalse(registerPage.isLastNameFieldEmpty(), "Last name field expected not to be empty!");
    }
}
