package za.co.ezmed.qa.pagesweb.Patientsdetails;


import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.Screenshot;

public class Providers extends BaseClass {

    @FindBy(xpath = "//span[@class='hidden-xs hidden-sm']")
    private WebElement AddNewProvider;

    SeleniumAction seleniumAction;
    public Providers(WebDriver driver) {
        super(driver);
        seleniumAction= new SeleniumAction(wdriver);
    }
    public boolean addProvider() throws InterruptedException {
        Waitforelement();
        //  seleniumAction.waitForElementToBeClickable(AddPatients);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(AddNewProvider);
        return true;
    }

}
