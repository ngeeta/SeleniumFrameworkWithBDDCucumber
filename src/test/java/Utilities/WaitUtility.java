package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import Base.BaseClass;

import java.time.Duration;

public class WaitUtility extends BaseClass {

    WebDriver driver = BaseClass.getDriver();

    public WaitUtility(WebDriver driver) {
       this.driver = driver;
    }

    // Explicit wait for element visibility
    public WebElement waitForVisibility(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    public WebElement waitForClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Fluent wait example
    public WebElement fluentWait(By locator, int timeoutInSeconds, int pollingInMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver -> driver.findElement(locator));
    }
}