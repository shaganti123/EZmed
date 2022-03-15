package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.apache.commons.lang3.event.EventListenerSupport;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.util.Calendar;
import java.util.List;

public class Calendar1 extends BaseClass {
    SeleniumAction seleniumAction;
    @FindBy(xpath = "//span//button[contains(@ng-click,'DatePickerOpen()')]")
    WebElement DOB;

    @FindBy(xpath = "//span//button[contains(@ng-click,'IncidentDateOpen')]")
    WebElement INC;

    @FindBy(xpath = "//span//button[contains(@ng-click,'endDateOpen')]")
    WebElement End;

   // @FindBy(xpath = "//span//button[contains(@ng-click,'treatmentDateOpen')]")
   // WebElement TreatmentStart;

    private By TreatmentStart = By.xpath("//span//button[contains(@ng-click,'treatmentDateOpen')]");

    @FindBy(xpath = "//thead/tr[1]/th[3]/button[1]/i[1]")
    private WebElement Next;

    @FindBy(xpath = "//thead/tr[1]/th[1]/button[1]/i[1]")
    private WebElement Previous;

    @FindBy(xpath = "//button[@class='btn btn-default btn-sm uib-title']")
    private WebElement midLink;

    @FindBy(xpath = "//input[@placeholder='HH']")
    private WebElement HR;

    @FindBy(xpath = "//input[@placeholder='MM']")
    private WebElement MM;
    @FindBy(xpath = "//input[@name='EndTime']")
    private WebElement Endtime;
    @FindBy(xpath = "//button[contains(text(),'Done')]")
    private WebElement DoneButton;
    @FindBy(xpath = "//button[contains(text(),'Create')]")
    private WebElement Confirm;
    @FindBy(xpath = "//button[contains(text(),'Create & Notify')]")
    private WebElement CreateNotify;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    private WebElement Cancel;

    @FindBy(xpath = "//div[@ng-if='showEmergencyCheck']")
    private WebElement Emergency;
    @FindBy(xpath = "//button[@ng-class='item.class'][2]")
    private WebElement Accept;

    List<WebElement> Cbutton= wdriver.findElements(By.xpath("//span[@class='input-group-btn']//button[@class='btn btn-default']"));

    public Calendar1(WebDriver driver)
    {
        super(driver);
        seleniumAction = new SeleniumAction(wdriver);

    }

    public  void buttons(String CalenderIcon) throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitUntilAngularReady();
        if(CalenderIcon.equalsIgnoreCase("DOB"))
        {
            JSWaiter.setDriver(this.wdriver);
            JSWaiter.waitUntilAngularReady();;
            seleniumAction.clickWebElementObject(DOB);
        }
        else if (CalenderIcon.equalsIgnoreCase("Start"))
        {
            Cbutton.get(0).click();
           // seleniumAction.clickWebElementObject(Start);
        }
        else if (CalenderIcon.equalsIgnoreCase("End"))
        {
            Cbutton.get(1).click();
            //seleniumAction.clickWebElementObject(End);
        }
        else if (CalenderIcon.equalsIgnoreCase("Treatment"))
        {
            JSWaiter.setDriver(this.wdriver);
            JSWaiter.waitJQueryAngular();
            WebElement Treat= WebElementSearcher.elementsearchSettlementCondition(wdriver,TreatmentStart);
            Treat.click();
        }
        else if (CalenderIcon.equalsIgnoreCase("EFFFrom"))
        {
            Cbutton.get(3).click();
        }
        else if (CalenderIcon.equalsIgnoreCase("EFFTo"))
        {
            Cbutton.get(4).click();
        }
        else if(CalenderIcon.equalsIgnoreCase("IncidentDate"))
        {
            Waitforelement();
            seleniumAction.clickWebElementObject(INC);
        }

    }
    public void calender(String Date) throws InterruptedException
    {


        //Split the date
        String date_dd_MM_yyyy[] = (Date.split(" ")[0]).split("/");

        //get the year difference between current year and year to set in calander
        int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);
        Waitforelement();
        midLink.click();

        if (yearDiff != 0)
        {

            //if you have to move next year

            if (yearDiff > 0)
            {

                for (int i = 0; i < yearDiff; i++)
                {

                        Next.click();
                }
            }
            else if (yearDiff < 0)
            {

                for (int i = 0; i < (yearDiff * (-1)); i++)
                {
                    Previous.click();
                }
            }
        }

        //Get all months from calendar to select correct one

        List<WebElement> list_AllMonthToBook = wdriver.findElements(By.xpath("//tbody//tr[@class='uib-months ng-scope']//button[@type='button']"));
        Waitforelement();
         WebElement month = list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1);
         month.click();

        //get all dates from calendar to select correct one

        List<WebElement> list_AllDateToBook = wdriver.findElements(By.xpath("//tbody//tr[@class='uib-weeks ng-scope']//button//span[@class='ng-binding']"));
        Waitforelement();
       WebElement date= list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1);
       date.click();
        Screenshot.takeScreenshot(wdriver);
    }


    public void StartTime(String Hr, String min) throws InterruptedException {
         if (HR.isEnabled())
         {
             HR.clear();
             seleniumAction.typeText(HR,Hr);
             //seleniumAction.clearText(MM);
             MM.clear();
             seleniumAction.typeText(MM,min);
             seleniumAction.clickWebElementObject(DoneButton);


         }
         else
             {
                 seleniumAction.clickWebElementObject(Cancel);
             }
    }


    public void EndTime(String Hr, String min) {
            HR.clear();
            seleniumAction.typeText(HR, Hr);
            MM.clear();
            seleniumAction.typeText(MM, min);
            seleniumAction.clickWebElementObject(DoneButton);
            seleniumAction.clickWebElementObject(CreateNotify);


    }
    public void EndTime1(String Hr, String min) {
        seleniumAction.clickWebElementObject(Endtime);
        HR.clear();
        seleniumAction.typeText(HR, Hr);
        MM.clear();
        seleniumAction.typeText(MM, min);
        seleniumAction.scrollDown();
        seleniumAction.clickWebElementObject(DoneButton);
        JavascriptExecutor js = (JavascriptExecutor) wdriver;
        js.executeScript("arguments[0].scrollIntoView();", Confirm);
        seleniumAction.clickWebElementObject(Confirm);

    }
    public void EndTimeEme(String Hr, String min) {
        JavascriptExecutor js = (JavascriptExecutor) wdriver;
        seleniumAction.clickWebElementObject(Endtime);
        HR.clear();
        seleniumAction.typeText(HR, Hr);
        MM.clear();
        seleniumAction.typeText(MM, min);
        seleniumAction.scrollDown();
        seleniumAction.clickWebElementObject(DoneButton);
        Emergency.click();
        js.executeScript("arguments[0].scrollIntoView();", Accept);
        seleniumAction.clickWebElementObject(Accept);
        js.executeScript("arguments[0].scrollIntoView();", Confirm);
        seleniumAction.clickWebElementObject(Confirm);

    }

}