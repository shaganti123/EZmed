package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.util.List;

public class Funder extends BaseClass {
    SeleniumAction seleniumAction;
    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    String xpathofdrop= "//div[contains(@class,'col-sm')]//select/descendant::option[@ng-repeat='p in PatientFunders']";
    private By Add = By.xpath("//button/i[@class='fa fa-plus']");
    private By FunderType = By.xpath("//select[@ng-change='loadPatientFunders()']");
   private By FT = By.xpath("//select[@ng-change='loadPatientFunders()']");
    @FindBy(xpath = "//select[@ng-change='loadPatientFunders()']")
    private WebElement FunderTrype;
    @FindBy(xpath = "//select[contains(@ng-model,'.Patient')]")
    private WebElement PatientFunders;
    @FindBy(xpath = "//select[contains(@ng-model,'ngModel.Incident.PatientEmployer')]")
    private  WebElement Funders;

    public Funder(WebDriver driver) {
        super(driver);
        seleniumAction= new SeleniumAction(wdriver);
    }
    public void PatientFunders() throws InterruptedException {
        Waitforelement();
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        String text= b.get(1).getText();
        System.out.println(text);
        if(text.contains("Health")){
        b.get(2).click();
        }
        else if (!text.contains("Health"))
        {
            b.get(1).click();
        }
        WebElementSearcher.WaitForAjax2Complete(wdriver);
        Thread.sleep(5000);
        WebElement AddNew = WebElementSearcher.elementsearchFluentWait(wdriver,Add);
        AddNew.click();



    }
    public boolean FunderType(String FType) throws InterruptedException {
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

       Thread.sleep(10000);
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
