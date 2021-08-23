package za.ca.ezmed.qa;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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
    Institution institution;
    Funder funder;
    Claim claim;
    Appoitment appoitment;
    AuthrizationNumber authrizationNumber;
    Financials financials;
    SummaryPage summaryPage;



    @BeforeTest
    public void browserOpen() throws InterruptedException {

        // System.setProperty("webdriver.chrome.driver","src"+ File.separator + "main" + File.separator + "resources" + File.separator + "Drivers" + File.separator + "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver","C://Users//laxmis//Desktop//chromedriver.exe");
        System.setProperty("webdriver.edge.driver","C://Users//laxmis//Desktop//msedgedriver.exe");

        wdriver = new ChromeDriver();
        //wdriver = new EdgeDriver();
        wdriver.manage().window().maximize();
        wdriver.get("https://ezmed.spesstage.co.za");
      /*  Cookie name = new Cookie("SignInMessage.0000", "lnbRkBJ5GN5FdpwlbpRAknRQJf8VhI22hJ7SPg_p-8LmZ3Uy6tywNUKn44Ryi6nFdQy3SuGhjS-bypphz5QqP7ZtZRaKEtsePx1Ti4GcyAYh6_v-DCebzke77kWRfDPAWtr114ExNobf1TxFnFLgKmGSEO1fqSNp5skUNPQADtqH2wdjml7ED9LVNApBHe4Mf8X0GakIOaZz531PH4MXQaIieKUc8WWQzaXzrGvv_UEWw5vOj1SLY8MghYsKtYsbAs9nTqy8f57BNteI-jFqyUqqRGCvGQe-7OgkkhSWdKYxt6wqT3GmSHwfUz0vqId85EBDWX1TUNgmNHDgJbkv1V0nO_k7GLfvWTPJjEhEjA9Y19M_ks5x4t9arNZsZ5SzRQ42TfXJ86DwgFuiE1Cj-5vnLrrgZFaR2T4LDacPkaa3CHD9wN_XEfXgu1ZZUk2Nagy4uFaSRZAbrxBRIQTVOMyXtfhUxLzAxI4LwiXBEpmeZnXMFHFB8H5pTgD6303Mwi0Wd97Polv-Y4ptapGK7iAo-MAS63E4XozoDbhVNPeIy07-SomyAxkhE7L8vaAeFXAen4DUYQjyRkULbrat7_hKIrWKBB5Ol4Nh5Wy1TVvww6NbCQmqvpWzcPDx-innKSaz6abotLNM18xHYFcY8eOQw7wZMikd-u6lbYUNYpI");
        wdriver.manage().addCookie(name);
        Cookie name1 = new Cookie("idsrv.xsrf", "QXNFwhc4LU1LhHzGYVHnzHH3S17ExL4wgyxcYZx02fymvjt1a4VQipMx3Q_Ln3GqYYPpRZm_P-1IQbf0UCJIybfnjC6bE91RQvac3Ah4oic");
        wdriver.manage().addCookie(name1);
       */
        loginPage = new LoginPage(this.wdriver);
        loginPage.loginPage("laxmis@spesnet.co.za", "Passwordq!");
       // institution=new Institution(this.wdriver);
        //institution.Institute("JP Theron Clinical Psychologist ");
    }

    /*@Test
    public void insti()
    {
        actionButtons = new ActionButtons(this.wdriver);
        actionButtons.Institute("4 Wounds Wound Care Practice");
     }
     */

    @Test(priority = 1)
    public void PatientRegistration() throws InterruptedException, AWTException {
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


    @Test(priority = 2)
    @Parameters({"PID", "PatientFunder","IncidentDate"})
    public void IODFunder(String PID, String PatientFunder, String IncidentDate) throws InterruptedException, AWTException {
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

    @Test(priority = 3)
    @Parameters({"PID","FType","MSDate", "MEDate"})
    public void MedicalFunder(String PID, String FType, String SDate, String EDate) throws InterruptedException {
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
        genaralDetails.ProviderCheckBoxType("TreatingProvider");
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
    @Parameters({"PID"})
    public void Appointment(String PID) throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        appoitment = new Appoitment(this.wdriver);
        appoitment.PatientAppointments();
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Start");
        calendar1.calender("30/04/2021");
        calendar1.StartTime("11","00");
        calendar1.buttons("End");
        calendar1.calender("30/04/2021");
        calendar1.EndTime("11","30");

    }

    @Test(priority = 6)
    @Parameters({"PID"})
    public void Authorisation(String PID) throws InterruptedException {
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
    @Parameters({"PID","IODTDate"})
    public void CreateIODClaim(String PID, String IODTDate) throws InterruptedException, IOException {

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

    @Test(priority = 8)
    @Parameters({"PID", "MATDate"})
    public void CreateMedicalAidClaim(String PID, String MATDate)throws InterruptedException {

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
        calendar1.EndTime1("10","30");
        actionButtons.Treatments();
        actionButtons.Icd("Code");
        actionButtons.Procedure("Code");
        claim.CommitClaim("Submit to Funder");


    }

     @Test(priority = 9)
    @Parameters({"PID"})
    public void Notes(String PID) throws InterruptedException, AWTException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        notes= new Notes(this.wdriver);
        notes.Notes();
        notes.NoteDetails("General Note" , "Public","Laxmi-Test");
        notes.EditingPermission("Laxmi Shaganti");
        genaralDetails= new GenaralDetails(this.wdriver);
        genaralDetails.next("Save");

    }


    @Test(priority = 10)
    public void Reports() throws InterruptedException {
        Reports reports=new Reports(this.wdriver);
        reports.Dashboard();
        reports.AllReports("AuthReport","ThisMonth");
        reports.RType("PDF");
    }

    @Test(priority = 11)
    @Parameters({"PID"})
    public void AddPayment(String PID) throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        financials = new Financials(this.wdriver);
        financials.Financial();
        financials.AddPayments("EFT");
        financials.FullPayment();

    }
    @Test(priority = 12)
    @Parameters({"PID", "CTdate","ClaimStatus", "Action"})
    public void CloneClaim(String PID, String CTdate, String ClaimStatus, String Action) throws InterruptedException, IOException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        claim=new Claim(this.wdriver);
        claim.Claims();
        clone=new Clone(this.wdriver);
        clone.CloneProforma(CTdate,ClaimStatus);
        clone.action(Action);
        funder = new Funder(this.wdriver);
        funder.Funder("Discovery");
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Treatment");
        calendar1.calender( "10/08/2021");
        calendar1.StartTime("10","00");
        //  actionButtons.AfterHour();
        calendar1.EndTime1("10","30");
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.proceed("Clone");
        // actionButtons.documents();
        claim.CommitClaim("Submit to Funder");

    }

    @Test(priority = 13)
    @Parameters({"PID"})
    public void CreatePartialPayment(String PID) throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        claim=new Claim(this.wdriver);
        claim.Claims();
        financials = new Financials(this.wdriver);
        financials.ClaimRef("Fund - Not Payable");

    }
    @Test(priority = 14)
    @Parameters({"PID"})
    public void CreateFullPayment(String PID) throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        financials = new Financials(this.wdriver);
        financials.Financial();
        financials.AddPayments("EFT");
        financials.FullPayment();

    }

    @Test(priority = 14)
    @Parameters({"PID"})
    public void DeletePatient(String PID) throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient(PID);
        summaryPage= new SummaryPage(this.wdriver);
        summaryPage.DeleteP();

    }


    @Test(priority = 15)
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