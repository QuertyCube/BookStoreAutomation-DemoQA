package pageaction;

import Base.BaseTest;
import objectrepository.HomeObject;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseTest {

    private HomeObject homeObject;

    public HomePage() {
        PageFactory.initElements(driver, this);
        homeObject = new HomeObject();
        PageFactory.initElements(driver, homeObject); 
    }

    public void clickOnCard(String str){
        for(int i =0; i<homeObject.cards.size();i++){
            if(homeObject.cards.get(i).getText().equals(str)){
                clickOnElement(homeObject.cards.get(i));
                break;
            }
        }
    }
}
