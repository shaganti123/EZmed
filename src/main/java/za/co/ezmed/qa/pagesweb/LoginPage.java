package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.util.concurrent.TimeUnit;


/**
 * @author laxmis
 */

public class LoginPage extends BaseClass
{
    @FindBy(xpath = "//div[@class='portlet']//input[@id='username']")
    private WebElement username;

    private By passwordBy = By.xpath("//div[@class='portlet']//input[@id='password']");
    private By usernameBy = By.xpath("//div[@class='portlet']//input[@id='username']");

    @FindBy(xpath = "//div[@class='portlet']//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//div//button[@type='submit']")
    private WebElement loginbutton;

    private By WelcomeBy = By.xpath("//a[contains(@class, 'dropdown-toggle')]");

    @FindBy(xpath = "//a[contains(@class, 'dropdown-toggle')]")
    private WebElement Welcome;

    @FindBy(xpath = "//a[@ng-click='Logout()']")
    private WebElement Logout;

    @FindBy(xpath = "//*[@class='c-bn']")
    private WebElement CookiesAccept;


    private By LogoutBy = By.xpath("//a[@ng-click='Logout()']");
    public By Home = By.xpath("//div[@class='navbar-header hidden-xs']/a");

        SeleniumAction seleniumAction;

    public LoginPage (WebDriver driver)
    {
    super(driver);

         seleniumAction= new SeleniumAction(wdriver);
    }

    public void loginPage(String un, String pw ) throws InterruptedException
    {
        for(int i=0;i<3;i++)
        {
            System.out.println("In for loop");
            try
            {
                if(wdriver.findElements(usernameBy).size()>0)
                {
                    System.out.println("Element found");
                    WebElement username =WebElementSearcher.elementsearchSettlementCondition(wdriver,usernameBy);
                    seleniumAction.typeText(username,un);
                    try {
                        CookiesAccept.click();
                    } catch(NoSuchElementException e) {
                    }
                    WebElement password=WebElementSearcher.elementsearchFluentWait(wdriver,passwordBy);
                    seleniumAction.typeText(password,pw);
                    Screenshot.takeScreenshot(wdriver);
                    seleniumAction.clickWebElementObject(loginbutton);
                    WebElement home=WebElementSearcher.elementsearchSettlementCondition(wdriver,Home);
                    home.click();
                    i=3;
                }
                else
                {
                    System.out.println("Element not found"+i+" try");
                    wdriver.navigate().refresh();
                    wdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                    i++;
                }
            }
            catch(NoSuchElementException e)
            {
                System.out.println(e);
            }

        /*


        WebElement username =WebElementSearcher.elementsearchSettlementCondition(wdriver,usernameBy);
        seleniumAction.typeText(username,un);
        try {
            CookiesAccept.click();
        } catch(NoSuchElementException e) {
        }
        WebElement password=WebElementSearcher.elementsearchFluentWait(wdriver,passwordBy);
        seleniumAction.typeText(password,pw);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(loginbutton);
        WebElement home=WebElementSearcher.elementsearchSettlementCondition(wdriver,Home);
        home.click();

         */
    }
    }

    public void logout() throws InterruptedException {
        WebElement Welcome =WebElementSearcher.elementsearchSettlementConditionWithTimeLimit(wdriver,WelcomeBy,20);
        seleniumAction.waitForElementToBeVisible(Welcome);
        seleniumAction.clickWebElementObject(Welcome);
        WebElement Logout =WebElementSearcher.elementsearchFluentWait(wdriver,LogoutBy);
        seleniumAction.clickWebElementObject(Logout);
        Waitforelement();
        Screenshot.takeScreenshot(wdriver);
        wdriver.navigate().refresh();
        Screenshot.takeScreenshot(wdriver);
        wdriver.close();

    }
}
