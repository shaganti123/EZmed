package za.co.ezmed.qa.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot
{

    static Logger logger= Logger.getLogger(Screenshot.class);

    public static void takeScreenshot(WebDriver webdriver)
    {
        try
        {
            TakesScreenshot screenshot=((TakesScreenshot)webdriver);
            File srcFile=screenshot.getScreenshotAs(OutputType.FILE);
            String timestamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String filePath="screenshots/EZMed"+timestamp+".png";
            File destFile=new File(filePath);
            FileUtils.copyFile(srcFile,destFile);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
        }
    }
}