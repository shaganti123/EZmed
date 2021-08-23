package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;
import za.co.ezmed.qa.utils.Xls_Reader;

import java.io.IOException;
import java.util.List;

public class ActionButtons extends BaseClass {


    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    //String ICDAction = "//button[contains(@ng-click,'selectICD10')]";
   // List<WebElement> ICD = wdriver.findElements(By.xpath(ICDAction));

    @FindBy(xpath = "//tbody/tr[2]/td[3]/a[1]")
    private WebElement AddDiagnosis;

    @FindBy(xpath = "//input[contains(@class,'form-control ng-pristine')]")
    private WebElement EnterCode;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement CodeSearchB;
    @FindBy(xpath = "//input[@id='txtSearchCode']")
    private WebElement PCodeEnter;
    @FindBy(xpath = "//tbody/tr[1]/td[7]")
    private WebElement ICDAction;

    @FindBy(xpath = "//tbody/tr[1]/td[4]/button[1]")
    private WebElement ProAction;
    //Change tr for to select another
    @FindBy(xpath = "//tbody/tr[2]/td[5]/a[1]")
    private WebElement AddProcedure;

    //private By AddProcedure = By.xpath("//tbody/tr[2]/td[5]/a[1]");
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
                Thread.sleep(10000);
                WebElement Proceed = WebElementSearcher.elementsearchWithTimeLimit(wdriver,ProceedBy,5);
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
        JavascriptExecutor js = (JavascriptExecutor) wdriver;
        js.executeScript("arguments[0].scrollIntoView();", AddDiagnosis);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(AddDiagnosis);
        // seleniumAction.clickWebElementObject(xpathOfRadioDesc);

    }





    public boolean  Icd(String IcdRadio) throws InterruptedException {
        Xls_Reader reader = new Xls_Reader("C:\\Users\\laxmis\\IdeaProjects\\EZmed\\src\\main\\java\\za\\co\\ezmed\\qa\\utils\\addpatients.xlsx");
        String sheetName = "ICD";
        int rowCount = reader.getRowCount(sheetName);

        if (IcdRadio.equalsIgnoreCase("Desc"))
        {
            WebElement Desc = WebElementSearcher.elementsearchFluentWait(wdriver,DescRadio);
            Screenshot.takeScreenshot(wdriver);
            Desc.click();

            for (int rowNum = 2; rowNum <= rowCount; rowNum++)
            {

                String IcdCode = reader.getCellData(sheetName, "IcdDesc", rowNum);
                EnterCode.sendKeys(IcdCode);
                seleniumAction.clickWebElementObject(CodeSearchB);
                Thread.sleep(10000);
               seleniumAction.clickWebElementObject(ICDAction);
                Waitforelement();

                break;
            }
           // seleniumAction.scrollDown();
            Waitforelement();
            //seleniumAction.scrollDownuptoEle(AddProcedure);
            //seleniumAction.clickWebElementObject(AddProcedure);




        }
        else if (IcdRadio.equalsIgnoreCase("Code"))
        {
            WebElement element1 = wdriver.findElement(By.xpath("//div[@class='radio-inline fa5'][1]"));
            Waitforelement();
            Screenshot.takeScreenshot(wdriver);
            seleniumAction.clickWebElementObject(element1);
            Waitforelement();
            seleniumAction.clickWebElementObject(EnterCode);

            for (int rowNum = 2; rowNum <= rowCount; rowNum++)
            {
                String IcdCode = reader.getCellData(sheetName, "IcdCode", rowNum);
                Waitforelement();
                EnterCode.sendKeys(IcdCode);
                seleniumAction.clickWebElementObject(CodeSearchB);
                Waitforelement();
                seleniumAction.clickWebElementObject(ICDAction);
                Waitforelement();
                seleniumAction.scrollDown();
                Waitforelement();

                break;
            }
        }
        seleniumAction.scrollDown();
        Waitforelement();
        seleniumAction.clickWebElementObject(AddProcedure);


        return true;
    }

    public boolean Procedure(String ProcedureRadio) throws InterruptedException {
        Thread.sleep(10000);
        ((JavascriptExecutor) wdriver).executeScript("arguments[0].scrollIntoView(true);", AddProcedure);
        seleniumAction.clickWebElementObject(AddProcedure);
        Xls_Reader reader = new Xls_Reader("C:\\Users\\laxmis\\IdeaProjects\\EZmed\\src\\main\\java\\za\\co\\ezmed\\qa\\utils\\addpatients.xlsx");

        String sheetName = "ICD";
        int rowCount = reader.getRowCount(sheetName);

        if (ProcedureRadio.equalsIgnoreCase("Desc"))
        {
            for (int rowNum = 2; rowNum <= rowCount; rowNum++)

            {
                WebElement Action = WebElementSearcher.elementsearchSettlementCondition(wdriver,Act);
                Action.click();
                Waitforelement();
               // WebElement element = wdriver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/procedure[1]/div[1]/div[1]/div[1]/div[1]/div[2]/label[1]"));
                //Waitforelement();
            //    seleniumAction.clickWebElementObject(element);
                Waitforelement();
                seleniumAction.clickWebElementObject(PCodeEnter);
                Waitforelement();
                String ProcedureDesc = reader.getCellData(sheetName, "ProcedureDesc", rowNum);
                Waitforelement();
                PCodeEnter.sendKeys(ProcedureDesc);
                Waitforelement();
                seleniumAction.clickWebElementObject(CodeSearchB);
                Waitforelement();
                seleniumAction.clickWebElementObject(ProAction);
                Waitforelement();
                seleniumAction.scrollDown();

            }



        }

        else if (ProcedureRadio.equalsIgnoreCase("Code")) {
            WebElement element1 = wdriver.findElement(By.xpath("//div[@class='radio-inline fa5'][1]"));
            Waitforelement();
            seleniumAction.clickWebElementObject(element1);
            Waitforelement();
            seleniumAction.clickWebElementObject(EnterCode);


            for (int rowNum = 2; rowNum <= rowCount; rowNum++)
            {
                Waitforelement();
                String IcdCode = reader.getCellData(sheetName, "ProcedureCode", rowNum);
                Waitforelement();
                EnterCode.sendKeys(IcdCode);
                Waitforelement();
                seleniumAction.clickWebElementObject(CodeSearchB);
                Waitforelement();
                seleniumAction.clickWebElementObject(ProAction);
                Waitforelement();
                seleniumAction.scrollDown();

            }
        }
        seleniumAction.scrollDown();
        Waitforelement();
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.scrollUp();
        Waitforelement();
        return true;


    }

    public void documents() throws IOException, InterruptedException {
        Thread.sleep(10000);
        wdriver.findElement(By.xpath("//button[@id='action']")).click();
        List<WebElement> d=wdriver.findElements(By.xpath("//label[@uib-tooltip='Upload document']"));
        System.out.println(d.size());

        for(int i=0;i<d.size()-1; i++)
        {
            Thread.sleep(10000);
            System.out.println(i);
             d.get(i).click();
             Waitforelement();
             Runtime.getRuntime().exec("C:\\Users\\laxmis\\Desktop\\AutoIt\\Fileupload.exe");
             Waitforelement();
            }
        wdriver.findElement(By.xpath("//button[@class='doc-btn btn btn-default pull-right']")).click();
        seleniumAction.clickWebElementObject(Save);
            }
}







