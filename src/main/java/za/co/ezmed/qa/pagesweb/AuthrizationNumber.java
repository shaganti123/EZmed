package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
        Xls_Reader reader = new Xls_Reader("C:\\Users\\laxmis\\IdeaProjects\\EZmed\\src\\main\\java\\za\\co\\ezmed\\qa\\utils\\addpatients.xlsx");
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        String text= b.get(6).getText();
        System.out.println(text);
        if(text.contains("E.O.C")){
            b.get(7).click();
        }
        else if (!text.contains("E.O.C"))
        {
            b.get(6).click();
        }
        Screenshot.takeScreenshot(wdriver);
        WebElement AddNew = WebElementSearcher.elementsearchSettlementCondition(wdriver,Add);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(AddNew);

        String sheetName = "PrivatePatient";
        int rowCount = reader.getRowCount(sheetName);
        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String AuthNum = reader.getCellData(sheetName, "AccountHolder", rowNum);
            String Comment=reader.getCellData(sheetName, "Insurer", rowNum);
            Auth.sendKeys(AuthNum);
            Comments.sendKeys(Comment);
            break;
        }


    }
}
