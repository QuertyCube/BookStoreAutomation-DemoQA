package objectrepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileObject {

    @FindBy(className = "mr-2")
    public List<WebElement> booksInSearch;

    @FindBy(id = "delete-record-undefined")
    public List<WebElement> deleteButtons;

    @FindBy(id = "submit")
    public List<WebElement> profileButtons;

    @FindBy(id = "userName-value")
    public WebElement loggedInUserValue;

    @FindBy(linkText = "Log out")
    public WebElement logoutButton;

    @FindBy(id = "closeSmallModal-ok")
    public WebElement okButton;

    @FindBy(id = "searchBox")
    public WebElement searchBoxField;

    @FindBy(id = "notLoggin-label")
    public WebElement notLoggedInLabel;

    private WebDriver driver;

    public ProfileObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

}
