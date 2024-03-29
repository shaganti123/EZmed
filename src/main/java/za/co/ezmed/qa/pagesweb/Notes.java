package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.apache.poi.hssf.record.PageBreakRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.awt.*;
import java.util.List;

public class Notes extends BaseClass {
    String xpathOfButtons = "//ul[@role='menu']//li/descendant::button[@type='button']";
    @FindBy(xpath = "//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
    private WebElement Content;
    private By AddNote=By.xpath("//button[contains(@ng-show,'ctrl.showNote')]");
    @FindBy(xpath = "//input[@placeholder='Heading']")
    WebElement Heading;

    String brefore_xpath="//div[@class='well blog-panel ng-scope'][";
    String after_sxpath="]/div/div/div/div/h3";
    String EditXpath="]/div/div[2]//button[contains(@ng-click,'ctrl.createOrUpdateNote')]";

    private By EP=By.xpath("//input[@role='combobox']");
    private By NoteType=By.xpath("//select[contains(@ng-model,'ctrl.patientNote.DocumentTypeGuid')]");
    //@FindBy(xpath = "//select[contains(@ng-model,'ctrl.patientNote.DocumentTypeGuid')]")
  //  WebElement NoteType;

    @FindBy(xpath = "//select[contains(@ng-model,'ctrl.patientNote.NoteVisibilityType')]")
    WebElement NoteVisibility;
    @FindBy(xpath = "//button[@uib-tooltip='Expand/Collapse panel']")
    WebElement NV;

    JavascriptExecutor executor = (JavascriptExecutor)wdriver;


    SeleniumAction seleniumAction;
    public Notes(WebDriver driver) {
        super(driver);
        seleniumAction = new SeleniumAction(wdriver);
    }
    public void Notes ()
    {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        for(int i =0;i<=b.size();i++) {
            String text = b.get(i).getText();
            if (text.contains("Notes")) {
                b.get(i).click();
                break;
            }
        }

    }

    public void AddNotes(){
        WebElement Add= WebElementSearcher.elementsearchSettlementCondition(wdriver,AddNote);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(Add);
    }
    public void EditingPermission(String EPs) throws InterruptedException, AWTException {
        WebElement EPS= WebElementSearcher.elementsearchFluentWait(wdriver, EP);
        EPS.sendKeys(EPs);
        Thread.sleep(10000);
        List<WebElement> list = wdriver.findElements(By.xpath("//li/descendant::div[@role='option']"));
        //System.out.println(list);

        for (WebElement element: list) {
            System.out.println(element.getText());
            element.click();
        }

    }
    public void NoteDetails(String NType, String NVisibility,String NotesHeading) throws InterruptedException {
        Waitforelement();
        seleniumAction.typeText(Heading,NotesHeading);
        WebElement NoteTypes= WebElementSearcher.elementsearchFluentWait(wdriver,NoteType);
        seleniumAction. dropdownValue(NoteTypes, NType);
        Waitforelement();
        seleniumAction.dropdownValue(NoteVisibility,NVisibility);
        Waitforelement();
        int size = wdriver.findElements(By.tagName("iframe")).size();
        wdriver.switchTo().frame(0);
        seleniumAction.typeText(Content,"Notes");
        wdriver.switchTo().defaultContent();
    }

    public void Edit() throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();

        for (int i = 1; i <= 5; i++) {
            Thread.sleep(5000);
            String text = wdriver.findElement(By.xpath(brefore_xpath + i + after_sxpath)).getText();
            WebElement element = wdriver.findElement(By.xpath(brefore_xpath + i + EditXpath));
            if (text.contains("Notes Version")) {
                executor.executeScript("arguments[0].click();", element);
                //(brefore_xpath+i+EditXpath);
                break;
            }
        }

    }
    public void EditNotes(String Edit) throws InterruptedException {
        JSWaiter.setDriver(this.wdriver);
        JSWaiter.waitJQueryAngular();
        if(Edit.contentEquals("Edit")){
            Thread.sleep(2000);
            wdriver.switchTo().frame(0);
            seleniumAction.typeText(Content,"V2");
            wdriver.switchTo().defaultContent();
        }
         if (Edit.contentEquals("Version")){
            JSWaiter.setDriver(this.wdriver);
            JSWaiter.waitJQueryAngular();
            executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            seleniumAction.clickWebElementObject(NV);
        }


    }

}
