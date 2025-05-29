package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageaction.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public HomePage homePage;
    public LoginPage loginPage;
    public ProfilePage profilePage;
    public RegisterPage registerPage;
    public SideBarPage sideBarPage;
    public static WebDriver driver;
    public WebDriverWait wait;

    /**
     * Sets up the WebDriver and initializes the browser before running tests.
     */
    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Accepts a pop-up alert or confirmation dialog.
     */
    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    /**
     * Simulates a click on a web element using JavaScript.
     * Useful when the standard click() method fails.
     *
     * @param element The WebElement to be clicked.
     */
    public void clickOnElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls to a specific web element when it is outside the viewport.
     *
     * @param element The WebElement to scroll to.
     */
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForVisibility(WebElement element, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
            .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable(WebElement element, int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
            .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Checks if an element is present in the DOM.
     */
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Cleans up after each test by deleting cookies, refreshing the page,
     * and closing the browser.
     */
    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.quit();
    }
}
