package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.util.List;

public class Claim extends BaseClass {
    SeleniumAction seleniumAction;
    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    @FindBy(xpath = "//span[@class='welcome ng-binding']")
    private WebElement institute;

    @FindBy(xpath = "//button[@class='btn pull-right btn-success btn-margin-bottom-10']")
    private WebElement CreateNewClaim;

    @FindBy(xpath = "//select[contains(@ng-model,'config.ReferingProviderGuid')]")
    private WebElement ReferringP;

    private By RP = By.xpath("//select[contains(@class,'ng-empty')]");

    @FindBy(xpath = "//div/div/claim/div[@class='row']/div[@class='col-md-6 col-sm-12']/ng-form/div/button[@type='button']")
    private WebElement Commit;

    @FindBy(xpath = "//button[@class='btn btn-success  ng-binding']")
    private WebElement CommitOnly;

    @FindBy(xpath = "//button[@class='btn btn-info  ng-binding']")
    private WebElement SubmitToFunder;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    private WebElement Cancel;

    private By CreateClaim = By.xpath("//button[@class='btn pull-right btn-success btn-margin-bottom-10']");
    private By Committ = By.xpath("//button[@uib-tooltip='Post this claim to the financial system']");


    public Claim(WebDriver driver) {
        super(driver);
        seleniumAction=new SeleniumAction(wdriver);
    }
    public void Claims() throws InterruptedException {

        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        for(int i =0;i<=b.size();i++)
        {
            String text= b.get(i).getText();
            if(text.contains("Claims")) {
                b.get(i).click();
                break;
            }
    }
    }

    public boolean CreateClaim() throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        WebElement Create = WebElementSearcher.elementsearchSettlementConditionWithTimeLimit(wdriver,CreateClaim,20);
        ImplicitWait();
        seleniumAction.clickWebElementObject(Create);
        WebElement P = WebElementSearcher.elementsearchFluentWait(wdriver,RP);
       if (P.isDisplayed())
        {
            Select RP = new Select(ReferringP);
           Thread.sleep(2000);
            RP.selectByIndex(1);
        }
        return true;
    }

    public boolean CommitClaim(String committ) throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitUntilAngularReady();

        JavascriptExecutor jsExecuter = (JavascriptExecutor)wdriver;
        jsExecuter.executeScript("window.scrollTo(0,document.body.scrollTop)");
      //  ((JavascriptExecutor) wdriver).executeScript("arguments[0].scrollIntoViewIfNeeded(ture);", Commit);
        seleniumAction.clickWebElementObject(Commit);

        if (committ.equals("Commit Only"))
        {
            ((JavascriptExecutor) wdriver).executeScript("arguments[0   ].scrollIntoViewIfNeeded(false);", CommitOnly);
             seleniumAction.clickWebElementObject(CommitOnly);
        }
        else if (committ.equals("Submit to Funder"))
        {

            seleniumAction.clickWebElementObject(SubmitToFunder);
        }
        else if(committ.equals("Cancel")) {
            seleniumAction.clickWebElementObject(Cancel);
        }
        return true;
    }



}
