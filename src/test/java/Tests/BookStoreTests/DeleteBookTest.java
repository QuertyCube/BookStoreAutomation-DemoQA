package Tests.BookStoreTests;

import Base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageaction.LoginPage;
import pageaction.ProfilePage;

import java.time.Duration;

public class DeleteBookTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        // Initialize WebDriver and navigate to the books page
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/books");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Initialize page objects
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
    }

    @Test(priority = 1)
    public void userCanDeleteAllBooksWhenBookEmpty() {
        // Perform login action
        loginPage.loginAction();

        // Cause problem in Account can't add book, so book is empty, and no way to add it
        // Attempt to delete all books
        profilePage.clickOnDeleteAllBooks();
        profilePage.clickOnOk();

        // Handle alert and verify the message
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();

            // Assert the alert message
            Assert.assertEquals(alertText, "No books available in your's collection!", 
                "Alert message mismatch!");
            alert.accept(); // Accept the alert
        } catch (Exception e) {
            Assert.fail("Alert not found: " + e.getMessage());
        }
    }
}

