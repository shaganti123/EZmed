package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import za.co.ezmed.qa.utils.JSWaiter;

import java.util.List;

public class Appoitment extends BaseClass {
    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    SeleniumAction seleniumAction;
    @FindBy(xpath = "//select[@ng-model='ngModel.Provider.PersonGuid']")
    WebElement Provider;
    @FindBy(xpath = "//select[@ng-model='ngModel.PlaceOfService.LocationGuid']")
    WebElement PlaceofService;
    @FindBy(xpath = "//input[@placeholder='Title']")
    WebElement Title;


    public Appoitment(WebDriver driver) {
        super(driver);
        seleniumAction = new SeleniumAction(this.wdriver);

    }
    public void PatientAppointments() throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        for(int i =0;i<=b.size();i++)
        {
            String text= b.get(i).getText();
            if(text.contains("Appt")) {
                b.get(i).click();
                break;
            }
        }
        Actions actions = new Actions(wdriver);
        JSWaiter.waitJQueryAngular();
        List<WebElement> elementLocator = wdriver.findElements(By.xpath("//tbody/tr/td[@role='gridcell']"));
        ImplicitWait();
        //JavascriptExecutor js = (JavascriptExecutor) wdriver;
      //  WebElement e=elementLocator.get(1);
     //   js.executeScript("arguments[0].doubleClick();",e);
        actions.doubleClick(elementLocator.get(5)).perform();
        JSWaiter.waitJQueryAngular();
        Select P = new Select(Provider);
        P.selectByIndex(1);
        Select PS = new Select(PlaceofService);
        PS.selectByIndex(1);
        seleniumAction.typeText(Title,"Automation");

    }

}
