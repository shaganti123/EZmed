package Base;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumAction
{
    private static final Logger logger= LogManager.getLogger(SeleniumAction.class);
    private WebDriver wdriver;

    public SeleniumAction(WebDriver webDriver)
    {
        this.wdriver=webDriver;
    }

    public boolean findElementByLinkText(String textname)
    {
        try
        {
            this.wdriver.findElement(By.linkText(textname));
            return true;
        }
        catch (Exception e)
        {
            logger.error(String.format("Failed to find element %s",textname));
            return false;
        }
    }

    public boolean findElementByName(String name)
    {
        try
        {
            this.wdriver.findElement(By.name(name));
            return true;
        }
        catch (Exception e)
        {
            logger.error(String.format("Failed to find element by name locator %s",name));
            return false;
        }
    }
    public boolean findElementByXpath(String xpath)
    {
        try
        {
            this.wdriver.findElement(By.xpath(xpath));
            return true;
        }
        catch (Exception e)
        {
            logger.error(String.format("Failed to find element %s",xpath));
            return false;
        }
    }
    public boolean findElementByCssSelector(String css)
    {
        try
        {
            this.wdriver.findElement(By.cssSelector(css));
            return true;
        }
        catch (Exception e)
        {
            logger.error(String.format("Failed to find element by cssSelector locator %s",css));
            return false;
        }
    }

    public boolean findElementById(String id)
    {
        try
        {
            this.wdriver.findElement(By.id(id));
            return true;
        }
        catch (Exception e)
        {
            logger.error(String.format("Failed to find element by id locator %s",id));
            return false;
        }
    }
    public boolean findElementByBy(By by)
    {
        try
        {
            this.wdriver.findElement( by);
            return true;
        }
        catch (Exception e)
        {
            logger.error(String.format("Failed to find element by id locator %s",by));
            return false;
        }
    }


  public boolean clickElement(Object element)
  {
      try{
          ( (WebElement)element).click();
          return true;
      } catch (Exception var4)
      {
          logger.error(String.format("Element not successfully clicked %s",element.toString()));
          return false;
      }
  }

  public  boolean clickWebElementObject(WebElement webElement)
  {
      try
          {
             return this.clickElement(webElement);
          }catch (Exception var3)
      {
          logger.error(String.format("Element not successfully clicked %s",webElement.getTagName()));
          return false;
      }
  }
    public boolean waitForElementToBeClickable(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(this.wdriver, 12L);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (Exception var3) {
            logger.error(String.format("Element did not become clickable within 12s %s", webElement));
            return false;
        }
    }

    public boolean typeTextById(String id, String text) {
        try {
            WebElement webElement = this.wdriver.findElement(By.id(id));
            webElement.sendKeys(new CharSequence[]{text});
            return true;
        } catch (Exception var4) {
            logger.error(String.format("Text in id-located text field not typed in successfully %s", text));
            return false;
        }
    }

    public boolean typeTextByName(String name, String text) {
        try {
            WebElement webElement = this.wdriver.findElement(By.name(name));
            webElement.sendKeys(new CharSequence[]{text});
            return true;
        } catch (Exception var4) {
            logger.error(String.format("Text in name-located text field not typed in successfully %s", text));
            return false;
        }
    }

    public boolean typeTextByXpath(String xpath, String text) {
        try {
            WebElement webElement = this.wdriver.findElement(By.xpath(xpath));
            webElement.sendKeys(new CharSequence[]{text});
            return true;
        } catch (Exception var4) {
            logger.error(String.format("Text in xpath-located text field not typed in successfully %s", text));
            return false;
        }
    }

    public boolean typeTextByCssSelector(String css, String text) {
        try {
            WebElement webElement = this.wdriver.findElement(By.cssSelector(css));
            webElement.sendKeys(new CharSequence[]{text});
            return true;
        } catch (Exception var4) {
            logger.error(String.format("Text in css selector-located field not typed in successfully %s", text));
            return false;
        }
    }

    public boolean typeTextByBy(By by, String text) {
        try {
            WebElement webElement = this.wdriver.findElement(by);
            webElement.sendKeys(new CharSequence[]{text});
            return true;
        } catch (Exception var4) {
            logger.error(String.format("Text in cssSelector-located text field not typed in successfully %s", text));
            return false;
        }
    }

    public boolean typeText(Object element, String text) {
        try {
            ((WebElement)element).sendKeys(new CharSequence[]{text});
            return true;
        } catch (Exception var4) {
            logger.error(String.format("Text not successfully typed into textfield %s", text));
            return false;
        }
    }

    public boolean typeKeys(Object element, Object keys) {
        try {
            ((WebElement)element).sendKeys(new CharSequence[]{(Keys)keys});
            return true;
        } catch (Exception var4) {
            logger.error(String.format("Keys not successfully pressed into/from textfield %s", keys.toString()));
            return false;
        }
    }

    public boolean clearText(Object element) {
        try {
            ((WebElement)element).clear();
            return true;
        } catch (Exception var3) {
            logger.error(String.format("Text not successfully cleared from element %s", element));
            return false;
        }
    }

    public boolean replaceText(Object element, String replacementText) {
        try {
            String text = ((WebElement)element).getText();
            String newText = text.replaceAll(text, replacementText);
            ((WebElement)element).clear();
            ((WebElement)element).sendKeys(new CharSequence[]{newText});
            return true;
        } catch (Exception var5) {
            logger.error(String.format("Text not successfully replaced in element %s", element));
            return false;
        }
    }

    public boolean verifyTextPresent(String textToVerify) {
        try {
            return this.wdriver.getPageSource().contains(textToVerify);
        } catch (Exception var3) {
            logger.error(String.format("Text %s not present on page - %s", textToVerify, var3.getMessage()));
            return false;
        }
    }

    public boolean waitForElementToHaveText(WebElement webElement) {
        try {
            (new WebDriverWait(this.wdriver, 12L)).until((d) ->
            {
                return webElement.getText().length() != 0;
            });
            return true;
        } catch (Exception var3) {
            logger.error(String.format("Element was not populated with text within 12s %s", webElement));
            return false;
        }
    }

    public boolean waitForElementToBePresent(Object by) {
        try {
            WebDriverWait wait = new WebDriverWait(this.wdriver, 15L);
            wait.until(ExpectedConditions.presenceOfElementLocated((By)by));
            return true;
        } catch (Exception var3) {
            logger.error(String.format("Element did not become present within 15s %s", by.toString()));
            return false;
        }
    }

    public boolean waitForElementToBeVisible(Object element) {
        try {
            WebDriverWait wait = new WebDriverWait(this.wdriver, 30L);
            wait.until(ExpectedConditions.visibilityOf((WebElement)element));
            return true;
        } catch (Exception var3) {
            logger.error(String.format("Element %s did not become visible within 15s", element.toString()));
            return false;
        }
    }

    public boolean isElementDisplayed(Object element) {
        try {
            return ((WebElement)element).isDisplayed();
        } catch (Exception var3) {
            logger.error(String.format("Element %s not successfully displayed", element.toString()));
            return false;
        }
    }

    public boolean hoverMouseOverElement(By by) {
        try {
            Actions actions = new Actions(this.wdriver);
            actions.moveToElement(this.wdriver.findElement(by));
            return true;
        } catch (Exception var3) {
            logger.error(String.format("element located with By object not hovered to/over successfully %s", by.toString()));
            return false;
        }
    }

    public boolean hoverMouseOverElement(WebElement element) {
        try {
            Actions actions = new Actions(this.wdriver);
            actions.moveToElement(element);
            return true;
        } catch (Exception var3) {
            logger.error(String.format("element not hovered to/over successfully %s", element));
            return false;
        }
    }

    public boolean hoverMouseOverElement(Object element) {
        try {
            Actions actions = new Actions(this.wdriver);
            actions.moveToElement((WebElement)element);
            return true;
        } catch (Exception var3) {
            logger.error(String.format("element not hovered to/over successfully %s", element));
            return false;
        }
    }

    public String getPageSource() {
        try {
            return this.wdriver.getPageSource();
        } catch (Exception var2) {
            return null;
        }
    }

    public String getPageTitle() {
        try {
            return this.wdriver.getTitle();
        } catch (Exception var2) {
            logger.error("Page title not retrieved successfully");
            return null;
        }
    }

    public boolean refresh() {
        try {
            this.wdriver.navigate().refresh();
            return true;
        } catch (Exception var2) {
            logger.error("Page not refreshed successfully");
            return false;
        }
    }

    public boolean switchToWindow(String windowName) {
        try {
            this.wdriver.switchTo().window(windowName);
            return true;
        } catch (Exception var3) {
            logger.error(String.format("Window was not switched to successfully %s", windowName));
            return false;
        }
    }

    public boolean maximiseWindow() {
        try {
            this.wdriver.manage().window().maximize();
            return true;
        } catch (Exception var2) {
            logger.error("Window was not maximized successfully");
            return false;
        }
    }

    public boolean scrollDown() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor)this.wdriver;
            jse.executeScript("window.scrollBy(0,300)", new Object[]{"100"});
            return true;
        } catch (Exception var2) {
            logger.error("Scroll down failed for value");
            return false;
        }
    }

    public boolean scrollUp() {
        try {
            JavascriptExecutor jse = (JavascriptExecutor)this.wdriver;
           // jse.executeScript("window.scrollBy(0,-500)", new Object[]{"100"});
            jse.executeScript("window.scrollTo(0,document.body.scrollTop)");
            return true;
        } catch (Exception var2) {
            logger.error("Scroll down failed for value");
            return false;
        }
    }

    public boolean scrollDownuptoEle(WebElement element, WebElement felement, WebElement melement){
        JavascriptExecutor js = (JavascriptExecutor) wdriver;
        js.executeScript("arguments[0].scrollIntoView();",element );
        return true;
    }

    public boolean dropdownValue(WebElement element, String value)
    {
        try{
            Select select= new Select(element);
            select.selectByVisibleText(value);
        }catch (org.openqa.selenium.StaleElementReferenceException ex) {

            Select select = new Select(element);
            select.selectByVisibleText(value);
        } return  true;
    }
}
