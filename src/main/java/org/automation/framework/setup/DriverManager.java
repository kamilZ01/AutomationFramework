package org.automation.framework.setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.automation.framework.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public WebDriver initDriver(BrowserType browser) {
        System.out.printf("Browser value is: [%s].\n", browser);

        switch (browser) {
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            default -> throw new IllegalStateException("Please pass the correct browser value.");
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();
    }

}
