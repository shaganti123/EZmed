package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.util.List;

public class Reports extends BaseClass {

    SeleniumAction seleniumAction=new SeleniumAction(wdriver);


    String xpathOfReports="//h4";

    @FindBy(xpath = "//h4[contains(text(),'Reports')]")
    WebElement Reports;

    @FindBy(xpath = "//h4[contains(text(),'Age Analysis Report')]")
    WebElement AgeAnalysisReport;
    @FindBy(xpath = "//h4[contains(text(),'Authorisation Report')]")
    WebElement AuthorisationReport;
    @FindBy(xpath = "//h4[contains(text(),'Claims Report')]")
    WebElement ClaimsReport;
    @FindBy(xpath = "//h4[contains(text(),'Communication Report')]")
    WebElement CommunicationReport;
    @FindBy(xpath = "//h4[contains(text(),'EDI Fault Report')]")
    WebElement EDIFaultReport;
    @FindBy(xpath = "//h4[contains(text(),'Opening Balance Recon Report')]")
    WebElement OpeningBalanceReconReport;
    @FindBy(xpath = "//h4[contains(text(),'Patient List Report')]")
    WebElement PatientListReport;
    @FindBy(xpath = "//h4[contains(text(),'Patient Notes Report')]")
    WebElement PatientNotesReport;
    @FindBy(xpath = "//h4[contains(text(),'Payments Received Report')]")
    WebElement PaymentsReceivedReport;
    @FindBy(xpath = "//h4[contains(text(),'Pro Forma Report')]")
    WebElement ProFormaReport;
    @FindBy(xpath = "//h4[contains(text(),'Provider Billing Report')]")
    WebElement ProviderBillingReport;
    @FindBy(xpath = "//h4[contains(text(),'Referring Provider Report')]")
    WebElement ReferringProviderReport;
    @FindBy(xpath = "//h4[contains(text(),'Transaction Detail')]")
    WebElement TransactionDetailReport;
    @FindBy(xpath = "//h4[contains(text(),'Turnover Report')]")
    WebElement TurnoverReport;

    @FindBy(xpath = "//select[@ng-model='ctrl.ReportFilter.FunderTypeId']")
    private WebElement FunderType;

    @FindBy(xpath = "//button[contains(text(),'End Of Last Year')]")
    private WebElement EndOfLastYear;
    @FindBy(xpath = "//button[contains(text(),'End Of Last Month')]")
    private WebElement EndOfLastMonth;
    @FindBy(xpath = "//button[contains(text(),'End Of Last Week')]")
    private WebElement EndOfLastWeek;
    @FindBy(xpath = "//button[contains(text(),'Yesterday')]")
    private WebElement Yesterday;
    @FindBy(xpath = "//button[contains(text(),'Today')]")
    private WebElement Today;

    @FindBy(xpath = "//button[contains(text(),'This Year')]")
    private WebElement ThisYear;
    @FindBy(xpath = "//button[contains(text(),'Last Month')]")
    private WebElement LastMonth;
    @FindBy(xpath = "//button[contains(text(),'This Month')]")
    private WebElement ThisMonth;
    @FindBy(xpath = "//button[contains(text(),'Last Week')]")
    private WebElement LastWeek;
    @FindBy(xpath = "//button[contains(text(),'This Week')]")
    private WebElement ThisWeek;

    @FindBy(xpath = "//strong[contains(text(),'Screen')]")
    private WebElement Screen;
    @FindBy(xpath = "//strong[contains(text(),'PDF')]")
    private WebElement PDF;
    @FindBy(xpath = "//strong[contains(text(),'Excel')]")
    private WebElement Excel;
    @FindBy(xpath = "//a[contains(text(),'Report Dashboard')]")
    private WebElement ReportDashboard;

    public By Home = By.xpath("//img[@id='ezmedLogo']");

    public Reports(WebDriver driver) {
        super(driver);

    }

    public void  Dashboard()
    {
        WebElement home= WebElementSearcher.elementsearchSettlementCondition(wdriver,Home);
        home.click();
        seleniumAction.waitForElementToBeVisible(Reports);
        seleniumAction.clickWebElementObject(Reports);
    }
    public boolean FunderType(String FType){
        seleniumAction.waitForElementToBeVisible(FunderType);
        seleniumAction.dropdownValue(FunderType, FType);
        seleniumAction.scrollDown();
        return true;
    }

    public void RType(String type) throws InterruptedException {
        Waitforelement();
        seleniumAction.scrollDown();
        if(type.equalsIgnoreCase("PDF")){
            PDF.click();
        }
        else if(type.equalsIgnoreCase("Excel"))
        {
            Excel.click();
        }

        ReportDashboard.click();
    }

    public void AllReports(String Report, String QL) throws InterruptedException {
        Waitforelement();
        if (Report.equalsIgnoreCase("AuthReport"))
        {
            seleniumAction.clickWebElementObject(AuthorisationReport);
        }
        else if (Report.equalsIgnoreCase("AgeAnalysisReport"))
        {
            seleniumAction.clickWebElementObject(AgeAnalysisReport);
        }
        else if (Report.equalsIgnoreCase("ClaimsReport"))
        {
            seleniumAction.clickWebElementObject(ClaimsReport);
        }
        else if (Report.equalsIgnoreCase("CommunicationReport"))
        {
            seleniumAction.clickWebElementObject(CommunicationReport);
        }
        else if (Report.equalsIgnoreCase("EDIFaultReport"))
        {
            seleniumAction.clickWebElementObject(EDIFaultReport);
        }
        else if (Report.equalsIgnoreCase("OpeningBalanceReconReport"))
        {
            seleniumAction.clickWebElementObject(OpeningBalanceReconReport);
        }
        else if (Report.equalsIgnoreCase("PatientListReport"))
        {
            seleniumAction.clickWebElementObject(PatientListReport);
        }
        else if (Report.equalsIgnoreCase("PatientNotesReport"))
        {
            seleniumAction.clickWebElementObject(PatientNotesReport);
        }
        else if (Report.equalsIgnoreCase("PaymentsReceivedReport"))
        {
            seleniumAction.clickWebElementObject(PaymentsReceivedReport);
        }
        else if (Report.equalsIgnoreCase("ProFormaReport"))
        {
            seleniumAction.clickWebElementObject(ProFormaReport);
        }
        else if (Report.equalsIgnoreCase("ProviderBillingReport"))
        {
            seleniumAction.clickWebElementObject(ProviderBillingReport);
        }
        else if (Report.equalsIgnoreCase("ReferringProviderReport"))
        {
            seleniumAction.clickWebElementObject(ReferringProviderReport);
        }
        else if (Report.equalsIgnoreCase("TransactionDetailReport"))
        {
            seleniumAction.clickWebElementObject(TransactionDetailReport);
        }
        else if (Report.equalsIgnoreCase("TurnoverReport"))
        {
            seleniumAction.clickWebElementObject(TurnoverReport);
        }

        if(QL.equalsIgnoreCase("ThisYear"))
        {
            seleniumAction.clickWebElementObject(ThisYear);
        }
        else if(QL.equalsIgnoreCase("LastMonth"))
        {
            seleniumAction.clickWebElementObject(LastMonth);
        }
        else if(QL.equalsIgnoreCase("ThisMonth"))
        {
            seleniumAction.clickWebElementObject(ThisMonth);
        }
        else if(QL.equalsIgnoreCase("LastWeek"))
        {
            seleniumAction.waitForElementToBeVisible(LastWeek);

        }
        else if(QL.equalsIgnoreCase("ThisWeek"))
        {
            seleniumAction.waitForElementToBeVisible(ThisWeek);
        }
        else if(QL.equalsIgnoreCase("Yesterday"))
        {
            seleniumAction.waitForElementToBeVisible(Yesterday);
        }
        else if(QL.equalsIgnoreCase("Today"))
        {
            seleniumAction.waitForElementToBeVisible(Today);
        }
        if(QL.equalsIgnoreCase("EYear"))
        {
            Waitforelement();
            EndOfLastYear.click();
        }
        else if(QL.equalsIgnoreCase("EMonth"))
        {
            EndOfLastMonth.click();
        }
        else if(QL.equalsIgnoreCase("EWeek"))
        {
            EndOfLastWeek.click();
        }

    }

    }

