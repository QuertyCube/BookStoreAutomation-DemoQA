package Tests.BookStoreTests;

import Base.BaseTest;
import io.github.cdimascio.dotenv.Dotenv;
import pageaction.LoginPage;
import pageaction.ProfilePage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DeleteAccountTest extends BaseTest {

    private Dotenv dotenv;
    private String username;
    private String password;

    @BeforeMethod
    public void pageSetUp() {
        // Initialize WebDriver and navigate to the login page
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Load environment variables
        dotenv = Dotenv.configure().load();
        username = dotenv.get("usernameDelete");
        password = dotenv.get("passwordDelete");

        // Initialize page objects
        loginPage = new LoginPage();
        profilePage = new ProfilePage();

        // Perform login
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Test(priority = 10)
    public void userCanDeleteAccount() {
        // Verify the logged-in username
        Assert.assertEquals(profilePage.userNameValue(), username, "Logged-in username does not match!");

        // Perform account deletion
        profilePage.clickOnAnySubmitButton("Delete Account");
        profilePage.clickOnOk();

        // Handle alert and verify redirection to login page
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/login"));

        // Attempt to log in again and verify error message
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(loginPage.errorMessage(), "Invalid username or password!", "Error message mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login", "URL mismatch after account deletion!");
    }
}
