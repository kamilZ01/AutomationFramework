package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.framework.context.ScenarioContext;
import org.automation.framework.models.Product;
import org.automation.framework.pages.ProductDetailsPage;
import org.automation.framework.setup.DriverManager;
import org.junit.jupiter.api.Assertions;
import utils.CustomLogger;

public class ProductDetailsPageSteps {

    private final CustomLogger logger;
    private final ProductDetailsPage productDetailsPage = new ProductDetailsPage(DriverManager.getDriver());
    private final ScenarioContext context;
    private Product product;

    public ProductDetailsPageSteps(CustomLogger logger, ScenarioContext context) {
        this.logger = logger;
        this.context = context;
    }

    @Then("product page should be visible")
    public void productPageShouldBeVisible() {
        logger.logStep("Checking is product page is visible by locating header with phone name.");
        Assertions.assertTrue(productDetailsPage.isProductNameDisplayed(), "Product name should be visible on product page.");
        product = new Product();
        product.setName(productDetailsPage.getProductName());
        product.setStartPrice(productDetailsPage.getStartPrice());
        product.setMonthlyPrice(productDetailsPage.getMonthlyPrice());
        context.setProduct(product);
        logger.logInfo("Product page is confirmed to be visible.");
    }

    @When("user adds product to cart")
    public void userAddsProductToCart() {
        logger.logStep("Adding product [%s] to cart.", product.getName());
        productDetailsPage.clickAddToCart();
    }
}
