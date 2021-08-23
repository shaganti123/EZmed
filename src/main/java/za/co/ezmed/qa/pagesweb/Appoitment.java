package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

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
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(3).click();
        Waitforelement();
        Actions actions = new Actions(wdriver);
        List<WebElement> elementLocator = wdriver.findElements(By.xpath("//td[@role='gridcell'][5]"));
        actions.doubleClick(elementLocator.get(0)).perform();
        seleniumAction.dropdownValue(Provider,"Nombe, Velly");
        seleniumAction.dropdownValue(PlaceofService,"Ferncrest Hospital");
        seleniumAction.typeText(Title,"Automation");

    }

}
