package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import za.co.ezmed.qa.utils.JSWaiter;
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
    public By ProviderDelete = By.xpath("//button[@class='btn btn-danger ng-scope']");
    public By PopUPText = By.xpath("//div[@class='toast toast-success']/div/div");

    public SummaryPage(WebDriver driver) {
         super(driver);
         seleniumAction = new SeleniumAction(wdriver);

    }

    public void DeleteP() throws InterruptedException {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(0).click();
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitUntilAngularReady();
        WebElement Delete= WebElementSearcher.elementsearchSettlementCondition(wdriver,Deletee);
        ((JavascriptExecutor) wdriver).executeScript("arguments[0].scrollIntoViewIfNeeded()", Delete);
        Delete.click();
        seleniumAction.clickWebElementObject(CDelete);
        WebElement Actual= WebElementSearcher.elementsearchFluentWait(wdriver,PopUPText);
        String p = Actual.getText();
        String ExpectedText = "Patient Successfully de-activated!";
        Assert.assertEquals(ExpectedText, p);
    }


    public void DeleteProvider() throws InterruptedException {
        WebElement PDelete= WebElementSearcher.elementsearchFluentWait(wdriver,ProviderDelete);
        seleniumAction.clickWebElementObject(PDelete);
        Thread.sleep(5000);
        seleniumAction.clickWebElementObject(CDelete);
    }

}
