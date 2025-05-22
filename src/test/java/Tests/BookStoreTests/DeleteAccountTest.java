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

import java.io.IOException;
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

    @Test(priority = 1)
    public void userCanDeleteAccount() throws IOException {
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
        System.out.println("Error message: " + loginPage.errorMessage());
        Assert.assertEquals(loginPage.errorMessage(), "Invalid username or password!", "Error message mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login", "URL mismatch after account deletion!");
        createUserViaApi();
    }

    public void createUserViaApi() throws IOException {
        String apiUrl = "https://demoqa.com/Account/v1/User";
        String user = dotenv.get("usernameDelete");
        String pass = dotenv.get("passwordDelete");
        String jsonBody = String.format("{ \"userName\": \"%s\", \"password\": \"%s\" }", user, pass);

        java.net.URL url = new java.net.URL(apiUrl);
        java.net.HttpURLConnection con = (java.net.HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        try (java.io.OutputStream os = con.getOutputStream()) {
            byte[] input = jsonBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        Assert.assertEquals(responseCode, 201, "User creation failed!");

        // // Optionally, read the response
        // try (java.io.BufferedReader br = new java.io.BufferedReader(
        //         new java.io.InputStreamReader(con.getInputStream(), "utf-8"))) {
        //     StringBuilder response = new StringBuilder();
        //     String responseLine;
        //     while ((responseLine = br.readLine()) != null) {
        //         response.append(responseLine.trim());
        //     }
        //     System.out.println("API Response: " + response.toString());
        // }
    }
}
