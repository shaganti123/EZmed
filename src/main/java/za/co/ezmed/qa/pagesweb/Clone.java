package za.co.ezmed.qa.pagesweb;

import Base.BaseClass;
import Base.SeleniumAction;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import za.co.ezmed.qa.utils.JSWaiter;
import za.co.ezmed.qa.utils.WebElementSearcher;

import java.util.List;

public class Clone extends BaseClass {
    int i;
    String brefore_xpath="//tbody/tr[";
    String after_xpathk="]/td[6]";
    String after_sxpath="]/td[9]";
    String Button="]/td[12]/div[@class='pull-right']/button-group/div[@class='btn-group  bg']/button[2]";
    String Xpathofrecords = "//ul[@role='menu']//li/descendant::button[@type='button']";
    SeleniumAction seleniumAction;
    public Clone(WebDriver driver) {
        super(driver);

        seleniumAction = new SeleniumAction(wdriver);

    }
    public void CloneProforma(String Tdate, String Status) throws InterruptedException {
        List<WebElement> b = wdriver.findElements(By.xpath(Xpathofrecords));
        for( i=1; i<=b.size();i++) {
            JSWaiter.setDriver(this.wdriver);
            JSWaiter.waitJQueryAngular();
            String TName = wdriver.findElement(By.xpath(brefore_xpath + i + after_xpathk)).getText();
            System.out.println(TName);

            if(TName.equals(Tdate))
            {
                String CStatus = wdriver.findElement(By.xpath(brefore_xpath + i + after_sxpath)).getText();
                    if(CStatus.contains(Status))
                    {
                        WebElement ele=wdriver.findElement(By.xpath(brefore_xpath + i + Button));
                        JavascriptExecutor executor = (JavascriptExecutor)wdriver;
                        executor.executeScript("arguments[0].click();",ele);
                        break;

                            }
                        }
                    }

                    }

                    public void ClaimRef(String CliamStatus){
                        for( int k=1; k<=10;k++) {
                            String CStatus = wdriver.findElement(By.xpath(brefore_xpath + k + after_sxpath)).getText();
                            if(CStatus.contains(CliamStatus))
                            {
                                String ClaimRef = wdriver.findElement(By.xpath(brefore_xpath + k + "]/td[1]")).getText();
                                System.out.println(ClaimRef);
                            }

                        }

                    }
                    public void action(String Action){
                    List<WebElement> Clone=wdriver.findElements(By.xpath(brefore_xpath + i + "]/td[12]/div[@class='pull-right']/button-group/div/ul[@class='dropdown-menu']/li/descendant::button[@type='button']"));
                        for(int j=0;j<=Clone.size();j++) {
                            String text = Clone.get(j).getText();
                            System.out.println(text);
                            if (text.contains(Action)) {
                                Clone.get(j).click();
                                break;
                            }
                        }
                    }




                }


