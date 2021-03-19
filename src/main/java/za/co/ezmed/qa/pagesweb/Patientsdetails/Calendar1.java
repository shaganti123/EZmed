package za.co.ezmed.qa.pagesweb.Patientsdetails;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.Screenshot;

import java.util.Calendar;
import java.util.List;

public class Calendar1 extends BaseClass {
    SeleniumAction seleniumAction;
    @FindBy(xpath = "//span//button[contains(@ng-click,'DatePickerOpen()')]")
    WebElement DOB;

    @FindBy(xpath = "//span//button[contains(@ng-click,'startDateOpen')]")
    WebElement Start;

    @FindBy(xpath = "//span//button[contains(@ng-click,'endDateOpen')]")
    WebElement End;

    @FindBy(xpath = "//span//button[contains(@ng-click,'treatmentDateOpen')]")
    WebElement TreatmentStart;

    @FindBy(xpath = "//thead/tr[1]/th[3]/button[1]/i[1]")
    private WebElement Next;

    @FindBy(xpath = "//thead/tr[1]/th[1]/button[1]/i[1]")
    private WebElement Previous;

    @FindBy(xpath = "//button[@class='btn btn-default btn-sm uib-title']")
    private WebElement midLink;

    List<WebElement> Cbutton= wdriver.findElements(By.xpath("//span[@class='input-group-btn']//button[@class='btn btn-default']"));


    public Calendar1(WebDriver driver)
    {
        super(driver);
        seleniumAction = new SeleniumAction(wdriver);

    }

    public  void buttons(String CalenderIcon) throws InterruptedException {
        Waitforelement();
        if(CalenderIcon.equalsIgnoreCase("DOB"))
        {
            Waitforelement();
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
            seleniumAction.clickWebElementObject(TreatmentStart);
        }
        else if (CalenderIcon.equalsIgnoreCase("EFFFrom"))
        {
            Cbutton.get(3).click();
        }
        else if (CalenderIcon.equalsIgnoreCase("EFFTo"))
        {
            Cbutton.get(4).click();
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
}