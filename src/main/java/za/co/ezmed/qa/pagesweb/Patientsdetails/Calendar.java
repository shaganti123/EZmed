package za.co.ezmed.qa.pagesweb.Patientsdetails;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Calendar extends BaseClass {
    SeleniumAction seleniumAction;
    @FindBy(xpath = "//i[@class='fa fa-calendar']")
    WebElement CIcon;

    public Calendar(WebDriver driver) {
        super(driver);
        seleniumAction = new SeleniumAction(wdriver);
        seleniumAction.clickWebElementObject(CIcon);

    }
 public void calender(String month, String Day) throws InterruptedException {

    while (true) {
        String text = wdriver.findElement(By.xpath("//button[@class='btn btn-default btn-sm uib-title']")).getText();
        if (text.equals(month)) {
            break;
        } else {
            wdriver.findElement(By.xpath("//thead/tr[1]/th[1]/button[1]/i[1]")).click();
        }
    }

    wdriver.findElement(By.xpath("//button[@class='btn btn-default btn-sm']//span[contains(text()," + Day + ")]")).click();
}
}