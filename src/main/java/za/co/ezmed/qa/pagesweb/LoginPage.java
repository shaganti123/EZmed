package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.co.ezmed.qa.utils.Screenshot;
import za.co.ezmed.qa.utils.WebElementSearcher;

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

    @FindBy(xpath = "//body//div//div//div//div//div//div//div//div//div//login-form//button[1]")
    private WebElement loginbutton;

    private By WelcomeBy = By.xpath("//a[contains(@class, 'dropdown-toggle')]");

    @FindBy(xpath = "//a[contains(@class, 'dropdown-toggle')]")
    private WebElement Welcome;

    @FindBy(xpath = "//a[@ng-click='Logout()']")
    private WebElement Logout;



    private By LogoutBy = By.xpath("//a[@ng-click='Logout()']");


        SeleniumAction seleniumAction;



    public LoginPage (WebDriver driver)
    {
    super(driver);

         seleniumAction= new SeleniumAction(wdriver);
    }

    public void loginPage(String un, String pw ) throws InterruptedException
    {
        WebElement username =WebElementSearcher.elementsearchSettlementCondition(wdriver,usernameBy);
        seleniumAction.typeText(username,un);
        WebElement password=WebElementSearcher.elementsearchSettlementCondition(wdriver,passwordBy);
        seleniumAction.typeText(password,pw);
        Screenshot.takeScreenshot(wdriver);
        seleniumAction.clickWebElementObject(loginbutton);
        wdriver.navigate().to(wdriver.getCurrentUrl());

    }

    public void logout() throws InterruptedException {
       // WebElement welcome =WebElementSearcher.elementsearchFluentWait(wdriver,usernameBy);
        Waitforelement();
        Waitforelement();
        seleniumAction.clickWebElementObject(Welcome);
        Waitforelement();
        Screenshot.takeScreenshot(wdriver);
       // WebElement logout =WebElementSearcher.elementsearchSettlementCondition(wdriver,usernameBy);
        seleniumAction.clickWebElementObject(Logout);
        wdriver.navigate().refresh();
        //WebElementSearcher.elementsearchSettlementCondition(wdriver,usernameBy);
        Screenshot.takeScreenshot(wdriver);
        wdriver.close();

    }
}
