package steps;

import io.cucumber.java.en.Given;
import org.automation.framework.setup.DriverManager;
import utils.CustomLogger;

public class HomePageSteps {

    private final CustomLogger logger;

    public HomePageSteps(CustomLogger logger) {
        this.logger = logger;
    }

    @Given("user is on {string} page")
    public void user_is_on_page(String url) {
        logger.logStep("Opening [%s] page.", url);
        DriverManager.getDriver().get(url);
    }

}
