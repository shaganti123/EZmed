package za.co.ezmed.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;
import java.util.logging.Logger;

public class WebElementSearcher
{

   private WebElementSearcher()
   {

   }
   private static Logger logger = Logger.getLogger(String.valueOf(WebElementSearcher.class));

   public static WebElement  elementsearchSettlementCondition( WebDriver wdriver,By searchMethod)
   {

      DocumentSettleCondition <WebElement> settleCondition = new DocumentSettleCondition(
              ExpectedConditions.visibilityOfElementLocated(searchMethod));
              return new FluentWait<>(wdriver)
              .withTimeout(Duration.ofSeconds(90))
              .pollingEvery(Duration.ofMillis(settleCondition.getSettleTime()))
              .ignoring(WebDriverException.class)
              .until(settleCondition);

   }
   public static WebElement  elementsearchSettlementConditionWithTimeLimit( WebDriver wdriver,By searchMethod,int time)
   {

      DocumentSettleCondition <WebElement> settleCondition = new DocumentSettleCondition(
              ExpectedConditions.visibilityOfElementLocated(searchMethod));
      return new FluentWait<>(wdriver)
              .withTimeout(Duration.ofSeconds(time))
              .pollingEvery(Duration.ofMillis(settleCondition.getSettleTime()))
              .ignoring(WebDriverException.class)
              .until(settleCondition);

   }
   public static WebElement elementsearchFluentWait( WebDriver wdriver,By searchMethod)
   {
      Wait<WebDriver> wait = new FluentWait<>(wdriver)
              .withTimeout(Duration.ofSeconds(180))
              .pollingEvery(Duration.ofSeconds(2))
              .ignoring(WebDriverException.class);

      return wait.until((Function<WebDriver,WebElement>) driver ->
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
              .withTimeout(Duration.ofSeconds(maxWaitTime))
              .pollingEvery(Duration.ofSeconds(2))
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
