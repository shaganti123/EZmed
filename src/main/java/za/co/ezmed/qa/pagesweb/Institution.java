package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.WebElementSearcher;


public class Institution  extends BaseClass {
    SeleniumAction seleniumAction;

    public Institution(WebDriver driver) {
        super(driver);
       seleniumAction = new SeleniumAction(wdriver);
    }
    @FindBy(xpath = "//span[@class='welcome ng-binding']")
    private WebElement institute;

    public By institutes = By.xpath("//div/span[@class='welcome ng-binding']");
    public By Home = By.xpath("//div[@class='navbar-header hidden-xs']/a/img");
    @FindBy(xpath = "//input[contains(@placeholder,'Search for Institution...')]")
    private WebElement institutePlace;
    @FindBy(xpath = "//div[contains(@class,'list-group-item ng-sc')][1]")
    private WebElement instiSelect;

    public void Institute(String Ins) throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        ImplicitWait();
        WebElement home=WebElementSearcher.elementsearchFluentWait(wdriver,Home);
       seleniumAction.clickWebElementObject(home);
        JSWaiter.waitUntilAngularReady();
        WebElement INS = WebElementSearcher.elementsearchFluentWait(wdriver, institutes);
        String InstituionName = INS.getText();
        if (InstituionName.contains(Ins)==false) {
            seleniumAction.clickWebElementObject(INS);
            seleniumAction.waitForElementToBeClickable(institutePlace);
            JSWaiter.setDriver(this.wdriver);
            JSWaiter.waitUntilAngularReady();
            seleniumAction.typeText(institutePlace, Ins);
            seleniumAction.clickWebElementObject(instiSelect);
        }

    }
}