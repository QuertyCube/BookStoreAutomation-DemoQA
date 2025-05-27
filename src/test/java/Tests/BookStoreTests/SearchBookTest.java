package Tests.BookStoreTests;

import Base.BaseTest;
import objectrepository.ProfileObject;
import pageaction.ProfilePage;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchBookTest extends BaseTest {

    private ProfileObject profileObject;
    private ProfilePage profilePage;

    private static final String VALID_BOOK = "Git Pocket Guide";
    private static final String INVALID_BOOK = "xx xx xx";

    @BeforeMethod
    public void pageSetUp() throws IOException {
        // Initialize WebDriver and navigate to the books page
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/books");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize page objects
        profilePage = new ProfilePage();
        profileObject = new ProfileObject(driver);
    }

    private boolean isBookFound(String searchInput) {
        profilePage.inputSearch(searchInput);
        return profileObject.booksInSearch.stream()
                .anyMatch(book -> book.getText().equalsIgnoreCase(searchInput));
    }

    @Test(priority = 1)
    public void userCanSearchBook() {
        // Assert that the search results contain the expected book
        Assert.assertTrue(isBookFound(VALID_BOOK), "The book '" + VALID_BOOK + "' was not found in the search results.");
    }

    @Test(priority = 3)
    public void userCanSearchBooksCaseInsensitive() {

        String[] searchInputs = { "git pocket guide", "GIT POCKET GUIDE" };
        for (String searchInput : searchInputs) {
            // Assert that the search results contain the expected book
            Assert.assertTrue(isBookFound(searchInput), "The book '" + searchInput + "' was not found in the search results.");
        }
    }

    // No results are displayed for invalid search queries
    @Test(priority = 2)
    public void userCantFindInvalidBook() {
        Assert.assertFalse(isBookFound(INVALID_BOOK), "The book '" + INVALID_BOOK + "' found in the search results.");
    }
}
