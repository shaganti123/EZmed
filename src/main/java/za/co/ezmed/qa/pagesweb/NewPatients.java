package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.Screenshot;

public class NewPatients extends BaseClass {

    @FindBy(xpath = "//button[@class='btn btn-success']")
    private WebElement AddPatients;

    @FindBy(xpath = "//input[@id='txtSearchPatient']")
    private WebElement PatientSearch;



SeleniumAction seleniumAction;
    public NewPatients(WebDriver driver)
    {
        super(driver);
        seleniumAction= new SeleniumAction(wdriver);
    }

    public boolean addpatients() throws InterruptedException {
        Waitforelement();
      //  seleniumAction.waitForElementToBeClickable(AddPatients);
        //Screenshot.takeScreenshot(wdriver);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(AddPatients);
        return true;
    }

    public boolean searchPatient(String PId)
    {
        seleniumAction.waitForElementToBeClickable(PatientSearch);
        seleniumAction.typeText(PatientSearch,PId);
        return true;
    }

}
