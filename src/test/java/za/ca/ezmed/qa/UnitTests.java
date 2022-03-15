package za.ca.ezmed.qa;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import za.co.ezmed.qa.pagesweb.*;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UnitTests {

    private WebDriver wdriver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    NewPatients newPatients;
    GenaralDetails genaralDetails;
    FunderDetails funderDetails;
    ActionButtons actionButtons;
    Notes notes;
    Calendar1 calendar1;
    Providers providers;
    Clone clone;
    Funder funder;
    Claim claim;
    Appoitment appoitment;
    AuthrizationNumber authrizationNumber;
    Financials financials;
    SummaryPage summaryPage;
    Institution institution;
    BatchProcesses batchProcesses;



    @BeforeTest
    @Parameters({"Environment","UserName","Password" })
    public void browserOpen(String url, String UN, String PWD) throws InterruptedException {

        // System.setProperty("webdriver.chrome.driver","src"+ File.separator + "main" + File.separator + "resources" + File.separator + "Drivers" + File.separator + "chromedriver.exe");
        String DriverPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver",DriverPath +"/src/main/resources/Drivers/chromedriver.exe");
       // System.setProperty("webdriver.edge.driver","C://Users//laxmis//Desktop//msedgedriver.exe");

        wdriver = new ChromeDriver();
        //wdriver = new EdgeDriver();
        wdriver.manage().window().maximize();
        wdriver.get(url);
        loginPage = new LoginPage(this.wdriver);
        loginPage.loginPage(UN,PWD);

    }



    @Test(priority = 1)
    @Parameters({"OT"})
    public void PatientRegistration(String Ins) throws InterruptedException, AWTException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.addpatients();
        genaralDetails= new GenaralDetails(this.wdriver);
        genaralDetails.title1("Mrs");
        genaralDetails.IdentityType("Passport");
        genaralDetails.IdType("Passport");
        genaralDetails.countryautoSuggestion("South Africa");
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("DOB");
        calendar1.calender("15/12/1989");
        genaralDetails.radioButton("MRadio");
        genaralDetails.PreferredContact("Email");
        genaralDetails.next("Next");
        funderDetails = new FunderDetails(this.wdriver);
        funderDetails.FunderType("Private Patient", "check");
        calendar1.buttons("EFFFrom");
        calendar1.calender("02/11/2020");
        calendar1.buttons("EFFTo");
        calendar1.calender("15/12/2022");
        genaralDetails.next("Save");

    }


    @Test(priority = 3)
    @Parameters({"PID", "PatientFunder","IncidentDate","OT"})
    public void IODFunder(String PID, String PatientFunder, String IncidentDate, String Ins) throws InterruptedException, AWTException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        funder=new Funder(this.wdriver);
        funder.PatientFunders();
        funderDetails = new FunderDetails(this.wdriver);
        funderDetails.FunderType("IOD", "check");
        //unhide below if it is nor IOD and change Cicon
        genaralDetails= new GenaralDetails(this.wdriver);
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("IncidentDate");
        calendar1.calender(IncidentDate);
        funder.Funder1(PatientFunder);
        genaralDetails.countryautoSuggestion("South Africa");
        genaralDetails.next("Save");
    }

    @Test(priority = 2)
    @Parameters({"PID","FType","MSDate", "MEDate","OT"})
    public void MedicalFunder(String PID, String FType, String SDate, String EDate,String Ins) throws InterruptedException, AWTException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        funder=new Funder(this.wdriver);
        funder.PatientFunders();
        funderDetails = new FunderDetails(this.wdriver);
        funderDetails.FunderType(FType, "check");
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Start");
        calendar1.calender(SDate);
        calendar1.buttons("End");
        calendar1.calender(EDate);
        genaralDetails= new GenaralDetails(this.wdriver);
        genaralDetails.next("Save");

    }

    @Test(priority = 4)
    public void ProvidersRegistration() throws InterruptedException
    {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.Providers();
        providers= new Providers(this.wdriver);
        providers.addProvider();
        genaralDetails= new GenaralDetails(this.wdriver);
        genaralDetails.ProviderCheckBoxType("ReferringProvider");
        genaralDetails.LinkPlace();
        genaralDetails.title1("Mrs");
        genaralDetails.IdentityType("Passport");
        genaralDetails.IdType("Passport");
        //  genaralDetails.countryautoSuggestion("South Africa");
        // calendar1 = new Calendar1(this.wdriver);
        // calendar1.buttons("DOB");
        //  calendar1.calender("15/12/2019");
        genaralDetails.radioButton("FRadio");
        genaralDetails.PreferredContact("Email");
        genaralDetails.next("Save");

    }

    @Test(priority = 5)
    @Parameters({"PID","OT"})
    public void Appointment(String PID,String Ins) throws InterruptedException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        appoitment = new Appoitment(this.wdriver);
        appoitment.PatientAppointments();
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Start");
        calendar1.calender("01/03/2022");
        calendar1.StartTime("11","00");
        calendar1.buttons("End");
        calendar1.calender("01/03/2022");
        calendar1.EndTime("11","30");

    }

    @Test(priority = 6)
    @Parameters({"PID","OT"})
    public void Authorisation(String PID,String Ins) throws InterruptedException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        authrizationNumber = new AuthrizationNumber(this.wdriver);
        authrizationNumber.AuthRef();
        genaralDetails= new GenaralDetails(this.wdriver);
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Start");
        calendar1.calender("02/11/2020");
        calendar1.buttons("End");
        calendar1.calender("15/12/2022");
        genaralDetails.next("Save");

    }

    @Test(priority =7)
    @Parameters({"PID","IODTDate","OT"})
    public void CreateIODClaim(String PID, String IODTDate, String Ins ) throws InterruptedException, IOException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        // dashboardPage.home();
        claim=new Claim(this.wdriver);
        claim.Claims();
        claim.CreateClaim();
        funder = new Funder(this.wdriver);
        funder.FunderType("IOD");
        funder.Funder("RMA");
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.proceed("Claim");
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Treatment");
        calendar1.calender( IODTDate);
        calendar1.StartTime("10","00");
        calendar1.EndTime1("10","30");
        actionButtons.Treatments();
        actionButtons.Icd("Desc");
        actionButtons.Procedure("Desc");
        actionButtons.documents();
        claim.CommitClaim("Submit to Funder");


    }

    @Test(priority =8)
    @Parameters({"PID", "MATDate","PhysioIns"})
    public void PhysioCreateMedicalAidClaim(String PID, String MATDate, String Ins)throws InterruptedException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        // dashboardPage.home();
        claim=new Claim(this.wdriver);
        claim.Claims();
        claim.CreateClaim();
        funder = new Funder(this.wdriver);
        funder.FunderType("Medical Aid");
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.proceed("Claim");
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Treatment");
        calendar1.calender( MATDate);
        calendar1.StartTime("10","00");
        calendar1.EndTimeEme("10","30");
        actionButtons.Treatments();
        actionButtons.Icd("Code");
        actionButtons.Procedure("Code");
        financials = new Financials(this.wdriver);
        financials.AssertAmount();
        claim.CommitClaim("Commit Only");
    }



    @Test()
    @Parameters({"PID", "MATDate"})
    public void PCreateMedicalAidClaim(String PID, String MATDate)throws InterruptedException {
    //    institution=new Institution(this.wdriver);
      //  institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        // dashboardPage.home();
        claim=new Claim(this.wdriver);
        claim.Claims();
        claim.CreateClaim();
        funder = new Funder(this.wdriver);
        funder.FunderType("Medical Aid");
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.proceed("Claim");
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Treatment");
        calendar1.calender( MATDate);
        calendar1.StartTime("10","00");
        calendar1.EndTimeEme("10","30");
        actionButtons.Treatments();
        actionButtons.Icd("Code");
        actionButtons.Procedure("Code");
       // financials = new Financials(this.wdriver);
       // financials.AssertAmount();
        claim.CommitClaim("Commit Only");
    }

     @Test(priority = 9)
    @Parameters({"PID","OT"})
    public void Notes(String PID, String Ins) throws InterruptedException, AWTException {
         institution=new Institution(this.wdriver);
         institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        notes= new Notes(this.wdriver);
        notes.Notes();
        notes.AddNotes();
        notes.NoteDetails("General Note" , "Public","Notes Version");
        notes.EditingPermission("Laxmi Shaganti");
        genaralDetails= new GenaralDetails(this.wdriver);
        genaralDetails.next("Save");

    }

    @Test(priority = 10)
    @Parameters({"PID","OT"})
    public void NotesVersion(String PID, String Ins) throws InterruptedException, AWTException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        notes= new Notes(this.wdriver);
        notes.Notes();
        notes.Edit();
        notes.EditNotes("Edit");
        genaralDetails= new GenaralDetails(this.wdriver);
        genaralDetails.next("Save");
        notes.Edit();
        notes.EditNotes("Version");


    }

    @Test(priority = 11)
    @Parameters({"PID", "MATDate","PhysioIns"})
    public void Reports(String Ins) throws InterruptedException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        Reports reports=new Reports(this.wdriver);
        reports.Dashboard();
        reports.AllReports("AuthReport","ThisMonth");
        reports.RType("PDF");
    }

    @Test()
    @Parameters({"PID", "OT"})
    public void AddPayment(String PID, String Ins) throws InterruptedException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        financials = new Financials(this.wdriver);
        financials.Financial();
        financials.AddPayments("EFT");
        financials.FullPayment();
      //  financials.MenuItems("Reconcile");
       // financials.reconcile();
       // genaralDetails= new GenaralDetails(this.wdriver);
       // genaralDetails.next("Save");

    }
    @Test(priority = 12)
    @Parameters({"PID", "CTdate","ClaimStatus", "Action","OT"})
    public void CloneClaim(String PID, String CTdate, String ClaimStatus, String Action, String Ins) throws InterruptedException, IOException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        claim=new Claim(this.wdriver);
        claim.Claims();
        clone=new Clone(this.wdriver);
        clone.CloneProforma(CTdate,ClaimStatus);
        clone.action(Action);
       // funder = new Funder(this.wdriver);
        //funder.Funder("Discovery");
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Treatment");
        calendar1.calender( "10/08/2021");
        calendar1.StartTime("10","00");
        //  actionButtons.AfterHour();
        calendar1.EndTime1("10","30");
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.proceed("Clone");
         actionButtons.documents();
        claim.CommitClaim("Submit to Funder");

    }

    @Test(priority = 13)
    @Parameters({"OT"})
    public void BatchProcess(String Ins) throws InterruptedException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        batchProcesses = new BatchProcesses(this.wdriver);
        batchProcesses.BatchP();
        batchProcesses.Statements("Nombe, Velly");
        batchProcesses.ProcessQ();
        batchProcesses.MassageSubject("Statement from","Nombe, Velly");



    }

    @Test()
    @Parameters({"PID","OT"})
    public void CreatePartialPayment(String PID, String Ins) throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        financials = new Financials(this.wdriver);
        financials.Financial();

    }
    @Test(priority = 14)
    @Parameters({"PID","OT"})
    public void CreateFullPayment(String PID, String Ins) throws InterruptedException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        financials = new Financials(this.wdriver);
        financials.Financial();
        financials.AddPayments("EFT");
       // financials.FullPayment();

    }

    @Test(priority = 15)
    @Parameters({"PID", "OT"})
    public void DeletePatient(String PID, String Ins) throws InterruptedException {
        institution=new Institution(this.wdriver);
        institution.Institute(Ins);
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        summaryPage= new SummaryPage(this.wdriver);
        summaryPage.DeleteP();

    }


    @Test(priority = 16)
    public void DeleteProvider() throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.Providers();
        dashboardPage.searchProvider("Velly");
        summaryPage= new SummaryPage(this.wdriver);
        summaryPage.DeleteProvider();

    }

    @Test()
    @Parameters({"PID"})
    public void Conditions(String PID) throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.PatientConditions();
      //  actionButtons.Icd("Code");
       // actionButtons.SecondaryICD("Code");

    }


    //@AfterTest()
    public void logout() throws InterruptedException {
        loginPage = new LoginPage(this.wdriver);
       loginPage.logout();
    }

    @AfterMethod
    public void ftakeScreenshot(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus())
        {
            TakesScreenshot screenshot=((TakesScreenshot)wdriver);
            File srcFile=screenshot.getScreenshotAs(OutputType.FILE);
            String timestamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String filePath="screenshots\\FailedScreenshot"+timestamp+".png";
           // String filePath="screenshots\\Failed.png";
            File destFile=new File(filePath);
            FileUtils.copyFile(srcFile,destFile);
        }
    }







}