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
    private final String loginUrl = "https://demoqa.com/login";
    private final String profileUrl = "https://demoqa.com/profile";

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/books");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        homePage = new HomePage();
        sideBarPage = new SideBarPage();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();

        dotenv = Dotenv.configure().load();
        username = dotenv.get("username");
        password = dotenv.get("password");

        sideBarPage.clickOnItem("Login");
    }

    private void doLogin(String user, String pass) {
        loginPage.inputUsername(user);
        loginPage.inputPassword(pass);
        loginPage.clickOnLoginButton();
    }

    private void assertLoginError(String expectedMessage) {
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed!");
        Assert.assertEquals(loginPage.errorMessage(), expectedMessage, "Error message mismatch!");
    }

    @Test(priority = 1)
    public void userCanLoginWithValidCredentials() {
        doLogin(username, password);
        wait.until(ExpectedConditions.urlToBe(profileUrl));
        Assert.assertEquals(driver.getCurrentUrl(), profileUrl, "User is not redirected to the profile page!");
        Assert.assertEquals(profilePage.userNameValue(), username, "Logged-in username does not match!");
    }

    @Test(priority = 2)
    public void userCannotLogInWithInvalidUsername() {
        doLogin(username + "1", password);
        assertLoginError("Invalid username or password!");
    }

    @Test(priority = 3)
    public void userCannotLoginWithInvalidPassword() {
        doLogin(username, password + "1");
        assertLoginError("Invalid username or password!");
    }

    @Test(priority = 4)
    public void userCannotLogInWithInvalidCredentials() {
        doLogin(username + "1", password + "1");
        assertLoginError("Invalid username or password!");
    }

    @Test(priority = 5)
    public void userCannotLoginWithEmptyUsernameField() {
        doLogin("", password);
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl, "User is not redirected to the login page!");
        Assert.assertTrue(loginPage.isUsernameFieldEmpty(), "Username field is not empty!");
    }

    @Test(priority = 6)
    public void userCannotLoginWithEmptyPasswordField() {
        doLogin(username, "");
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl, "User is not redirected to the login page!");
        Assert.assertTrue(loginPage.isPasswordFieldEmpty(), "Password field is not empty!");
    }

    @Test(priority = 7)
    public void userCannotLoginWithEmptyFields() {
        doLogin("", "");
        Assert.assertTrue(loginPage.isUsernameFieldEmpty(), "Username field is not empty!");
        Assert.assertTrue(loginPage.isPasswordFieldEmpty(), "Password field is not empty!");
        Assert.assertEquals(loginPage.invalidFields(), 2, "Invalid fields count mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), loginUrl, "User is not redirected to the login page!");
    }
}
