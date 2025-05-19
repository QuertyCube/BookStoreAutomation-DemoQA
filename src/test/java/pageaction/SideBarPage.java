package pageaction;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class SideBarPage extends BaseTest {

    public SideBarPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "text")
    public List<WebElement> cardItems;

    public void clickOnItem(String str){
        for(int i =0; i<cardItems.size();i++){
            if(cardItems.get(i).getText().equals(str)){
                clickOnElement(cardItems.get(i));
                break;
            }
        }
    }
}
