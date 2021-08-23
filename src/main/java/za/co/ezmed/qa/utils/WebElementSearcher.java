package za.co.ezmed.qa.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Logger;

public class WebElementSearcher
{

   private WebElementSearcher()
   {

   }
   private static Logger logger = Logger.getLogger(String.valueOf(WebElementSearcher.class));

   public static WebElement elementsearchSettlementCondition( WebDriver wdriver,By searchMethod)
   {

      DocumentSettleCondition <WebElement> settleCondition = new DocumentSettleCondition(
              ExpectedConditions.visibilityOfElementLocated(searchMethod));
              return new FluentWait<>(wdriver)
              .withTimeout(Duration.ofMinutes(10))
              .pollingEvery(Duration.ofMillis(settleCondition.getSettleTime()))
              .ignoring(WebDriverException.class)
              .until(settleCondition);

   }
   public static WebElement  elementsearchSettlementConditionWithTimeLimit( WebDriver wdriver,By searchMethod,int time)
   {

      DocumentSettleCondition <WebElement> settleCondition = new DocumentSettleCondition(ExpectedConditions.visibilityOfElementLocated(searchMethod));
      return new FluentWait<>(wdriver)
            .withTimeout(Duration.ofMinutes(time))
            //  .pollingEvery(settleCondition.getSettleTime(), TimeUnit.MILLISECONDS)
              .pollingEvery(Duration.ofMillis(settleCondition.getSettleTime()))
              .ignoring(WebDriverException.class)
              .until(settleCondition);

              

   }
    public static void WaitForAjax2Complete(WebDriver driver)
    {

            if ((Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0")){

            }

    }
   public static WebElement elementsearchFluentWait( WebDriver wdriver,By searchMethod)
   {
      Wait<WebDriver> wait = new FluentWait<>(wdriver)
              .withTimeout(Duration.ofMinutes(1))
              .pollingEvery(Duration.ofSeconds(5))
              .ignoring(WebDriverException.class);

      return
              wait.until((Function<WebDriver,WebElement>) driver ->
      {
            WebElement searchedElement = driver.findElement(searchMethod);
            if(searchedElement.isEnabled())
            {
               logger.info("Element was found : " + searchMethod);
            }else{
               logger.info("Element was not found : "+searchMethod );
            }
            return searchedElement;
      });

   }


   public static WebElement elementsearchWithTimeLimit( WebDriver wdriver,By searchMethod,int maxWaitTime)
   {

      Wait<WebDriver> wait = new FluentWait(wdriver)
              .withTimeout(Duration.ofMinutes(maxWaitTime))
              .pollingEvery(Duration.ofSeconds(5))
              .ignoring(WebDriverException.class);

      return wait.until((Function<WebDriver, WebElement>) driver ->
      {

            WebElement searchedElement = driver.findElement(searchMethod);
            if (searchedElement.isEnabled()) {
               logger.info("Element was found : " + searchMethod);
            } else {
               logger.info("Element was not found : " + searchMethod);
            }
            return searchedElement;
      });

   }

   }
