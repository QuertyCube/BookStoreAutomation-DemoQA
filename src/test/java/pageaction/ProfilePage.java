package pageaction;

import Base.BaseTest;
import objectrepository.ProfileObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BaseTest {

    private ProfileObject profileObject;

    /**
     * Constructor to initialize the ProfilePage and its elements.
     */
    public ProfilePage() {
        PageFactory.initElements(driver, this);
        profileObject = new ProfileObject(driver);
        PageFactory.initElements(driver, profileObject);
    }

    /**
     * Clicks on any submit button based on the button's text.
     *
     * @param buttonText The text of the button to click.
     */
    public void clickOnAnySubmitButton(String buttonText) {
        clickOnElement(findButton(buttonText));
    }

    /**
     * Clicks on a button with the specified text.
     *
     * @param buttonText The text of the button to click.
     */
    public void clickOnButton(String buttonText) {
        for (WebElement button : profileObject.profileButtons) {
            if (button.getText().equals(buttonText)) {
                button.click();
                break;
            }
        }
    }

    /**
     * Clicks on the "Delete All Books" button.
     */
    public void clickOnDeleteAllBooks() {
        for (WebElement button : profileObject.profileButtons) {
            if (button.getText().equals("Delete All Books")) {
                clickOnElement(button);
                break;
            }
        }
    }

    /**
     * Clicks on the "OK" button in a confirmation dialog.
     */
    public void clickOnOk() {
        profileObject.okButton.click();
    }

    /**
     * Finds a button by its text.
     *
     * @param buttonText The text of the button to find.
     * @return The WebElement of the button if found, or the first button as a fallback.
     */
    public WebElement findButton(String buttonText) {
        for (WebElement button : profileObject.profileButtons) {
            if (button.getText().equals(buttonText)) {
                return button;
            }
        }
        return profileObject.profileButtons.get(0); // Default to the first button if not found
    }

    /**
     * Inputs a search query into the search box.
     *
     * @param query The search query to input.
     */
    public void inputSearch(String query) {
        profileObject.searchBoxField.clear();
        profileObject.searchBoxField.sendKeys(query);
    }

    /**
     * Retrieves the username of the currently logged-in user.
     *
     * @return The username as a String.
     */
    public String userNameValue() {
        return profileObject.loggedInUserValue.getText();
    }
}
