package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;
import za.co.ezmed.qa.utils.Xls_Reader;

import java.util.List;

public class AuthrizationNumber extends BaseClass {
    SeleniumAction seleniumAction;

    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    private By Add = By.xpath("//button[contains(@class,'pull-right btn-margin-bottom-10')]");
    @FindBy(xpath = "//input[@placeholder='Number']")
    private WebElement Auth;
    @FindBy(xpath = "//textarea[@placeholder='Add your Comments']")
    private WebElement Comments;
    @FindBy(xpath = "//span[@class='welcome ng-binding']")
    private WebElement institute;


    public AuthrizationNumber(WebDriver driver) {
        super(driver);
        seleniumAction = new SeleniumAction(wdriver);
    }

    public void AuthRef() {
        Xls_Reader reader = new Xls_Reader( "src/main/java/za/co/ezmed/qa/utils/addpatients.xlsx");
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        for(int i =0;i<=b.size();i++)
        {
            String text= b.get(i).getText();
            if(text.contains("Auth/Ref")) {
                b.get(i).click();
                break;
            }
        }
        JSWaiter.waitJQueryAngular();
        Screenshot.takeScreenshot(wdriver);
        WebElement AddNew = WebElementSearcher.elementsearchFluentWait(wdriver,Add);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(AddNew);

        String sheetName = "PrivatePatient";
        int rowCount = reader.getRowCount(sheetName);
        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String AuthNum = reader.getCellData(sheetName, "AccountHolder", rowNum);
            String Comment=reader.getCellData(sheetName, "Insurer", rowNum);
            JSWaiter.waitJQueryAngular();
            Auth.sendKeys(AuthNum);
            Comments.sendKeys(Comment);
            break;
        }


    }
}
