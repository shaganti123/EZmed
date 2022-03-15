package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import za.co.ezmed.qa.utils.DocumentSettleCondition;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.util.List;

public class Funder extends BaseClass {
    SeleniumAction seleniumAction;
    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    String xpathofdrop= "//div[contains(@class,'col-sm')]//select/descendant::option[@ng-repeat='p in PatientFunders']";
    private By Add = By.xpath("//button[@class='btn btn-success pull-right btn-margin-bottom-10']");
   // @FindBy(xpath = "//button[@class='btn btn-success pull-right btn-margin-bottom-10']")
   // private WebElement Add;
    private By FunderType = By.xpath("//select[@ng-change='loadPatientFunders()']");
   private By FT = By.xpath("//select[@ng-change='loadPatientFunders()']");
    @FindBy(xpath = "//select[@ng-change='loadPatientFunders()']")
    private WebElement FunderTrype;
    @FindBy(xpath = "//select[contains(@ng-model,'.Patient')]")
    private WebElement PatientFunders;
    @FindBy(xpath = "//select[contains(@ng-model,'ngModel.Incident.PatientEmployer')]")
    private  WebElement Funders;
    JavascriptExecutor js = (JavascriptExecutor) wdriver;

    public Funder(WebDriver driver) {
        super(driver);
        seleniumAction= new SeleniumAction(wdriver);
    }
    public void PatientFunders() throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        for(int i =0;i<=b.size();i++)
        {
            String text= b.get(i).getText();
            if(text.contains("Funder")) {
                b.get(i).click();
                break;
            }
        }
        JSWaiter.waitUntilAngularReady();
        WebElement AddNew = WebElementSearcher.elementsearchFluentWait(wdriver,Add);
       // AddNew.click();
        js.executeScript("arguments[0].click()", AddNew);

    }
    public boolean FunderType(String FType)  {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitUntilAngularReady();
        WebElement FT = WebElementSearcher.elementsearchSettlementCondition(wdriver,FunderType);
        seleniumAction.dropdownValue(FT, FType);
        return true;
    }
    public boolean Funder1(String PatientFunder)
    {
       // seleniumAction.dropdownValue(Funders, PatientFunder);
        seleniumAction.typeText(Funders,PatientFunder);

        return true;
    }



    public boolean Funder(String PatientFunder) throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitUntilAngularReady();
        List<WebElement> listoption=wdriver.findElements(By.xpath(xpathofdrop));

        seleniumAction.clickWebElementObject(PatientFunders);
        //wdriver.findElement(By.xpath("//select[contains(@ng-model,'config.PatientFunder')]")).click();
        Waitforelement();

        if (listoption.size() != 0){
            Waitforelement();
            for (int i = 0; i <= listoption.size(); i++) {
                String text = listoption.get(i).getText();
                if (text.contains(PatientFunder)) {
                    listoption.get(i).click();
                    break;
                }

            }


    }
        return true;
}

}
