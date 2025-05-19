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

    @Test(priority = 1)
    public void userCanSearchBook() {
        // Define the book to search for
        String searchInput = "Git Pocket Guide";

        // Perform search action
        profilePage.inputSearch(searchInput);

        // Assert that the search results contain the expected book
        boolean isBookFound = profileObject.booksInSearch.stream()
                .anyMatch(book -> book.getText().equalsIgnoreCase(searchInput));

        Assert.assertTrue(isBookFound, "The book '" + searchInput + "' was not found in the search results.");
    }

    @Test(priority = 2)
    public void userCanSearchBooksCaseInsensitive() {

        String[] searchInputs = { "git pocket guide", "GIT POCKET GUIDE" };
        for (String searchInput : searchInputs) {
            // Perform search action
            profilePage.inputSearch(searchInput);

            // Assert that the search results contain the expected book
            boolean isBookFound = profileObject.booksInSearch.stream()
                    .anyMatch(book -> book.getText().equalsIgnoreCase(searchInput));

            Assert.assertTrue(isBookFound, "The book '" + searchInput + "' was not found in the search results.");
        }
    }

    // No results are displayed for invalid search queries
    @Test(priority = 2)
    public void userCantFindInvalidBook() {
        // Define the book to search for
        String searchInput = "xx xx xx";

        // Perform search action
        profilePage.inputSearch(searchInput);

        // Assert that the search results contain the expected book
        boolean isBookFound = profileObject.booksInSearch.stream()
                .anyMatch(book -> book.getText().equalsIgnoreCase(searchInput));

        Assert.assertFalse(isBookFound, "The book '" + searchInput + "' found in the search results.");
    }
}
