package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BatchProcesses extends BaseClass {
    SeleniumAction seleniumAction;

    public BatchProcesses(WebDriver driver) {
        super(driver);
        seleniumAction = new SeleniumAction(wdriver);
    }

    String before_xpath="//tbody/tr[";
    String after_xpathk="]/td[3]";
    String Patient_Name ="]/td[1]";

    public By JobStatus_xpath = By.xpath("//div[@class='table-responsive']/table/tbody//div[@ng-if='!isLoading && !canShowAll && (results == null || results.length == 0)']");
    public By BatchProcessing = By.xpath("//div[@class='thumbnail']//i[@class='fal fa-4x fa-briefcase']");
  //  public By Statement = By.xpath("//div[@class='thumbnail']//i[@class='fal fa-4x fa-folder']");
    public By ProcessQueue = By.xpath("//div[@class='thumbnail']//i[@class='fal fa-4x fa-clipboard-list-check']");

    String XpathOfChecks = "//label/i[@ng-style='getLineCheckStyle(invoice)']";
    String xpathofsemails = "//tbody/tr";
    String EmailDate = "//div/span[@class='details pull-right ng-binding']";
    String RadioButtons = "//div[@class='radio-inline fa5']//label";

    @FindBy(xpath = "//div[@class='thumbnail']//i[@class='fal fa-4x fa-folder']")
    private WebElement Statement;

    @FindBy(xpath = "//div[@class='col-md-12 hidden-md hidden-sm hidden-xs']/date-quick-links//button[contains(text(),'This Week')]")
    private WebElement ThisWeek;
    @FindBy(xpath = "//date-quick-links[@class='hidden-sm hidden-xs ng-scope ng-isolate-scope']//button[contains(text(),'Today')]")
    private WebElement Today;
    @FindBy(xpath = "//button/span[contains(text(),'Search')]")
    private WebElement Search;
    @FindBy(xpath = "//button/span[contains(text(),'Execute Job')]")
    private WebElement ExecuteJob;
    public By Last = By.xpath("//ul[@role='menu']//li/descendant::a[contains(text(),'Last')]");
    public By SelAll = By.xpath("//button[@id='selAll']");
    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right']//li/descendant::a/span/i[@class='fal fa-3x fa-envelope']")
    private WebElement Inbox;

    @FindBy(xpath = "//input[@name='22039']")
    private WebElement Future;

    @FindBy(xpath = "//*[@class='c-bn']")
    private WebElement CookiesAccept;
    @FindBy(xpath = "//div//ol/li/descendant::a[contains(text(),'Batched ')]")
    private WebElement BatchedDashboard;


    public void BatchP() throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        try {
            CookiesAccept.click();
        } catch (Exception e) {

        }
        WebElement Batch = WebElementSearcher.elementsearchSettlementCondition(wdriver, BatchProcessing);
        JavascriptExecutor js = (JavascriptExecutor)wdriver;
        js.executeScript("arguments[0].click()", Batch);
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
    }
    public void Statements(String PatientName)
    {

        JavascriptExecutor executor = (JavascriptExecutor)wdriver;
        int i;
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        seleniumAction.clickWebElementObject(Statement);
        JSWaiter.waitJQueryAngular();
        seleniumAction.clickWebElementObject(ThisWeek);
        JSWaiter.waitJQueryAngular();
        seleniumAction.clickWebElementObject(Search);
        JSWaiter.waitJQueryAngular();
        try{
            WebElement select = WebElementSearcher.elementsearchSettlementCondition(wdriver, SelAll);
            executor.executeScript("arguments[0].scrollIntoView(true);",select);
            executor.executeScript("arguments[0].click()", select);}
        catch (Exception e){
            JSWaiter.waitJQueryAngular();
            seleniumAction.clickWebElementObject(Search);
            WebElement select = WebElementSearcher.elementsearchSettlementCondition(wdriver, SelAll);
            executor.executeScript("arguments[0].scrollIntoView(true);",select);
            executor.executeScript("arguments[0].click()", select);}

        WebElement Lasts = WebElementSearcher.elementsearchSettlementCondition(wdriver, Last);
        executor.executeScript("arguments[0].scrollIntoView(true);",Lasts);
        seleniumAction.clickWebElementObject(Lasts);
        JSWaiter.waitJQueryAngular();
        List<WebElement> b = wdriver.findElements(By.xpath(XpathOfChecks));
        for( i=1; i<=b.size();i++) {
            String TName = wdriver.findElement(By.xpath(before_xpath + i + after_xpathk)).getText();
       if(TName.equals(PatientName))
       {
           System.out.println(i);
           b.get(i-1).click();
       }
        }
        executor.executeScript("window.scrollTo(0,document.body.scrollTop)");
       seleniumAction.clickWebElementObject(ExecuteJob);

    }

    public void ProcessQ(){
        seleniumAction.clickWebElementObject(BatchedDashboard);
        WebElement Queue = WebElementSearcher.elementsearchFluentWait(wdriver, ProcessQueue);
        Queue.click();
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        seleniumAction.clickWebElementObject(Today);
        List<WebElement> r = wdriver.findElements(By.xpath(RadioButtons));
        r.get(1).click();
        seleniumAction.clickWebElementObject(Search);
        WebElement JobStatus = WebElementSearcher.elementsearchSettlementCondition(wdriver, JobStatus_xpath);
        System.out.println(JobStatus);




    }

    public void MassageSubject(String Subject, String PatientName)
    {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        seleniumAction.clickWebElementObject(Inbox);
        JSWaiter.waitJQueryAngular();

        List<WebElement> b = wdriver.findElements(By.xpath(xpathofsemails));
        for( int i=1; i<=b.size();i++) {
            String SName = wdriver.findElement(By.xpath(before_xpath + i + after_xpathk)).getText();
            if(SName.contains(Subject))
            {
                String PName = wdriver.findElement(By.xpath(before_xpath + i + Patient_Name)).getText();
                    if (PName.contains(PatientName))
                    {
                        b.get(i).click();
                        break;
                    }

            }


    }
}

}
