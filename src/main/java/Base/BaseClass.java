package Base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    protected WebDriver wdriver;

    public BaseClass(WebDriver driver) {
        this.wdriver = driver;
        PageFactory.initElements(wdriver, this);
    }

    public boolean Waitforelement() throws InterruptedException {
        Thread.sleep(1000);
        return true;
    }

    public boolean ImplicitWait() {
        wdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return true;
    }
    /**
     * Sometime it happens while automating the angular app, view gets loaded entirely but performing any action
     * on that view fails the test. This could happen because angular $http calls are still pending in backend.
     * We can have explicit wait in this way to ensure that angular has made all the $http calls.
     * Wait until angular finishes the $http calls while loading the view
     */
    public void waitForAngular() {
        final String javaScriptToLoadAngular =
                "var injector = window.angular.element('body').injector();" +
                        "var $http = injector.get('$http');" +
                        "return ($http.pendingRequests.length === 0)";

        ExpectedCondition<Boolean> pendingHttpCallsCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript(javaScriptToLoadAngular).equals(true);
            }
        };
        WebDriverWait wait = new WebDriverWait(wdriver, 20);
        wait.until(pendingHttpCallsCondition);
    }
    }

