package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.automation.framework.pages.HomePage;
import org.automation.framework.setup.DriverManager;
import org.junit.jupiter.api.Assertions;
import utils.CustomLogger;

public class HomePageSteps {

    private final CustomLogger logger;
    private final HomePage homePage = new HomePage(DriverManager.getDriver());

    public HomePageSteps(CustomLogger logger) {
        this.logger = logger;
    }

    @Given("user navigates to {string} page")
    public void user_is_on_page(String url) {
        logger.logStep("Opening [%s] page.", url);
        DriverManager.getDriver().get(url);
    }

    @Then("homepage should be visible")
    public void homepageShouldBeVisible() {
        logger.logStep("Checking if the homepage is visible by locating T-Mobile logo.");
        Assertions.assertTrue(homePage.isLogoDisplayed(), "T-Mobile logo should be visible on the homepage.");
        logger.logInfo("Homepage is confirmed to be visible.");
    }

    @Then("user accepts all cookies")
    public void userAcceptsAllCookies() {
        logger.logStep("Clicking [Zaakceptuj wszystkie] button.");
        homePage.clickAcceptCookies();
    }

}
