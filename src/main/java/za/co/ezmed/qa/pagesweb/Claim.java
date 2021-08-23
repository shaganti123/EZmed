package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
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

    @FindBy(xpath = "//button[@class='btn btn-success']")
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
        Waitforelement();
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        String text= b.get(6).getText();
        System.out.println(text);
        if(text.contains("E.O.C")){
            b.get(8).click();
        }
        else if (!text.contains("E.O.C"))
        {
            b.get(7).click();
        }

    }

    public boolean CreateClaim() throws InterruptedException {
        WebElement Create = WebElementSearcher.elementsearchFluentWait(wdriver,CreateClaim);
        Thread.sleep(10000);
        Create.click();
        Waitforelement();
        WebElement P = WebElementSearcher.elementsearchFluentWait(wdriver,RP);
       if (P.isDisplayed())
        {
            Select RP = new Select(ReferringP);
            Screenshot.takeScreenshot(wdriver);
            RP.selectByIndex(1);
        }
        return true;
    }

    public boolean CommitClaim(String committ) throws InterruptedException {
        Thread.sleep(5000);
        WebElement Commit = WebElementSearcher.elementsearchFluentWait(wdriver,Committ);
        if(!Commit.isDisplayed())
        {
            JavascriptExecutor js = (JavascriptExecutor) wdriver;
            js.executeScript("window.scrollBy(0,-800)");
        }
        seleniumAction.clickWebElementObject(Commit);

        if (committ.equals("Commit Only"))
        {
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
