package za.co.ezmed.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class DocumentSettleCondition<T> implements ExpectedCondition<T> {

    private final ExpectedCondition<T> condition;
    private final long settleTimeInMillis;
    private long lastComplete=0L;
    private String lastUrl;
    public DocumentSettleCondition(ExpectedCondition<T> condition, long settleTimeInMillis)
    {
        this.condition=condition;
        this.settleTimeInMillis=settleTimeInMillis;
    }
    public DocumentSettleCondition(ExpectedCondition<T> condition)
    {
        this(condition,10000L);

    }

    /**
     *
     * Get the settle time in millis
     */
    public long getSettleTime()
    {
        return settleTimeInMillis;
    }



    @Override
    public T apply(WebDriver driver)
    {
        if(driver instanceof JavascriptExecutor)
        {
            String currentUrl=driver.getCurrentUrl();
            String readyState=String.valueOf(((JavascriptExecutor)driver).executeScript("return document.readyState"));
            boolean complete=readyState.equalsIgnoreCase("complete");

            if(!complete)
            {
                lastComplete=0L;
                return null;
            }
            if(lastUrl !=null && !lastUrl.equals(currentUrl))
            {
                lastComplete=0L;
            }
            lastUrl=currentUrl;

            if (lastComplete==0L)
            {
                lastComplete=System.currentTimeMillis();
                return null;
            }
            long settleTime=System.currentTimeMillis()-lastComplete;
            if(settleTime < this.settleTimeInMillis)
            {
                return null;
            }
        }
        return condition.apply(driver);
    }

    @Override
    public String toString()
    {
        return "Document settle @"+settleTimeInMillis+"ms for"+condition;
    }



}

