package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.Screenshot;

public class DashboardPage extends BaseClass {

    @FindBy(xpath = "//a[text()='Home']")
    private WebElement home;

    @FindBy(xpath = "//nav-button[1]//a[1]//div[1]//div[1]")
    private WebElement patients;

    @FindBy(xpath = "//div[@class='thumbnail']//i[@class='fal fa-user-md fa-4x']")
    private WebElement providers;

    SeleniumAction seleniumAction;


    public DashboardPage (WebDriver driver)
    {
        super(driver);
        seleniumAction= new SeleniumAction(wdriver);
    }

    public boolean home()
    {
        seleniumAction.clickWebElementObject(home);
        return true;
    }

    public void patients()
    {
        ImplicitWait();
        seleniumAction.waitForElementToBeClickable(patients);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(patients);

    }

    public void Providers()
    {
        ImplicitWait();
        seleniumAction.waitForElementToBeClickable(providers);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(providers);
    }


}
