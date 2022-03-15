package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;
import za.co.ezmed.qa.utils.Xls_Reader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GenaralDetails extends BaseClass {



    @FindBy(xpath  ="//input[@name='FirstName']")
    private WebElement FirstName;

    @FindBy(xpath = "//input[@name='LastName']")
    private WebElement LastName;

    @FindBy(xpath = "//input[@name='Initials']")
    private WebElement Initials;

    @FindBy(xpath = "//input[@name='IdentityValue']")
    private WebElement IdNumber;

    @FindBy(xpath = "//input[@placeholder='Complex Name']")
    private  WebElement ComplexName;

    @FindBy(xpath = "//input[@placeholder='Unit No.']")
    private WebElement UnitNo;

    @FindBy(xpath = "//input[@placeholder='Street Name']")
    private  WebElement StreetName;

    @FindBy(xpath = "//address-search//div[7]//input[1]")
    private WebElement CityName;

    @FindBy (xpath = "//input[contains(@placeholder,'Postal Code')]")
    private WebElement PostalCode;

    @FindBy (xpath = "//input[contains(@placeholder,'Province')]")
    private  WebElement Province;

    @FindBy (xpath = "//input[contains(@placeholder,'Country')]")
    private WebElement Country;

    @FindBy(xpath  ="//input[@type='email']")
    private WebElement Email;

    @FindBy(xpath = "//button[@class='btn btn-success pull-right']")
    private WebElement Next;

    @FindBy(xpath = "//select[@name='Title']")
    private WebElement title;

    @FindBy(xpath = "//select[@name='IdentityType']")
    private WebElement ID;

    @FindBy (xpath = "//input[@id='mapLookup']")
    private WebElement PhysicalAddress;

    @FindBy(xpath = "//select[@name='contactType']")
    private WebElement PContactType;

    @FindBy(xpath = "//input[@placeholder='*** *** ****']")
    private WebElement Mobile;

    public By PopUPText = By.xpath("//div[@class='toast toast-success']/div/div");

    String xpathOfRadio="//div[@class='radio-inline fa5']";
    String xpathOfCheck="//span[@class='checkbox-inline fa5']";

    @FindBy(xpath = "//button[@class='btn btn-default ng-binding ng-scope btn-primary']")
     private WebElement Accept;

    @FindBy(xpath = "//div//input[@name='councilNumber']")
    private WebElement CouncilNumber;

    @FindBy(xpath = "//div//input[@name='bhfNumber']")
    private WebElement BHFNumber;

    @FindBy(xpath = "//button[contains(@class,'round-button btn-success')][not(@disabled)]")
     private WebElement Save;

    @FindBy(xpath = "//button[@ng-click='ctrl.addPlaceOfServiceLink()']")
    private WebElement LinkPlaceOfService;
    @FindBy(xpath = "//div//input[@id='txtSearchPlace']")
    private WebElement SearchPlaces;
    @FindBy(xpath = "//button[@class='k-button btn-sm ng-scope']")
    private WebElement Select;
    @FindBy(xpath = "//img[@id='ezmedLogo']")
    private WebElement home;

    private By PoPUP = By.xpath("//div[@class='toast-message']");
    private By HOME = By.xpath("//img[@id='ezmedLogo']");




    SeleniumAction seleniumAction;
    //String projectPath = System.getProperty("user.dir");
    Xls_Reader reader = new Xls_Reader( "src/main/java/za/co/ezmed/qa/utils/addpatients.xlsx");


    public GenaralDetails(WebDriver driver)
    {
        super(driver);
        seleniumAction= new SeleniumAction(wdriver);

    }

    public boolean IdType(String ID) throws InterruptedException {


        if (ID.equalsIgnoreCase("Passport"))
        {
            String sheetName = "Passport";

            int rowCount = reader.getRowCount(sheetName);

            for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
                String IN = reader.getCellData(sheetName,"Initials", rowNum);
                String FN = reader.getCellData(sheetName, "FirstName", rowNum);
                String LN = reader.getCellData(sheetName, "LastName", rowNum);
                String IDN = reader.getCellData(sheetName, "IdNumber", rowNum);
                String CN = reader.getCellData(sheetName, "ComplexName",rowNum);
                String UN = reader.getCellData(sheetName, "UnitNo",rowNum);
                String SN = reader.getCellData(sheetName,"StreetName", rowNum);
                String City = reader.getCellData(sheetName,"CityName", rowNum);
                String PC = reader.getCellData(sheetName, "PostalCode", rowNum);
                String PV = reader.getCellData(sheetName, "Province", rowNum);
                String Ema= reader.getCellData(sheetName, "Email", rowNum);
               //String BN = reader.getCellData(sheetName, "BHFNumber",rowNum);


                Initials.sendKeys(IN);
                FirstName.sendKeys(FN);
                LastName.sendKeys(LN);
            //   BHFNumber.sendKeys(BN);
                IdNumber.sendKeys(IDN);
                ComplexName.sendKeys(CN);
                UnitNo.sendKeys(UN);
                StreetName.sendKeys(SN);
                CityName.sendKeys(City);
                PostalCode.sendKeys(PC);
                Province.sendKeys(PV);
               // Waitforelement();
                //BHFNumber.sendKeys(BN);


            }

        }
        else if (ID.equalsIgnoreCase("South African ID"))
        {
            String sheetName = "South African ID";

            int rowCount = reader.getRowCount(sheetName);

            for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
                String IN = reader.getCellData(sheetName,"Initials", rowNum);
                String FN = reader.getCellData(sheetName, "FirstName", rowNum);
                String LN = reader.getCellData(sheetName, "LastName", rowNum);
                String IDN = reader.getCellData(sheetName, "IdNumber", rowNum);
                String PA = reader.getCellData(sheetName, "PhysicalAddress",rowNum);
                String BN = reader.getCellData(sheetName, "BHFNumber",rowNum);



                Initials.sendKeys(IN);
                FirstName.sendKeys(FN);
                LastName.sendKeys(LN);
                IdNumber.sendKeys(IDN);
                PhysicalAddress.sendKeys(PA);
                wdriver.findElements(By.cssSelector(".pac-item")).get(0).click();
                BHFNumber.sendKeys(BN);
            }
        }

        return true;
    }

    public void countryautoSuggestion(String cn) throws InterruptedException, AWTException {
        wdriver.findElement(By.xpath("//input[@placeholder='Country']")).sendKeys(cn);
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        List<WebElement> list = wdriver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::a[@class='ng-binding ng-scope']"));
        //System.out.println(list);

        for (WebElement element: list) {
            System.out.println(element.getText());
            element.click();
        }
/*
      for(int i = 0 ;i<=list.size();i++)
        {

           // String t=list.get(i).getText();
           // System.out.println(t);
            if(list.get(i).getText().contains(cn))
            {
                Waitforelement();
                list.get(i).click();
                break;
            }
        }

 */
    }


    public void title1 (String tSelect) {
      ImplicitWait();

        seleniumAction.clickWebElementObject(title);
      seleniumAction.dropdownValue(title,tSelect);

    }

    public void LinkPlace() throws InterruptedException {
        Waitforelement();
        seleniumAction.clickWebElementObject(LinkPlaceOfService);
        SearchPlaces.sendKeys("Curae Occupational Therapy");
        Waitforelement();
        Select.click();
    }



    public void IdentityType(String idSelect){

        seleniumAction.clickWebElementObject(ID);
        seleniumAction.dropdownValue(ID, idSelect);
    }


    public void radioButton (String RB) throws InterruptedException {


        seleniumAction.scrollDown();
        Waitforelement();
        if(RB.equalsIgnoreCase("MRadio"))
        {
            List<WebElement> r = wdriver.findElements(By.xpath(xpathOfRadio));
            Waitforelement();
            r.get(0).click();

        }
        else if(RB.equalsIgnoreCase("FRadio"))
        {
            List<WebElement> r = wdriver.findElements(By.xpath(xpathOfRadio));
            Waitforelement();
            r.get(1).click();
        }
        else if(RB.equalsIgnoreCase("ORadio"))
        {
            List<WebElement> r = wdriver.findElements(By.xpath(xpathOfRadio));
            Waitforelement();
            r.get(2).click();
        }



    }

    public void ProviderCheckBoxType(String Check) throws InterruptedException {
        if(Check.equalsIgnoreCase("Locum"))
        {
            List<WebElement> c = wdriver.findElements(By.xpath(xpathOfCheck));
            Waitforelement();
            c.get(0).click();
            seleniumAction.clickWebElementObject(Accept);
            BHFNumber.sendKeys("1253025");

        }
        else if(Check.equalsIgnoreCase("ReferringProvider"))
        {
            List<WebElement> c = wdriver.findElements(By.xpath(xpathOfCheck));
            Waitforelement();
            c.get(1).click();
            BHFNumber.sendKeys("1253025");
        }
        else if(Check.equalsIgnoreCase("TreatingProvider"))
        {
            List<WebElement> c = wdriver.findElements(By.xpath(xpathOfCheck));
            JSWaiter.setDriver(this.wdriver);
            JSWaiter.waitUntilAngularReady();
            c.get(2).click();
            seleniumAction.clickWebElementObject(Accept);
            BHFNumber.sendKeys("1253025");
        }
    }

    public void PreferredContact(String PContact) throws InterruptedException {

        //seleniumAction.clickWebElementObject(PContactType);
        seleniumAction.dropdownValue(PContactType, PContact);

        if (PContact.equalsIgnoreCase("Email"))
        {
            Email.sendKeys("test@gmail.com");
        }
        else if (PContact.equalsIgnoreCase("Both Email & Mobile Number"))
        {
            Email.sendKeys("btest@gmail.com");
            Mobile.sendKeys("0784456663");

        }


    }
public void next(String next) throws InterruptedException {
    JSWaiter.setDriver(this.wdriver);
    JSWaiter.waitUntilAngularReady();
        if (next.equalsIgnoreCase("Next"))
        {
            seleniumAction.scrollDown();
            Waitforelement();
            seleniumAction.clickWebElementObject(Next);
        }
    else if(next.equalsIgnoreCase("Save"))
       {
           Save.click();
           new WebDriverWait(wdriver, 10).until(ExpectedConditions.invisibilityOfElementLocated(PopUPText));

       }

}

    
}

