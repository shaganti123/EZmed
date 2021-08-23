package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.Xls_Reader;

import java.util.List;

public class FunderDetail extends BaseClass {
    public FunderDetail(WebDriver driver) {
        super(driver);
    }
    SeleniumAction seleniumAction=new SeleniumAction(wdriver);
    @FindBy(xpath = "//*[contains(@name,'MedicalAidFund')]")
    private WebElement MedicalAidplan;

    @FindBy(xpath = "//*[contains(@name,'MedicalAidNumber')]")
    private WebElement PolicyNumber;
    @FindBy(xpath = "//select[@name='PatientType']")
    private WebElement FunderType;

    @FindBy(xpath = "//span[@class='checkbox-inline fa5']")
    private WebElement MainCheckbox;

    @FindBy(xpath = "//input[@name='MedicalAidPlan']")
    private WebElement MedicalAidInsurance;

    @FindBy(xpath = "//input[@name='MedicalAidNumber']")
    private WebElement MedicalAidNumber;

    @FindBy(xpath = "//input[@name='DependantCode']")
    private WebElement PatientDepNo;

    @FindBy(xpath = "//button[contains(text(),'Select Payor')]")
    private WebElement SelectPayor;

    @FindBy(xpath = "//button[contains(text(),'Select Main Member')]")
    private WebElement MainMember;

    @FindBy(xpath = "//button[@class='btn btn-warning pull-right']")
    private WebElement SelectPolicy;

    @FindBy(xpath = "//input[@name='InsurerName']")
    private WebElement InsurerName;

    @FindBy(xpath = "//input[@class='form-control ng-touched ng-not-empty ng-dirty ng-valid-editable ng-valid ng-valid-required ng-valid-maxlength']")
    private WebElement Insurer;

    @FindBy(xpath = "//input[@id='txtSearchPatient']")
    private WebElement AccountH;

    @FindBy(xpath = "//tbody/tr[1]/td[8]/button[1]/span[1]")
    private WebElement Select;

    @FindBy(xpath = "//input[@name='AccountNumber']")
    private WebElement AccountNumber;

    @FindBy(xpath = "//button[contains(@class,'btn-success')]//i[@class='fa fa-save fa-1x']")
    private WebElement Save;

    @FindBy(xpath = "//input[@placeholder='Enter the Insurer name...']")
    private WebElement InsrerClick;

    @FindBy(xpath = "//input[@name='CompanyName']")
    private WebElement CompanyName;
    @FindBy(xpath = "//input[@name='CompanyRegistrationNumber']")
    private WebElement CompanyRegistrationNumber;
    @FindBy(xpath = "//input[@name='Email']")
    private WebElement Email;
    @FindBy(xpath = "//input[@name='CompanyPolicyNumber' and @required='required']")
    private WebElement FunderPolicyNumber;


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

    public boolean FunderType(String FType, String Check) throws InterruptedException {

        seleniumAction.clickWebElementObject(FunderType);
        seleniumAction.dropdownValue(FunderType, FType);

        if (FType.equalsIgnoreCase("Private Patient")) {
            Xls_Reader reader = new Xls_Reader("C:\\Users\\laxmis\\IdeaProjects\\EZmed\\src\\main\\java\\za\\co\\ezmed\\qa\\utils\\addpatients.xlsx");

            String sheetName = "PrivatePatient";
            Waitforelement();

            if (Check.equalsIgnoreCase("uncheck")) {
                Waitforelement();
                seleniumAction.clickWebElementObject(MainCheckbox);
                Waitforelement();
                seleniumAction.clickWebElementObject(SelectPayor);

                int rowCount = reader.getRowCount(sheetName);
                for (int rowNum = 2; rowNum <= rowCount; rowNum++)
                {
                    String AccountHolder = reader.getCellData(sheetName, "AccountHolder", rowNum);
                    reader.getCellData(sheetName, "Insurer", rowNum);

                    Waitforelement();
                    AccountH.sendKeys(AccountHolder);
                    Waitforelement();

                    seleniumAction.clickWebElementObject(Select);
                    Waitforelement();
                    //InsurerName.sendKeys(Insurer);

                    // seleniumAction.scrollDown();
                    //      seleniumAction.clickWebElementObject(Save);
                    break;
                }


            }
            else if (Check.equalsIgnoreCase("check"))
                Waitforelement();
            {
                int rowCount = reader.getRowCount(sheetName);
                for (int rowNum = 2; rowNum <= rowCount; rowNum++)
                {
                    String Account = reader.getCellData(sheetName, "AccountHolder", rowNum);
                    String Insurer = reader.getCellData(sheetName, "Insurer", rowNum);
                    Waitforelement();
                    InsurerName.sendKeys(Insurer);
                    Waitforelement();
                    AccountNumber.sendKeys(Account);
                    break;
                }

            }
        }

        else if (FType.equalsIgnoreCase("Medical Aid")) {
            Xls_Reader reader = new Xls_Reader("C:\\Users\\laxmis\\IdeaProjects\\EZmed\\src\\main\\java\\za\\co\\ezmed\\qa\\utils\\addpatients.xlsx");

            Waitforelement();
            String sheetName = "PrivatePatient";
            if (Check.equalsIgnoreCase("uncheck")) {
                Waitforelement();
                seleniumAction.clickWebElementObject(MainCheckbox);
                Waitforelement();
                seleniumAction.clickWebElementObject(MainMember);
                int rowCount = reader.getRowCount(sheetName);
                for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
                    String AccountHolder = reader.getCellData(sheetName, "AccountHolder", rowNum);
                    Waitforelement();
                    AccountH.sendKeys(AccountHolder);
                    Waitforelement();
                    seleniumAction.clickWebElementObject(Select);
                    Waitforelement();
                    seleniumAction.scrollDown();
                    PatientDepNo.sendKeys("01");
                    Waitforelement();
                    seleniumAction.scrollUp();
                    // seleniumAction.clickWebElementObject(Save);
                }
            } else if (Check.equalsIgnoreCase("check")) {
                Waitforelement();
                MedicalAidplan.sendKeys("Discovery");

                List<WebElement> plist = wdriver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::a[@class='ng-binding ng-scope']"));
                System.out.println(plist.size());
                for (int i = 0; i < plist.size(); i++) {
                    Waitforelement();
                    if (plist.get(i).getText().equalsIgnoreCase("Discovery Health")) {
                        // String str=plist.get(i).getText();
                        //  System.out.println(str);
                        Waitforelement();
                        plist.get(i).click();
                        break;
                    }
                }
                seleniumAction.clickWebElementObject(MedicalAidInsurance);
                //wdriver.findElement(By.xpath("//input[@name='MedicalAidPlan']")).click();
                List<WebElement> list = wdriver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::a[@class='ng-binding ng-scope']"));

                for (int i = 0; i < list.size(); i++) {
                    Waitforelement();
                    if (list.get(i).getText().equals("Coastal Saver")) {
                        Waitforelement();
                        list.get(i).click();
                        break;
                    }
                }
                Waitforelement();
                MedicalAidNumber.sendKeys("5281452");
                Waitforelement();
                PatientDepNo.sendKeys("00");

            }
        }
        else if (FType.equalsIgnoreCase("Insurance"))
        {
            Xls_Reader reader = new Xls_Reader("C:\\Users\\laxmis\\IdeaProjects\\EZmed\\src\\main\\java\\za\\co\\ezmed\\qa\\utils\\addpatients.xlsx");
            String sheetName = "PrivatePatient";
            Waitforelement();
            if (Check.equalsIgnoreCase("uncheck")) {
                Waitforelement();
                seleniumAction.clickWebElementObject(MainCheckbox);
                Waitforelement();
                seleniumAction.clickWebElementObject(SelectPolicy);

                int rowCount = reader.getRowCount(sheetName);
                for (int rowNum = 2; rowNum <= rowCount; rowNum++)
                {
                    Waitforelement();
                    String AccountHolder = reader.getCellData(sheetName, "AccountHolder", rowNum);
                    Waitforelement();
                    AccountH.sendKeys(AccountHolder);
                    Waitforelement();
                    seleniumAction.clickWebElementObject(Select);
                    Waitforelement();
                    //seleniumAction.scrollDown();
                    //      seleniumAction.clickWebElementObject(Save);
                    break;
                }
            }
            else if (Check.equalsIgnoreCase("check"))
            {

                int rowCount = reader.getRowCount(sheetName);
                for (int rowNum = 2; rowNum <= rowCount; rowNum++)

                {
                    Waitforelement();
                    String PolicyNumber1 = reader.getCellData(sheetName, "AccountHolder", rowNum);
                    // String Insurer = reader.getCellData(sheetName, "Insurer", rowNum);
                    // String InsuranceOption = reader.getCellData(sheetName, "InsuranceOption", rowNum);
                    Waitforelement();
                    PolicyNumber.sendKeys(PolicyNumber1);
                    seleniumAction.clickWebElementObject(InsrerClick);

                    List<WebElement> plist = wdriver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::a[@class='ng-binding ng-scope']"));
                    System.out.println(plist.size());
                    for (int i = 0; i < plist.size(); i++) {
                        Waitforelement();
                        if (plist.get(i).getText().equalsIgnoreCase("African Health")) {
                            // String str=plist.get(i).getText();
                            //  System.out.println(str);
                            Waitforelement();
                            plist.get(i).click();

                            break;
                        }
                    }

                    seleniumAction.clickWebElementObject(MedicalAidInsurance);
                    List<WebElement> list = wdriver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::a[@class='ng-binding ng-scope']"));

                    for (int i = 0; i < list.size(); i++) {
                        Waitforelement();
                        if (list.get(i).getText().equals("African Health")) {
                            Waitforelement();
                            list.get(i).click();
                            break;
                        }
                    }

                    break;

                }


            }


        }
        else if (FType.equalsIgnoreCase("IOD"))
        {
            Xls_Reader reader = new Xls_Reader("C:\\Users\\laxmis\\IdeaProjects\\EZmed\\src\\main\\java\\za\\co\\ezmed\\qa\\utils\\addpatients.xlsx");

            String sheetName = "IOD";
            int rowCount = reader.getRowCount(sheetName);
            if(Check.contains("check"))
            for (int rowNum = 2; rowNum <= rowCount; rowNum++)
            {
                String CN = reader.getCellData(sheetName,"CompanyName", rowNum);
                String CR = reader.getCellData(sheetName, "CompanyReg", rowNum);
                String CE = reader.getCellData(sheetName, "ContactEmail", rowNum);
                String CoN = reader.getCellData(sheetName, "ComplexName",rowNum);
                String UN = reader.getCellData(sheetName, "UnitNo",rowNum);
                String SN = reader.getCellData(sheetName,"StreetName", rowNum);
                String City = reader.getCellData(sheetName,"CityName", rowNum);
                String PC = reader.getCellData(sheetName, "PostalCode", rowNum);
                String PV = reader.getCellData(sheetName, "Province", rowNum);

                Waitforelement();
                CompanyName.sendKeys(CN);
                Waitforelement();
                CompanyRegistrationNumber.sendKeys(CR);
                Waitforelement();
                Email.sendKeys(CE);
                seleniumAction.scrollDown();
                ComplexName.sendKeys(CoN);
                UnitNo.sendKeys(UN);
                StreetName.sendKeys(SN);
                CityName.sendKeys(City);
                PostalCode.sendKeys(PC);
                Province.sendKeys(PV);

                String cn="South Africa";
                    wdriver.findElement(By.xpath("//input[@placeholder='Country']")).sendKeys(cn);
                    List<WebElement> list = wdriver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::a[@class='ng-binding ng-scope']"));
                    Waitforelement();
                    for(int i = 0 ;i< list.size();i++)
                    {
                        if(list.get(i).getText().equals(cn))
                        {
                            list.get(i).click();

                            break;
                        }

                    }
                    seleniumAction.scrollUp();

            }


            }



        return true;
    }



    // Save.click();


}

