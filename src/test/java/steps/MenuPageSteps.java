package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.framework.pages.MenuPage;
import org.automation.framework.setup.DriverManager;
import org.junit.jupiter.api.Assertions;
import utils.CustomLogger;

import java.util.List;

public class MenuPageSteps {

    private final CustomLogger logger;
    private final MenuPage menuPage = new MenuPage(DriverManager.getDriver());

    public MenuPageSteps(CustomLogger logger) {
        this.logger = logger;
    }

    @When("user selects Urządzenia from menu")
    public void userSelectUrzadzeniaFromMenu() {
        logger.logStep("Selecting [Urządzenia] from menu.");
        menuPage.selectDevicesFromMenu();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("dropdown list with below items should be visible")
    public void dropdownListWithBelowItemsShouldBeVisible(DataTable items) {
        List<String> expectedMenuItems = items.asList();
        logger.logStep("Checking if expected items are visible: [%s]", expectedMenuItems);

        List<String> actualMenuItems = menuPage.getSubMenuGroupsTitle();
        logger.logInfo("Actual items list: [%s]", actualMenuItems);
        Assertions.assertTrue(expectedMenuItems.containsAll(actualMenuItems),
                "Both list should contains the same element.");
    }

    @When("user selects {string} from {string} group")
    public void userSelectsFromGroup(String option, String group) {
        logger.logStep("Selecting [%s] from [%s] group.", option, group);
        menuPage.clickOnSelectItemFromMenu(option, group);
    }
}
