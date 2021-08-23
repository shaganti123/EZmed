package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    private By EP=By.xpath("//input[@role='combobox']");
    private By NoteType=By.xpath("//select[contains(@ng-model,'ctrl.patientNote.DocumentTypeGuid')]");
    //@FindBy(xpath = "//select[contains(@ng-model,'ctrl.patientNote.DocumentTypeGuid')]")
  //  WebElement NoteType;

    @FindBy(xpath = "//select[contains(@ng-model,'ctrl.patientNote.NoteVisibilityType')]")
    WebElement NoteVisibility;

    SeleniumAction seleniumAction;
    public Notes(WebDriver driver) {
        super(driver);
        seleniumAction = new SeleniumAction(wdriver);
    }
    public void Notes ()
    {
        List<WebElement> b = wdriver.findElements(By.xpath(xpathOfButtons));
        String text= b.get(6).getText();
        System.out.println(text);
        if(text.contains("E.O.C")){
            b.get(9).click();
        }
        else if (!text.contains("E.O.C"))
        {
            b.get(8).click();
        }

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
        WebElement NoteTypes= WebElementSearcher.elementsearchSettlementConditionWithTimeLimit(wdriver,NoteType,1  );
        seleniumAction. dropdownValue(NoteTypes, NType);
        Waitforelement();
        seleniumAction.dropdownValue(NoteVisibility,NVisibility);
        Waitforelement();
        int size = wdriver.findElements(By.tagName("iframe")).size();
        wdriver.switchTo().frame(0);
        seleniumAction.typeText(Content,"Notes");
        wdriver.switchTo().defaultContent();
    }
}
