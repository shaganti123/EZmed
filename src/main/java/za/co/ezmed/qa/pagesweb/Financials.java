package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.WebElementSearcher;
import za.co.ezmed.qa.utils.Xls_Reader;

import java.util.List;

public class Financials extends BaseClass {
    SeleniumAction seleniumAction;
    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    String XpathofPayment = "//td[@class='currency ng-binding'][3]";
    String XpathOfOpen = "//tr[@ng-repeat-start='t in ctrl.trxVisible']//td[@class='text-right hidden-xs ng-binding'][1]";
    String XpathOfCheck = "//i[@class='fal']";
    String BeforeT = "//tbody[2]/tr/td[";
    String AfterT = "][@class='ng-binding']";
    // String ActualT="]";

    public By MethodOfPayment = By.xpath("//select[@name='BankAccount']");
    public By AmountXpath = By.xpath("//select[@name='BankAccount']");
    public By Add = By.xpath("//span[contains(text(),'Add Payment')]");
    public By Menu = By.xpath("//button[@class='btn btn-info']");
    public By AutoAllocateCredits = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/span[1]/label[1]/i[1]");
    String menutext="//div[@class='btn-group  bg open']//ul//li//button";
    @FindBy(xpath = "//span[@class='welcome ng-binding']")
    private WebElement institute;
    @FindBy(xpath = "//input[@name='PaymentReceived']")
    private WebElement Amount;
    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/receipts[1]/div[1]/ng-form[1]/div[1]/div[1]/div[6]/div[2]/span[1]/label[1]/i[1]")
    private WebElement FunderCheck;
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/receipts[1]/div[1]/ng-form[1]/div[3]/div[1]/div[1]/table[1]/tbody[2]/tr[1]/td[1]/span[1]/label[1]/i[1]")
    private WebElement OpenCheck;
    @FindBy(xpath = "//body/div[1]/div[1]/div[1]/div[3]/button[2][not(@disabled)]")
    private WebElement Save;
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/form[1]/div[1]/div[1]/table[1]/thead[1]/tr[2]/th[1]/span[1]/label[1]/i[1]")
    private WebElement Debits;
    @FindBy(xpath = "//button[@class='btn btn-primary ng-binding']")
    private WebElement P;


    String brefore_xpath="//tbody/tr[";
    String after_sxpath="]/td[9]";
    String after_CREFXpath="]/td[@class='hidden-xs ng-binding'][1]";
    String OpenTrans="//tbody[contains(@ng-show,'!receiptsFactory.isSearching && re')]//tr[contains(@ng-repeat-start,'i in receiptsFactory')]";

    public Financials(WebDriver driver) {
        super(driver);
        seleniumAction = new SeleniumAction(wdriver);
    }

    public void Financial()
    {

        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        for(int i =0;i<=b.size();i++)
        {
            String text= b.get(i).getText();
            if(text.contains("Financials")) {
                b.get(i).click();
                break;
            }
        }


    }

/*
        public void AddPayment(String MOP) throws InterruptedException {
            List<WebElement> Open= wdriver.findElements(By.xpath(XpathOfOpen));
            System.out.println(Open.size());
            for (int i=1;i<=Open.size();i++) {
                WebElement Addpayment = WebElementSearcher.elementsearchFluentWait(wdriver,Add);
                seleniumAction.clickWebElementObject(Addpayment);
                WebElement MeOP = WebElementSearcher.elementsearchFluentWait(wdriver,MethodOfPayment);
                seleniumAction.dropdownValue(MeOP, MOP);
                seleniumAction.clearText(Amount);
                Waitforelement();
                List<WebElement> amount = wdriver.findElements(By.xpath(XpathofPayment));
                Waitforelement();
                String a = amount.get(0).getText();
                seleniumAction.typeText(Amount, a);
                seleniumAction.clickWebElementObject(FunderCheck);
                seleniumAction.clickWebElementObject(OpenCheck);
                seleniumAction.clickWebElementObject(Save);

            }
        }

 */

    public void AddPayments(String MOP)  {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitUntilAngularReady();
        List<WebElement> Open = wdriver.findElements(By.xpath(XpathOfOpen));
        System.out.println(Open.size());
        for (int i = 1; i <= Open.size(); i++) {
            JSWaiter.waitUntilAngularReady();
            WebElement Addpayment = WebElementSearcher.elementsearchFluentWait(wdriver, Add);
            seleniumAction.clickWebElementObject(Addpayment);
            JSWaiter.waitUntilAngularReady();
            WebElement MeOP = WebElementSearcher.elementsearchFluentWait(wdriver, MethodOfPayment);
            ImplicitWait();
            seleniumAction.dropdownValue(MeOP, MOP);
            seleniumAction.clearText(Amount);
            JSWaiter.setDriver(this.wdriver);
            JSWaiter.waitUntilAngularReady();
            // WebElement MeOP = WebElementSearcher.elementsearchFluentWait(wdriver, AmountXpath);
            List<WebElement> amount = wdriver.findElements(By.xpath(XpathofPayment));
            String a = amount.get(0).getText();
            seleniumAction.typeText(Amount, a);
            seleniumAction.clickWebElementObject(FunderCheck);
            seleniumAction.clickWebElementObject(OpenCheck);
            seleniumAction.clickWebElementObject(Save);


        }
        try{
            JSWaiter.waitUntilAngularReady();
            JavascriptExecutor js = (JavascriptExecutor) wdriver;
            js.executeScript("arguments[0].scrollIntoView();", P);
            P.click();

        }catch (Exception e){

            }

    }
    public void FullPayment() {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitUntilAngularReady();
        List<WebElement> amount = wdriver.findElements(By.xpath(XpathofPayment));
        String a = amount.get(0).getText();
        seleniumAction.typeText(Amount, a);
        seleniumAction.clickWebElementObject(FunderCheck);
        seleniumAction.clickWebElementObject(OpenCheck);
        seleniumAction.clickWebElementObject(Save);
    }

    public void MenuItems(String Text)
    {
        WebElement menu=  WebElementSearcher.elementsearchFluentWait(wdriver,Menu);
        menu.click();
        List<WebElement> b = wdriver.findElements(By.xpath(menutext));
        for(int i=0;i<=b.size();i++)
        {
            String text=b.get(i).getText();
            if(text.equals(Text))
            {
                b.get(i).click();
                break;
            }
        }

    }
    public void reconcile()
    {
        WebElement AutoAllocateCredit=  WebElementSearcher.elementsearchFluentWait(wdriver,AutoAllocateCredits);
        AutoAllocateCredit.click();
        Debits.click();

    }

    public void ClaimRef(String CliamStatus) throws InterruptedException {
        WebElementSearcher.WaitForAjax2Complete(wdriver);
        Thread.sleep(5000);
        for( int k=1; k<=10;k++) {
            String CStatus = wdriver.findElement(By.xpath(brefore_xpath + k + after_sxpath)).getText();
            System.out.println(CStatus);
            if(CStatus.contains(CliamStatus))
            {
                String ClaimRef = wdriver.findElement(By.xpath(brefore_xpath + k + "]/td[1]")).getText();
                System.out.println(ClaimRef);
                wdriver.findElement(By.linkText("Financials")).click();
                WebElementSearcher.elementsearchFluentWait(wdriver,Add);
                AddPayments("EFT");
                List<WebElement> OpenTransRefs=wdriver.findElements(By.xpath(OpenTrans + "//td[3]"));
                Amount.sendKeys("100");
                System.out.println(OpenTransRefs.size());
                for(int j=0; j<=OpenTransRefs.size();j++)
                {
                    Thread.sleep(5000);
                    String Ref=OpenTransRefs.get(j).getText();
                    System.out.println(Ref);
                    if(Ref.contains(ClaimRef))
                    {
                        List<WebElement> check=wdriver.findElements(By.xpath(OpenTrans + "/td/span/label/input[@type='checkbox']"));
                        WebElement ele=check.get(j);
                        JavascriptExecutor executor = (JavascriptExecutor)wdriver;
                        executor.executeScript("arguments[0].click();",ele);
                        Save.click();
                        Thread.sleep(5000);
                        wdriver.findElement(By.linkText("Claims")).click();
                        PartialPayment(Ref);
                        break;
                    }

                }
                break;

            }

        }



    }
    public void PartialPayment( String Ref) throws InterruptedException {
        Waitforelement();
        for(int j=1; j<=10;j++){
            Thread.sleep(5000);
            String ClaimRefs=wdriver.findElement(By.xpath(brefore_xpath + j + after_CREFXpath)).getText();
            System.out.println(ClaimRefs);
            if (ClaimRefs.contains(Ref))
            {
                Thread.sleep(5000);
                String ClaimStatus=wdriver.findElement(By.xpath(brefore_xpath + j + after_sxpath)).getText();
                Assert.assertEquals(ClaimStatus,"Claim Partially Settled\n" + "0 days");

            }
            break;
        }

    }

    public void AssertAmount() {
        Xls_Reader reader = new Xls_Reader("src/main/java/za/co/ezmed/qa/utils/addpatients.xlsx");
        String sheetName = "POCode";
        int rowCount = reader.getRowCount(sheetName);
        int i=0;
        for ( int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String ExpectedPCode = reader.getCellData(sheetName, "InOrder", rowNum);
            List<WebElement> rows = wdriver.findElements(By.xpath("//tbody[2]/tr/td[4][@class='ng-binding']"));
            int Count = rows.size();
            for ( i=i;i <= Count;) {
                String APCode= rows.get(i).getText();
                Assert.assertEquals(ExpectedPCode, APCode);
                i++;
                break;
            }

        }
    }

    public void AssertAmoun() {
        Xls_Reader reader = new Xls_Reader("C:\\Users\\laxmis\\IdeaProjects\\EZmed\\src\\main\\java\\za\\co\\ezmed\\qa\\utils\\addpatients.xlsx");
        String sheetName = "POCode";
        int rowCount = reader.getRowCount(sheetName);
        int i=0;
        for ( int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String ExpectedPCode = reader.getCellData(sheetName, "InOrder", rowNum);
            List<WebElement> rows = wdriver.findElements(By.xpath("//tbody[2]/tr/td[4][@class='ng-binding']"));
            int Count = rows.size();
            for ( i=i;i <= Count;) {
                String APCode= rows.get(i).getText();
                Assert.assertEquals(ExpectedPCode, APCode);
                i++;
                break;
            }

        }
    }
}



