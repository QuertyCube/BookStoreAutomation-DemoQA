package objectrepository;

import java.util.List;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class HomeObject {

    @FindBy(className = "card-body")
    public List<WebElement> cards;

}
