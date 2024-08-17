package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.automation.framework.context.ScenarioContext;
import org.automation.framework.models.BasketSummary;
import org.automation.framework.models.Product;
import org.automation.framework.pages.BasketPage;
import org.automation.framework.setup.DriverManager;
import org.junit.jupiter.api.Assertions;
import utils.CustomLogger;

public class BasketPageSteps {

    private final CustomLogger logger;
    private final BasketPage basketPage = new BasketPage(DriverManager.getDriver());
    private final ScenarioContext context;

    public BasketPageSteps(CustomLogger logger, ScenarioContext context) {
        this.logger = logger;
        this.context = context;
    }

    @Then("your basket page should be visible")
    public void yourCartPageShouldBeVisible() {
        logger.logStep("Checking if the Your basket page is visible by locating header text.");
        Assertions.assertTrue(basketPage.isHeaderDisplayed(), "Header text should be visible on basket page.");
        logger.logInfo("Basket page is confirmed to be visible.");
    }

    @And("price inside basket should be the same as on the product details page")
    public void priceInsideTheCartShouldBeTheSameAsOnTheProductDetailsPage() {
        logger.logStep("Checking that the prices on the product page and in the basket are the same.");
        Product product = context.getProduct();
        BasketSummary basketSummary = new BasketSummary();
        basketSummary.setStartPrice(basketPage.getStartPrice());
        basketSummary.setMonthlyPrice(basketPage.getMonthlyPrice());
        Assertions.assertEquals(product.getStartPrice(), basketSummary.getStartPrice(),
                "Start price from product page should match the price inside basket.");
        logger.logInfo("Start price [%s] is the same on both pages.", product.getStartPrice());
        Assertions.assertEquals(product.getMonthlyPrice(), basketSummary.getMonthlyPrice(),
                "Monthly price from product page should match the price inside basket.");
        logger.logInfo("Monthly price [%s] is the same on both pages.", product.getMonthlyPrice());
    }
}
