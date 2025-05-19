package Tests.BookStoreTests;

import Base.BaseTest;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageaction.HomePage;
import pageaction.LoginPage;
import pageaction.ProfilePage;
import pageaction.SideBarPage;

import java.io.IOException;
import java.time.Duration;

public class LoginTest extends BaseTest {

    private Dotenv dotenv;
    private String username;
    private String password;
    private String loginUrl = "https://demoqa.com/login";
    private String profileUrl = "https://demoqa.com/profile";

    @BeforeMethod
    public void pageSetUp() throws IOException {
        // Initialize WebDriver and navigate to the books page
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/books");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize page objects
        homePage = new HomePage();
        sideBarPage = new SideBarPage();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();

        // Load environment variables
        dotenv = Dotenv.configure().load();
        username = dotenv.get("username");
        password = dotenv.get("password");

        // Navigate to login page
        sideBarPage.clickOnItem("Login");
    }

    @Test(priority = 1)
    public void userCanLoginWithValidCredentials() {
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        wait.until(ExpectedConditions.urlToBe(profileUrl));
        Assert.assertEquals(driver.getCurrentUrl(), profileUrl, "User is not redirected to the profile page!");
        Assert.assertEquals(profilePage.userNameValue(), username, "Logged-in username does not match!");
    }

    @Test(priority = 2)
    public void userCannotLogInWithInvalidUsername() {
        loginPage.inputUsername(username + "1"); // Invalid username
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed!");
        Assert.assertEquals(loginPage.errorMessage(), "Invalid username or password!", "Error message mismatch!");
    }

    @Test(priority = 3)
    public void userCannotLoginWithInvalidPassword() {
        loginPage.inputUsername(username);
        loginPage.inputPassword(password + "1"); // Invalid password
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed!");
        Assert.assertEquals(loginPage.errorMessage(), "Invalid username or password!", "Error message mismatch!");
    }

    @Test(priority = 4)
    public void userCannotLogInWithInvalidCredentials() {
        loginPage.inputUsername(username + "1"); // Invalid username
        loginPage.inputPassword(password + "1"); // Invalid password
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed!");
        Assert.assertEquals(loginPage.errorMessage(), "Invalid username or password!", "Error message mismatch!");
    }

    @Test(priority = 5)
    public void userCannotLoginWithEmptyUsernameField() {
        loginPage.inputUsername("");
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl, "User is not redirected to the login page!");
        Assert.assertTrue(loginPage.isUsernameFieldEmpty(), "Username field is not empty!");
    }

    @Test(priority = 6)
    public void userCannotLoginWithEmptyPasswordField() {
        loginPage.inputUsername(username);
        loginPage.inputPassword("");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl, "User is not redirected to the login page!");
        Assert.assertTrue(loginPage.isPasswordFieldEmpty(), "Password field is not empty!");
    }

    @Test(priority = 7)
    public void userCannotLoginWithEmptyFields() {
        loginPage.inputUsername("");
        loginPage.inputPassword("");
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.isUsernameFieldEmpty(), "Username field is not empty!");
        Assert.assertTrue(loginPage.isPasswordFieldEmpty(), "Password field is not empty!");
        Assert.assertEquals(loginPage.invalidFields(), 2, "Invalid fields count mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl, "User is not redirected to the login page!");
    }
}
