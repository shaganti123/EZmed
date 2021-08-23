package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;

public class NewPatients extends BaseClass {

    //@FindBy(xpath = "")
    //private WebElement AddPatients;

    private By AddPatients = By.xpath("//button[@class='btn btn-success']");


    @FindBy(xpath = "//input[@id='txtSearchPatient']")
    private WebElement PatientSearch;

    @FindBy(xpath = "//button[@id='btnDD_0']")
    private WebElement Actions;

    private By Actions1 = By.xpath("//button[@ng-click='dropdown($event)']");



SeleniumAction seleniumAction;
    public NewPatients(WebDriver driver)
    {
        super(driver);
        seleniumAction= new SeleniumAction(wdriver);
    }

    public boolean addpatients() throws InterruptedException {

        Screenshot.takeScreenshot(wdriver);
        WebElement Add=WebElementSearcher.elementsearchSettlementCondition(wdriver,AddPatients);
        seleniumAction.clickWebElementObject(Add);

        return true;
    }

    public boolean searchPatient(String PId) throws InterruptedException {

        seleniumAction.waitForElementToBeClickable(PatientSearch);
        seleniumAction.typeText(PatientSearch,PId);
        //seleniumAction.clickWebElementObject(Actions);
        Waitforelement();
        WebElement Act =WebElementSearcher.elementsearchWithTimeLimit(wdriver,Actions1,5);
        Act.click();
        return true;
    }

}
