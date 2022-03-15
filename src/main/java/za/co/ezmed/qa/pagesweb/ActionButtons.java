package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;
import za.co.ezmed.qa.utils.Xls_Reader;

import java.io.IOException;
import java.util.List;

public class ActionButtons extends BaseClass {


    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    @FindBy(xpath = "//tbody/tr[2]/td[3]/a[1]")
    private WebElement AddDiagnosis;

    @FindBy(xpath = "//input[contains(@class,'form-control ng-pristine')]")
    private WebElement EnterCode;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement CodeSearchB;
    @FindBy(xpath = "//input[@id='txtSearchCode']")
    private WebElement PCodeEnter;
    private By AddDiagnosisBy = By.xpath("//tbody/tr[2]/td[3]/a[1]");
    private By ICDActionBy = By.xpath("//tbody/tr[1]/td[7]/button");
    private By ICDCode = By.xpath("//div[@class='radio-inline fa5'][1]");

    //Change tr for to select another
    @FindBy(xpath = "//tbody/tr[2]/td[5]/a[1]")
    private WebElement AddPro;

    private By AddProcedure = By.xpath("//tbody/tr[2]/td[5]/a[1]");

    private By ProAction = By.xpath("//tbody/tr[1]/td[4]/button[1]");
    private By ProceedBy = By.xpath("//button[(@class='btn btn-success')][not(@disabled)]");
    private By Add = By.xpath("//button[contains(@class,'pull-right btn-margin-bottom-10')]");
    private By Act = By.xpath("//body/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/procedure[1]/div[1]/div[1]/div[1]/div[1]/div[2]/label[1]");
    private By DescRadio = By.xpath("//div[@class='radio-inline fa5'][2]");


    @FindBy(xpath = "//button[@title='Save']")
    WebElement Save;


    @FindBy(xpath = "//input[contains(@class,'form-control ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required ng-valid-maxlength')]")
    private WebElement ConditionDescription;

    @FindBy(xpath = "//input[contains(@class,'form-control  ng-pristine')]")
    private WebElement ICD10;

    @FindBy(xpath = "//textarea[contains(@class,'form-control valid ng-pristine')]")
    private WebElement CondtionComments;

    @FindBy(xpath = "//input[contains(@placeholder,'Click to select Secondary ICD-10s')]")
    private WebElement SceondaryICD;

    SeleniumAction seleniumAction=new SeleniumAction(wdriver);
    public ActionButtons(WebDriver driver) {
        super(driver);

    }

    public void PatientConditions() {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(4).click();
        WebElement AddNew = WebElementSearcher.elementsearchSettlementCondition(wdriver,Add);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(AddNew);
        ConditionDescription.sendKeys("TestDescription");
        seleniumAction.clickWebElementObject(ICD10);
        CondtionComments.sendKeys("Testing");

    }

        public void proceed(String p) throws InterruptedException {

            if(p.equalsIgnoreCase("Claim"))
            {
                JSWaiter.setDriver(this.wdriver);
                JSWaiter.waitUntilAngularReady();
                WebElement Proceed = WebElementSearcher.elementsearchWithTimeLimit(wdriver,ProceedBy,6);
                Proceed.click();
            }
             else if (p.equalsIgnoreCase("Clone")){
                WebElement Proceed = WebElementSearcher.elementsearchSettlementCondition(wdriver,ProceedBy);
                JavascriptExecutor js = (JavascriptExecutor) wdriver;
                js.executeScript("arguments[0].scrollIntoView();", Proceed);
                Screenshot.takeScreenshot(wdriver);
                seleniumAction.clickWebElementObject(Proceed);
            }

        }


    public void Treatments() throws InterruptedException {
        WebElement Add = WebElementSearcher.elementsearchSettlementCondition(wdriver,AddDiagnosisBy);
        JavascriptExecutor js = (JavascriptExecutor) wdriver;
        js.executeScript("arguments[0].scrollIntoView();", AddDiagnosis);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(Add);
        // seleniumAction.clickWebElementObject(xpathOfRadioDesc);

    }





    public boolean  Icd(String IcdRadio) throws InterruptedException {
        Xls_Reader reader = new Xls_Reader("src/main/java/za/co/ezmed/qa/utils/addpatients.xlsx");


        if (IcdRadio.equalsIgnoreCase("Desc"))
        {
            String sheetName = "Desc";
            int rowCount = reader.getRowCount(sheetName);
            WebElement Desc = WebElementSearcher.elementsearchFluentWait(wdriver,DescRadio);
            Screenshot.takeScreenshot(wdriver);
            Desc.click();

            for (int rowNum = 2; rowNum <= rowCount; rowNum++)
            {
                System.out.println(rowCount);
                String IcdCode = reader.getCellData(sheetName, "IcdDesc", rowNum);
                EnterCode.sendKeys(IcdCode);
                seleniumAction.clickWebElementObject(CodeSearchB);
                JSWaiter.setDriver(this.wdriver);
                JSWaiter.waitUntilAngularReady();
                WebElement ICDAction = WebElementSearcher.elementsearchSettlementConditionWithTimeLimit(wdriver,ICDActionBy,10 );
                ICDAction.click();
                break;
            }
           // seleniumAction.scrollDown();
            Waitforelement();
            //seleniumAction.scrollDownuptoEle(AddProcedure);
            //seleniumAction.clickWebElementObject(AddProcedure);




        }
        else if (IcdRadio.equalsIgnoreCase("Code"))
        {
            String sheetName = "Code";
            int rowCount = reader.getRowCount(sheetName);
            WebElement ICDC = WebElementSearcher.elementsearchSettlementCondition(wdriver,ICDCode );
            Screenshot.takeScreenshot(wdriver);
            ICDC.click();
            Waitforelement();
            seleniumAction.clickWebElementObject(EnterCode);

            for (int rowNum = 2; rowNum <= rowCount; rowNum++)
            {
                String IcdCode = reader.getCellData(sheetName, "IcdCode", rowNum);
                Waitforelement();
                EnterCode.sendKeys(IcdCode);
                seleniumAction.clickWebElementObject(CodeSearchB);
                WebElement ICDAction = WebElementSearcher.elementsearchSettlementConditionWithTimeLimit(wdriver,ICDActionBy,15 );
                ICDAction.click();
                Waitforelement();
                seleniumAction.scrollDown();
                Waitforelement();

                break;
            }
        }
        seleniumAction.scrollDown();

        return true;
    }

    public boolean  Procedure(String ProcedureRadio) throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitUntilAngularReady();
        Xls_Reader reader = new Xls_Reader("src/main/java/za/co/ezmed/qa/utils/addpatients.xlsx");

        if (ProcedureRadio.equalsIgnoreCase("Desc"))
        {
            String sheetName = "Desc";
            int rowCount = reader.getRowCount(sheetName);
            for (int rowNum = 2; rowNum <= rowCount; rowNum++)
            {
                JSWaiter.setDriver(this.wdriver);
                JSWaiter.waitUntilAngularReady();
                WebElement AddP = WebElementSearcher.elementsearchFluentWait(wdriver,AddProcedure);

                try{
                    AddP.click();
                }catch (Exception e){
                    ((JavascriptExecutor) wdriver).executeScript("arguments[0].scrollIntoView(true);", AddP);
                  //  ((JavascriptExecutor) wdriver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", AddP);
                    AddP.click();
                }
                WebElement Action = WebElementSearcher.elementsearchFluentWait(wdriver,Act);
                Action.click();
                seleniumAction.clickWebElementObject(PCodeEnter);
                String ProcedureDesc = reader.getCellData(sheetName, "ProcedureDesc", rowNum);
                PCodeEnter.sendKeys(ProcedureDesc);
                seleniumAction.clickWebElementObject(CodeSearchB);
                WebElement PAction = WebElementSearcher.elementsearchFluentWait(wdriver,ProAction);
                PAction.click();
                seleniumAction.scrollDown();

            }



        }

        else if (ProcedureRadio.equalsIgnoreCase("Code")) {
            JSWaiter.setDriver(this.wdriver);
            JSWaiter.waitUntilAngularReady();
            String sheetName = "Code";
            int rowCount = reader.getRowCount(sheetName);
            for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
                JSWaiter.waitUntilAngularReady();
                WebElement AddP = WebElementSearcher.elementsearchFluentWait(wdriver, AddProcedure);
                try{
                    AddP.click();
                }catch (Exception e){
                    ((JavascriptExecutor) wdriver).executeScript("arguments[0].scrollIntoView(true);", AddP);
                    AddP.click();
                }
                    WebElement ICDC = WebElementSearcher.elementsearchFluentWait(wdriver, ICDCode);
                    Screenshot.takeScreenshot(wdriver);
                    ICDC.click();
                    seleniumAction.clickWebElementObject(EnterCode);
                    String IcdCode = reader.getCellData(sheetName, "ProcedureCode", rowNum);
                    EnterCode.sendKeys(IcdCode);
                    seleniumAction.clickWebElementObject(CodeSearchB);
                    WebElement PAction = WebElementSearcher.elementsearchFluentWait(wdriver, ProAction);
                    PAction.click();


            }

        }
        seleniumAction.scrollDown();
        Waitforelement();
        Screenshot.takeScreenshot(wdriver);
        JSWaiter.waitUntilAngularReady();
        return true;


    }

    public void documents() throws IOException, InterruptedException {
        JavascriptExecutor jsExecuter = (JavascriptExecutor)wdriver;
        jsExecuter.executeScript("window.scrollTo(0,document.body.scrollTop)");
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitUntilAngularReady();
        wdriver.findElement(By.xpath("//button[@id='action']")).click();
        List<WebElement> d=wdriver.findElements(By.xpath("//label[@uib-tooltip='Upload document']"));
        System.out.println(d.size());

        for(int i=0;i<d.size()-1; i++)
        {
           Thread.sleep(10000);
            System.out.println(i);
             d.get(i).click();
             Waitforelement();
             String DriverPath = System.getProperty("user.dir");
             Runtime.getRuntime().exec("src/main/java/za/co/ezmed/qa/utils/FileUploadScript.exe"+" "+DriverPath+"/src/main/java/za/co/ezmed/qa/utils/Auth_1638866416");
             Waitforelement();
            }
        wdriver.findElement(By.xpath("//button[@class='doc-btn btn btn-default pull-right']")).click();
        seleniumAction.clickWebElementObject(Save);
            }


}







