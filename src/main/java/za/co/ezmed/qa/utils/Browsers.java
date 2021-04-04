package za.co.ezmed.qa.utils;

import Base.SeleniumAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;


public class Browsers {
     WebDriver wdriver;
     SeleniumAction seleniumAction;


    @Parameters("browser")
    public void setup(String browser) throws Exception{
        if(browser.equalsIgnoreCase("firefox")){
            // System.setProperty("webdriver.chrome.driver","src"+ File.separator + "main" + File.separator + "resources" + File.separator + "Drivers" + File.separator + "chromedriver.exe");
            System.setProperty("webdriver.chrome.driver","C://Users//laxmis//Desktop//chromedriver.exe");
            wdriver = new FirefoxDriver();
            wdriver.get("https://ezmed.spesstage.co.za/");

        }
        else if(browser.equalsIgnoreCase("chrome")){
            // System.setProperty("webdriver.chrome.driver","src"+ File.separator + "main" + File.separator + "resources" + File.separator + "Drivers" + File.separator + "chromedriver.exe");
            System.setProperty("webdriver.chrome.driver","C://Users//laxmis//Desktop//chromedriver.exe");
            wdriver = new ChromeDriver();

            wdriver.manage().window().maximize();
            wdriver.manage().deleteAllCookies();
            wdriver.get("https://ezmed.spesstage.co.za/");
        }
    }
}