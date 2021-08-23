package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.util.List;

public class SummaryPage extends BaseClass {
    SeleniumAction seleniumAction;
    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";

    @FindBy(xpath = "//button[@ng-class='item.class'][1]")
    private WebElement CDelete;
    //@FindBy(xpath = "//button[@ng-click='deleteProvider(p)']")
  //  private WebElement ProviderDelete;
    public By Deletee = By.xpath("//button[@uib-tooltip='Click here to remove this patient']");
    public By ProviderDelete = By.xpath("//button[@ng-click='deleteProvider(p)']");
    public SummaryPage(WebDriver driver) {
         super(driver);
         seleniumAction = new SeleniumAction(wdriver);

    }

    public void DeleteP() throws InterruptedException {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(0).click();
        Waitforelement();
        WebElement Delete= WebElementSearcher.elementsearchSettlementCondition(wdriver,Deletee);
        JavascriptExecutor js = (JavascriptExecutor) wdriver;
        js.executeScript("window.scrollBy(0,400)");
        seleniumAction.clickWebElementObject(Delete);
        Waitforelement();
        seleniumAction.clickWebElementObject(CDelete);
    }

    public void DeleteProvider() throws InterruptedException {
        WebElement PDelete= WebElementSearcher.elementsearchFluentWait(wdriver,ProviderDelete);
        seleniumAction.clickWebElementObject(PDelete);
        Waitforelement();
        seleniumAction.clickWebElementObject(CDelete);
    }

}
