package za.ca.ezmed.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.co.ezmed.qa.pagesweb.DashboardPage;
import za.co.ezmed.qa.pagesweb.LoginPage;
import za.co.ezmed.qa.pagesweb.NewPatients;
import za.co.ezmed.qa.pagesweb.Patientsdetails.*;
import za.co.ezmed.qa.utils.Screenshot;


import java.io.File;


public class UnitTests {

    private WebDriver wdriver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    NewPatients newPatients;
    GenaralDetails genaralDetails;
    FunderDetails funderDetails;
    ActionButtons actionButtons;
    Calendar1 calendar1;
    Providers providers;

    @BeforeMethod
    public void browserOpen() throws InterruptedException {

        // System.setProperty("webdriver.chrome.driver","src"+ File.separator + "main" + File.separator + "resources" + File.separator + "Drivers" + File.separator + "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver","C://Users//laxmis//Desktop//chromedriver.exe");

        wdriver = new ChromeDriver();
        wdriver.manage().window().maximize();
        wdriver.manage().deleteAllCookies();
        wdriver.get("https://ezmed.spesstage.co.za/");
        loginPage = new LoginPage(this.wdriver);
        loginPage.loginPage("laxmis@spesnet.co.za", "Nani@11october");
        actionButtons = new ActionButtons(this.wdriver);
        actionButtons.Institute("4 Wounds Wound Care Practice");
    }

    /*@Test
    public void insti()
    {
        actionButtons = new ActionButtons(this.wdriver);
        actionButtons.Institute("4 Wounds Wound Care Practice");
     }
     */
    @Test(priority = 1)
    public void PatientRegistration() throws InterruptedException
    {
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
        Screenshot.takeScreenshot(wdriver);
        genaralDetails.next("Next");
        funderDetails = new FunderDetails(this.wdriver);
        funderDetails.FunderType("Insurance", "uncheck");
        calendar1.buttons("EFFFrom");
        calendar1.calender("02/11/2020");
        calendar1.buttons("EFFTo");
        calendar1.calender("15/12/2022");
        // genaralDetails.next("Save");

    }

    @Test()
    public void Notes()
    {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient("8504235846089");
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.Notes("Testing");
        actionButtons.NoteDetails("General Note" , "Public", "Laxmi Shaganti");
    }


    @Test(priority = 2)
    public void CreateClaim() throws InterruptedException {

        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient("4807135068082");
        //dashboardPage.home();
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.Claims();
        actionButtons.FunderType("Medical Aid");
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Treatment");
        calendar1.calender( "01/01/2021");
        actionButtons.StartTime(5,5);
        actionButtons.EndTime(5,5);
        actionButtons.Treatments();
        actionButtons.Icd("Code");
        actionButtons.Procedure("Desc");
        // actionButtons.CommitClaim("Commit Only");


    }


    @Test(priority = 3)
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
        genaralDetails.IdentityType("South African ID");
        genaralDetails.IdType("South African ID");
        //  genaralDetails.countryautoSuggestion("South Africa");
        // calendar1 = new Calendar1(this.wdriver);
        // calendar1.buttons("DOB");
        //  calendar1.calender("15/12/2019");
        genaralDetails.radioButton("FRadio");
        genaralDetails.PreferredContact("Email");
        // genaralDetails.next("Save");


    }

    @Test(priority = 4)

    public void Funders() throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient("7110290295088");
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.PatientFunders();
        funderDetails = new FunderDetails(this.wdriver);
        funderDetails.FunderType("Insurance", "check");
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Start");
        calendar1.calender("02/11/2020");
        calendar1.buttons("End");
        calendar1.calender("15/12/2022");
    }


    @Test()
    public void Authorisation() throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient("7110290295088");
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.AuthRef();
        genaralDetails= new GenaralDetails(this.wdriver);
        calendar1 = new Calendar1(this.wdriver);
        calendar1.buttons("Start");
        calendar1.calender("02/11/2020");
        calendar1.buttons("End");
        calendar1.calender("15/12/2022");
        //genaralDetails.next("Save");


    }

    @Test()
    public void Conditions() throws InterruptedException {
        dashboardPage= new DashboardPage(this.wdriver);
        dashboardPage.patients();
        newPatients = new NewPatients(this.wdriver);
        newPatients.searchPatient("7110290295088");
        actionButtons= new ActionButtons(this.wdriver);
        actionButtons.PatientConditions();
        actionButtons.Icd("Code");
        actionButtons.SecondaryICD("Code");

    }


    @AfterMethod()
    public void logout() throws InterruptedException {
        loginPage = new LoginPage(this.wdriver);
        loginPage.logout();
    }





}