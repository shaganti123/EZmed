package za.co.ezmed.qa.pagesweb.Patientsdetails;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;
import za.co.ezmed.qa.utils.Xls_Reader;

import java.util.List;

public class ActionButtons extends BaseClass {


    Xls_Reader reader = new Xls_Reader("C:\\Users\\laxmis\\IdeaProjects\\EZmed\\src\\main\\java\\za\\co\\ezmed\\qa\\utils\\addpatients.xlsx");


    SeleniumAction seleniumAction;
    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";


    @FindBy(xpath = "//button[@class='btn pull-right btn-success btn-margin-bottom-10']")
    private WebElement CreateNewClaim;

    @FindBy(xpath = "//select[contains(@ng-model,'config.ReferingProviderGuid')]")
    private WebElement ReferringP;

    @FindBy(xpath = "//button[@class='btn btn-default'][2]")
    private WebElement Actions;

    @FindBy(xpath = "//td[@class='uib-increment hours']")
    private WebElement hours;
    @FindBy(xpath = "//td[@class='uib-increment minutes']")
    private WebElement Min;
    @FindBy(xpath = "//button[contains(text(),'Done')]")
    private WebElement DoneButton;
    @FindBy(xpath = "//input[@name='EndTime']")
    private WebElement EndTimeSelection;
    @FindBy(xpath = "//button[contains(text(),'Create')]")
    private WebElement CreateButton;

    @FindBy(xpath = "//tbody/tr[2]/td[3]/a[1]")
    private WebElement AddDiagnosis;

    @FindBy(xpath = "//input[contains(@class,'form-control ng-pristine')]")
    private WebElement EnterCode;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement CodeSearchB;
    @FindBy(xpath = "//input[@id='txtSearchCode']")
    private WebElement PCodeEnter;
    @FindBy(xpath = "//tbody/tr[1]/td[7]/button[1]")
    private WebElement ICDAction;

    @FindBy(xpath = "//tbody/tr[1]/td[4]/button[1]")
    private WebElement ProAction;
    //Change tr for to select another
    @FindBy(xpath = "//tbody/tr[2]/td[5]/a[1]")
    private WebElement AddProcedure;

    @FindBy(xpath = "//button[@class='btn btn-success']")
    private WebElement Commit;

    @FindBy(xpath = "//button[@class='btn btn-success  ng-binding']")
    private WebElement CommitOnly;

    @FindBy(xpath = "//button[@class='btn btn-info ']")
    private WebElement SubmitToFunder;

    //@FindBy(xpath = "//button[contains(@class,'pull-right btn-margin-bottom-10')]")
    //private WebElement AddNew;

    private By ProceedBy = By.xpath("//button[contains(text(),'Proceed')]");
    private By Add = By.xpath("//button[contains(@class,'pull-right btn-margin-bottom-10')]");
    private By AddNote=By.xpath("//button[contains(@ng-show,'ctrl.showNote')]");

    @FindBy(xpath = "//input[@placeholder='Heading']")
    WebElement Heading;

    @FindBy(xpath = "//select[contains(@ng-model,'ctrl.patientNote.DocumentTypeGuid')]")
    WebElement NoteType;

    @FindBy(xpath = "//select[contains(@ng-model,'ctrl.patientNote.NoteVisibilityType')]")
    WebElement NoteVisibility;

    @FindBy(xpath = "//input[@type='search']")
    WebElement EP;

    @FindBy(xpath = "//button[@title='Save']")
    WebElement Save;

    @FindBy(xpath = "//input[@placeholder='Number']")
    private WebElement Auth;


    @FindBy(xpath = "//textarea[@placeholder='Add your Comments']")
    private WebElement Comments;

    @FindBy(xpath = "//input[contains(@class,'form-control ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required ng-valid-maxlength')]")
    private WebElement ConditionDescription;

    @FindBy(xpath = "//input[contains(@class,'form-control  ng-pristine')]")
    private WebElement ICD10;

    @FindBy(xpath = "//textarea[contains(@class,'form-control valid ng-pristine')]")
    private WebElement CondtionComments;

    @FindBy(xpath = "//input[contains(@placeholder,'Click to select Secondary ICD-10s')]")
    private WebElement SceondaryICD;

    @FindBy(xpath = "//select[@ng-change='loadPatientFunders()']")
    private WebElement FunderType;









    public ActionButtons(WebDriver driver) {
        super(driver);
        seleniumAction = new SeleniumAction(wdriver);
        seleniumAction.clickWebElementObject(Actions);


    }

    public void Summary() {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(0).click();
    }


    public void HealthSummary() {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(1).click();
    }

    public void PatientFunders() {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(2).click();

        WebElement AddNew = WebElementSearcher.elementsearchSettlementCondition(wdriver,Add);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(AddNew);

    }

    public void PatientAppointments() {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(3).click();
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

    public void PatientDocuments() {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(5).click();
    }

    public void EoC() {

        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(6).click();
    }

    public void AuthRef() {

        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(7).click();

        WebElement AddNew = WebElementSearcher.elementsearchSettlementCondition(wdriver,Add);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(AddNew);

        String sheetName = "PrivatePatient";
        int rowCount = reader.getRowCount(sheetName);
        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String AuthNum = reader.getCellData(sheetName, "AccountHolder", rowNum);
            String Comment=reader.getCellData(sheetName, "Insurer", rowNum);
            Auth.sendKeys(AuthNum);
            Comments.sendKeys(Comment);
            break;
        }


    }

    public void Claims() throws InterruptedException {
        Waitforelement();
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        Waitforelement();
        Screenshot.takeScreenshot(wdriver);
        b.get(6).click();
        Waitforelement();
        seleniumAction.waitForElementToBeVisible(CreateNewClaim);
      //  Screenshot.takeScreenshot(wdriver);
      //  seleniumAction.waitForElementToBeClickable(CreateNewClaim);
        seleniumAction.clickWebElementObject(CreateNewClaim);
        Waitforelement();
         while (true)
       {
           Select RP = new Select(ReferringP);
           Screenshot.takeScreenshot(wdriver);
           RP.selectByIndex(5);
           break;
        }



    }

    public boolean FunderType(String FType)
    {
        seleniumAction.dropdownValue(FunderType, FType);
        WebElement Proceed = WebElementSearcher.elementsearchSettlementCondition(wdriver,ProceedBy);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(Proceed);
        return true;
    }

    public void Notes (String NotesHeading)
    {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(9).click();
        WebElement Add= WebElementSearcher.elementsearchSettlementCondition(wdriver,AddNote);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(Add);
        seleniumAction.typeText(Heading,NotesHeading);

    }


    public boolean NoteDetails(String NType, String NVisibility, String EPermission)
    {
        //seleniumAction.clickWebElementObject(NoteType);
        seleniumAction.dropdownValue(NoteType, NType);
        seleniumAction.dropdownValue(NoteVisibility,NVisibility);
        seleniumAction.typeText(EP,EPermission);
        seleniumAction.clickWebElementObject(Save);

        return true;
    }



    public void Financials() {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        b.get(10).click();
    }


    public void StartTime(int Hr, int min) throws InterruptedException {
        int i, j;
        for (i = 1; i <= Hr; i++) {
            hours.click();
        }
        for (j = 1; j <= min; j++) {
            Min.click();
        }
        seleniumAction.clickWebElementObject(DoneButton);
        seleniumAction.clickWebElementObject(EndTimeSelection);
    }

    public void EndTime(int Hr, int min) {
        int i, j;
        for (i = 1; i <= Hr; i++) {
            hours.click();
        }
        for (j = 1; j <= min; j++) {
            Min.click();
        }
        seleniumAction.clickWebElementObject(DoneButton);
        seleniumAction.clickWebElementObject(CreateButton);
    }

    public void Treatments() throws InterruptedException {
        seleniumAction.scrollDown();
        Waitforelement();
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(AddDiagnosis);
        // seleniumAction.clickWebElementObject(xpathOfRadioDesc);
        Waitforelement();


    }

    public void CommitClaim(String commit)
    {
        if (commit.equalsIgnoreCase("Commit Only"))
        {
            seleniumAction.clickWebElementObject(CommitOnly);
        }
        else if (commit.equalsIgnoreCase("Submit to Funder"))
        {
            seleniumAction.clickWebElementObject(SubmitToFunder);
        }

    }


    public boolean Icd(String IcdRadio) throws InterruptedException {
        String sheetName = "ICD";
        int rowCount = reader.getRowCount(sheetName);

        if (IcdRadio.equalsIgnoreCase("Desc"))
        {
            WebElement element = wdriver.findElement(By.xpath("//div[@class='radio-inline fa5'][2]"));
            Waitforelement();
            Screenshot.takeScreenshot(wdriver);
            seleniumAction.clickWebElementObject(element);

            seleniumAction.scrollDown();
            Waitforelement();
            seleniumAction.clickWebElementObject(AddProcedure);

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


        return true;
    }


    public boolean SecondaryICD(String IcdRadio) throws InterruptedException {

        String sheetName = "SecondaryICD";
        int rowCount = reader.getRowCount(sheetName);

          if (IcdRadio.equalsIgnoreCase("Code"))
        {
            for (int rowNum = 2; rowNum <= rowCount; rowNum++)
            {

                seleniumAction.clickWebElementObject(SceondaryICD);
                WebElement element1 = wdriver.findElement(By.xpath("//div[@class='radio-inline fa5'][1]"));
                Waitforelement();
                Screenshot.takeScreenshot(wdriver);
                seleniumAction.clickWebElementObject(element1);
                Waitforelement();
                seleniumAction.clickWebElementObject(EnterCode);

                String IcdCode = reader.getCellData(sheetName, "IcdCode", rowNum);
                Waitforelement();
                EnterCode.sendKeys(IcdCode);
                seleniumAction.clickWebElementObject(CodeSearchB);
                Waitforelement();
                seleniumAction.clickWebElementObject(ICDAction);
                Waitforelement();

            }

        }



        else if (IcdRadio.equalsIgnoreCase("Desc"))
        {

            WebElement element = wdriver.findElement(By.xpath("//div[@class='radio-inline fa5'][2]"));
            Waitforelement();
            Screenshot.takeScreenshot(wdriver);
            seleniumAction.clickWebElementObject(element);

            seleniumAction.scrollDown();
            Waitforelement();
            seleniumAction.clickWebElementObject(AddProcedure);

        }





        return true;
    }



    public boolean Procedure(String ProcedureRadio) throws InterruptedException {


        String sheetName = "ICD";
        int rowCount = reader.getRowCount(sheetName);

        if (ProcedureRadio.equalsIgnoreCase("Desc"))
        {
            for (int rowNum = 2; rowNum <= rowCount; rowNum++)

            {
                Screenshot.takeScreenshot(wdriver);
                seleniumAction.clickWebElementObject(AddProcedure);
                Waitforelement();

                WebElement element = wdriver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/procedure[1]/div[1]/div[1]/div[1]/div[1]/div[2]/label[1]"));
                Waitforelement();
                seleniumAction.clickWebElementObject(element);
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
                String IcdCode = reader.getCellData(sheetName, "IcdCode", rowNum);
                Waitforelement();
                EnterCode.sendKeys(IcdCode);
                Waitforelement();
                seleniumAction.clickWebElementObject(CodeSearchB);
                Waitforelement();
                seleniumAction.clickWebElementObject(ProAction);

            }
        }
        seleniumAction.scrollDown();
        Waitforelement();
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.scrollUp();
        Waitforelement();
        seleniumAction.clickWebElementObject(Commit);
        return true;


    }



}







