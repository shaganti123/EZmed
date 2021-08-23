package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.security.PublicKey;
import java.util.List;

public class Financials extends BaseClass {
    SeleniumAction seleniumAction;
    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    String XpathofPayment = "//td[@class='currency ng-binding'][3]";
    String XpathOfOpen = "//tr[@ng-repeat-start='t in ctrl.trxVisible']//td[@class='text-right hidden-xs ng-binding'][1]";
    String XpathOfCheck = "//i[@class='fal']";
    public By MethodOfPayment = By.xpath("//select[@name='BankAccount']");
    public By Add = By.xpath("//span[contains(text(),'Add Payment')]");
    @FindBy(xpath = "//span[contains(text(),'Add Payment')]")
    private WebElement AddPayment;
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

        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        String text= b.get(6).getText();
        System.out.println(text);
        if(text.contains("E.O.C")){
            b.get(10).click();
        }
        else if (!text.contains("E.O.C"))
        {
            b.get(9).click();
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

        public void AddPayments(String MOP) throws InterruptedException {
           Thread.sleep(5000);
            List<WebElement> Open = wdriver.findElements(By.xpath(XpathOfOpen));
            System.out.println(Open.size());
            for (int i = 1; i <= Open.size(); i++) {
                WebElement Addpayment = WebElementSearcher.elementsearchFluentWait(wdriver, Add);
                seleniumAction.clickWebElementObject(Addpayment);
                WebElement MeOP = WebElementSearcher.elementsearchFluentWait(wdriver, MethodOfPayment);
                seleniumAction.dropdownValue(MeOP, MOP);
                seleniumAction.clearText(Amount);
            }
        }
        public void FullPayment() throws InterruptedException {
            Waitforelement();
            List<WebElement> amount = wdriver.findElements(By.xpath(XpathofPayment));

            String a = amount.get(0).getText();
            seleniumAction.typeText(Amount, a);
            seleniumAction.clickWebElementObject(FunderCheck);
            seleniumAction.clickWebElementObject(OpenCheck);
            seleniumAction.clickWebElementObject(Save);
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


    }

