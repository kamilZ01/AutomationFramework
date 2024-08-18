package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.automation.framework.context.ScenarioContext;
import org.automation.framework.enums.BrowserType;
import org.automation.framework.setup.DriverManager;
import org.automation.framework.utils.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AppHooks {

    private Scenario scenario;
    private WebDriver driver;
    private final ScenarioContext scenarioContext;

    public AppHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    public Scenario getScenario() {
        return scenario;
    }

    @Before(order = 0)
    public void launchBrowser(Scenario scenario) {
        ConfigReader configReader = new ConfigReader();
        configReader.initProp();
        boolean isRest = scenario.getSourceTagNames().contains("@Rest");
        if (!isRest) {
            BrowserType browser = configReader.getBrowser();
            DriverManager driverFactory = new DriverManager();
            driver = driverFactory.initDriver(browser);
        }
        this.scenario = scenario;
    }

    @After(order = 0)
    public void quitBrowser() {
        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }
}
