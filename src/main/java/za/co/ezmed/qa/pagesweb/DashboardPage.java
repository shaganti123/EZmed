package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;

public class DashboardPage extends BaseClass {


    @FindBy(xpath = "//div[@class='thumbnail']//i[@class='fal fa-user-md fa-4x']")
    private WebElement providers;
    @FindBy(xpath = "//div[@class='thumbnail']//i[@class='fal fa-4x fa-user-circle']")
    private WebElement P;
    @FindBy(xpath = "//input[@id='txtSearchProvider']")
    private WebElement Provider;

    @FindBy(xpath = "//*[@class='c-bn']")
    private WebElement CookiesAccept;
    public By Home = By.xpath("//img[@id='ezmedLogo']");
    public By patients = By.xpath("//div[@class='thumbnail']//i[@class='fal fa-4x fa-user-circle']");
    JavascriptExecutor js = (JavascriptExecutor)wdriver;
    SeleniumAction seleniumAction;
    public DashboardPage (WebDriver driver)
    {
        super(driver);
        seleniumAction= new SeleniumAction(wdriver);
    }

    public void patients() throws InterruptedException {

            try {
                CookiesAccept.click();
            } catch (Exception e) {

            }
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitForAngularLoad();
        ImplicitWait();
        WebElement patient=WebElementSearcher.elementsearchFluentWait(wdriver,patients);
        js.executeScript("arguments[0].click()", patient);
    }

    public void Providers() throws InterruptedException {
        Waitforelement();
        WebElement home=WebElementSearcher.elementsearchSettlementCondition(wdriver,Home);
        home.click();
       // seleniumAction.clickWebElementObject(home);
        ImplicitWait();
        seleniumAction.waitForElementToBeClickable(providers);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(providers);
    }
    public boolean searchProvider(String PId) throws InterruptedException {

        seleniumAction.waitForElementToBeClickable(Provider);
        seleniumAction.typeText(Provider,PId);
        //seleniumAction.clickWebElementObject(Actions);

        return true;
    }

}
