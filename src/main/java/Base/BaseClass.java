package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class BaseClass
{
protected WebDriver wdriver;
    public  BaseClass (WebDriver driver)
    {
        this.wdriver=driver;
        PageFactory.initElements(wdriver,this);
    }

    public boolean Waitforelement() throws InterruptedException
    {
       Thread.sleep(2000);
       return true;
    }
    public boolean ImplicitWait()
    {
        wdriver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
        return true;
    }
}
