package za.co.ezmed.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.text.html.StyleSheet;

public class AdditionalConditions
{

    public static void waitForPageToBeReady(WebDriver wdriver){
    // JavaScript Executor to check ready state
    JavascriptExecutor j = (JavascriptExecutor)wdriver;
      if (j.executeScript("return document.readyState").toString().equals("complete")){
    System.out.println("Page has loaded");
}
    //iterate 50 times after every one second to verify if in ready state
      for (int i=0; i<50; i++){
    try {
        Thread.sleep(1000);
    }catch (InterruptedException ex) {
        System.out.println("Page has not loaded yet ");
    }
    //again check page state
    if (j.executeScript("return document.readyState").toString().equals("complete")){
        break;


}
      }
    }
}

