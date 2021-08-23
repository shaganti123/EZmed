package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class Institution  extends BaseClass {
    SeleniumAction seleniumAction;

    public Institution(WebDriver driver) {
        super(driver);
       seleniumAction = new SeleniumAction(wdriver);
    }
    @FindBy(xpath = "//span[@class='welcome ng-binding']")
    private WebElement institute;
    @FindBy(xpath = "//input[contains(@placeholder,'Search for Institution...')]")
    private WebElement institutePlace;
    @FindBy(xpath = "//div[contains(@class,'list-group-item ng-sc')]")
    private WebElement instiSelect;


    public boolean Institute(String insti1) throws InterruptedException {


        seleniumAction.waitForElementToBeVisible(institute);
        String InstituionType = institute.getText();
        if (!InstituionType.contains(insti1)) {
            seleniumAction.waitForElementToBeClickable(institute);
            seleniumAction.clickWebElementObject(institute);
            seleniumAction.waitForElementToBeClickable(institutePlace);
            Waitforelement();
            seleniumAction.typeText(institutePlace, insti1);
            seleniumAction.clickWebElementObject(instiSelect);
        }

        return true;
    }
}