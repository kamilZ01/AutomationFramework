package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.framework.pages.DevicesPage;
import org.automation.framework.setup.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import utils.CustomLogger;

import java.util.List;

public class DevicesPageSteps {

    private final CustomLogger logger;
    private final DevicesPage devicesPage = new DevicesPage(DriverManager.getDriver());
    private List<WebElement> devicesList;

    public DevicesPageSteps(CustomLogger logger) {
        this.logger = logger;
    }

    @Then("devices section count is {int}")
    public void devicesSectionCountIs(int expectedDevicesCount) {
        logger.logStep("Checking if [%s] devices are available on page.", expectedDevicesCount);
        devicesList = devicesPage.getDevicesCount();
        logger.logInfo("Found [%s] devices on page.", devicesList.size());
        Assertions.assertEquals(expectedDevicesCount, devicesList.size(),
                "Number of devices on the site should match the expected values.");
    }

    @When("user select first element from the list")
    public void userSelectFirstElementFromTheList() {
        logger.logStep("Selecting first element from the devices list.");
        devicesPage.clickOnElement(devicesList.getFirst());
    }

}
