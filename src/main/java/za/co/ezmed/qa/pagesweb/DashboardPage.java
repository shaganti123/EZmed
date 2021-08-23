package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;

public class DashboardPage extends BaseClass {


    @FindBy(xpath = "//div[@class='thumbnail']//i[@class='fal fa-user-md fa-4x']")
    private WebElement providers;

    @FindBy(xpath = "//*[@class='c-bn']")
    private WebElement CookiesAccept;
    //@FindBy(xpath = "//img[@id='ezmedLogo']")
    //private WebElement home;
    @FindBy(xpath = "//div[@class='thumbnail']//i[@class='fal fa-4x fa-user-circle']")
    private WebElement P;
    @FindBy(xpath = "//input[@id='txtSearchProvider']")
    private WebElement Provider;

    public By Home = By.xpath("//img[@id='ezmedLogo']");
    //@FindBy(xpath = "//div[@class='thumbnail']//i[@class='fal fa-4x fa-user-circle']")
    //  private WebElement patients;
    public By patients = By.xpath("//div[@class='thumbnail']//i[@class='fal fa-4x fa-user-circle']");
    SeleniumAction seleniumAction;
    public DashboardPage (WebDriver driver)
    {
        super(driver);
        seleniumAction= new SeleniumAction(wdriver);
    }

    public void patients() throws InterruptedException {
        Waitforelement();
        if (CookiesAccept.isDisplayed())
        {
            CookiesAccept.click();
        }
        WebElement home=WebElementSearcher.elementsearchSettlementCondition(wdriver,Home);
        home.click();
        Waitforelement();
        WebElement patient=WebElementSearcher.elementsearchFluentWait(wdriver,patients);
        patient.click();
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
