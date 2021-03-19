package za.co.ezmed.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class AdditionalConditions
{

    private AdditionalConditions()
    {

    }
    public static ExpectedCondition<Boolean> angularHasFinishedProcession()
    {
        return (WebDriver driver) ->Boolean.valueOf(((JavascriptExecutor)driver).executeScript("return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && ((angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
    }
}
