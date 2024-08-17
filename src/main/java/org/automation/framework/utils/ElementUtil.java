package org.automation.framework.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ElementUtil {
    private final WebDriver driver;
    private final FluentWait<WebDriver> fluentWait;
    private final ConfigReader configReader;
    private final int toBeLocatedTimeout;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        this.configReader = new ConfigReader();
        toBeLocatedTimeout = configReader.getNumberProperty("to_be_located_timeout");
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(toBeLocatedTimeout))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public WebElement waitForElementVisible(WebElement element) {
        return fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementClickable(WebElement element) {
        return fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        WebElement elementToClick = waitForElementClickable(element);
        elementToClick.click();
    }

    public void moveToElement(WebElement element) {
        WebElement elementToMove = waitForElementVisible(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToMove).perform();
    }

    public void sendKeys(WebElement element, String text) {
        WebElement visibleElement = waitForElementVisible(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    public String getElementText(WebElement element) {
        return waitForElementVisible(element).getText();
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return waitForElementVisible(element).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public List<WebElement> getWebElements(List<WebElement> elements) {
        AtomicReference<List<WebElement>> webElements = new AtomicReference<>();
        fluentWait.until((WebDriver d) -> {
            webElements.set(new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(toBeLocatedTimeout))
                    .pollingEvery(Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOfAllElements(elements)));
            return true;
        });
        return webElements.get();
    }

    public WebElement getWebElement(String xPath) {
        AtomicReference<WebElement> webElement = new AtomicReference<>();
        fluentWait.until((WebDriver d) -> {
            webElement.set(new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(toBeLocatedTimeout))
                    .pollingEvery(Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath))));
            return true;
        });
        return webElement.get();
    }

    public void clickByJavaScript(WebElement element) {
        WebElement webElement = waitForElementClickable(element);
        fluentWait.until((WebDriver d) -> {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", webElement);
            return true;
        });
    }

}
