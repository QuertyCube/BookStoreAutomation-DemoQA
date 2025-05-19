package Tests.BookStoreTests;

import Base.BaseTest;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import io.github.cdimascio.dotenv.Dotenv;
import objectrepository.ProfileObject;
import pageaction.LoginPage;
import pageaction.ProfilePage;
import pageaction.SideBarPage;

public class LogoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        sideBarPage = new SideBarPage();

        Dotenv dotenv = Dotenv.configure().load();
        String username = dotenv.get("username");
        String password = dotenv.get("password");
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickOnLoginButton();

    }

    ProfileObject profileObject = new ProfileObject(driver);

    @Test(priority = 1)
    public void userCanLogOut() {
        WebElement logoutButton = profilePage.findButton("Log out");
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        boolean logoutButtonIsDisplayed = logoutButton.isDisplayed();
        System.out.println("Logout button is displayed: " + logoutButtonIsDisplayed);
        profilePage.clickOnButton("Log out");

        try {
            logoutButtonIsDisplayed = logoutButton.isDisplayed();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            // Handle the stale element exception by setting the flag to false
            logoutButtonIsDisplayed = false;
        }
        Assert.assertFalse(logoutButtonIsDisplayed);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login");
    }


    @Test(priority = 2)
    public void userCannotAccessProfilePageAfterLoggingOut() {

        WebElement logoutButton = profilePage.findButton("Log out");
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        boolean logoutButtonIsDisplayed = logoutButton.isDisplayed();
        System.out.println("Logout button is displayed: " + logoutButtonIsDisplayed);
        profilePage.clickOnButton("Log out");

        try {
            logoutButtonIsDisplayed = logoutButton.isDisplayed();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            // Handle the stale element exception by setting the flag to false
            logoutButtonIsDisplayed = false;
        }
        Assert.assertFalse(logoutButtonIsDisplayed);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login");

        sideBarPage.clickOnItem("Profile");
        Assert.assertNotNull(profileObject.notLoggedInLabel);
    }
}
