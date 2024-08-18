package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.automation.framework.context.ScenarioContext;
import org.automation.framework.models.Rate;
import org.automation.framework.models.RateData;
import utils.CustomLogger;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class ExchangeRatesSteps {

    private final CustomLogger logger;
    private RateData[] rateData;
    private ScenarioContext context;

    public ExchangeRatesSteps(CustomLogger logger, ScenarioContext context) {
        this.logger = logger;
        this.context = context;
    }

    @Given("user gets exchange rates from NBP API")
    public void userGetsExchangeRatesFrom() {
        String url = context.getConfigReader().getStringProperty("nbp_api_url_table_a");
        logger.logStep("Getting exchange rates from [%s].", url);
        Response response = given().
                when().
                get(url).
                then().
                log().status().
                statusCode(200).
                extract().response();
        logger.logInfo("Response status code: [%s]", response.statusLine());
        rateData = response.as(RateData[].class);
    }

    @Then("user displays rate for a currency with code {string}")
    public void userDisplayRateForACurrencyWithCode(String currencyCode) {
        logger.logStep("Displaying exchange rate for currency with code [%s].", currencyCode);
        Optional<BigDecimal> midRate = Arrays.stream(rateData).toList().stream().
                flatMap(rateD -> rateD.getRates().stream()).
                filter(rate -> rate.getCode().equals(currencyCode)).
                map(Rate::getMid).
                findFirst();
        logger.logInfo(midRate.map(bigDecimal ->
                        String.format("Rate for currency [%s] is [%s].", currencyCode, bigDecimal)).
                orElseGet(() ->
                        String.format("Currency with code [%s] not found.", currencyCode)));
    }

    @And("user displays rate for a currency with name {string}")
    public void userDisplayRateForACurrencyWithName(String currencyName) {
        logger.logStep("Displaying exchange rate for currency with name [%s].", currencyName);
        Optional<BigDecimal> midRate = Arrays.stream(rateData).toList().stream().
                flatMap(rateD -> rateD.getRates().stream()).
                filter(currency -> currency.getCurrency().equals(currencyName)).
                map(Rate::getMid).
                findFirst();
        logger.logInfo(midRate.map(bigDecimal ->
                        String.format("Rate for currency [%s] is [%s].", currencyName, bigDecimal)).
                orElseGet(() ->
                        String.format("Currency with name [%s] not found.", currencyName)));

    }

    @And("user displays currencies with exchange rates above {bigdecimal}")
    public void userDisplaysCurrenciesWithExchangeRatesAbove(BigDecimal minExchangeRate) {
        logger.logStep("Displaying currencies with exchange rate above [%s].", minExchangeRate);
        List<Rate> currencies = Arrays.stream(rateData).toList().stream().
                flatMap(rateD -> rateD.getRates().stream()).
                filter(currency -> currency.getMid().compareTo(minExchangeRate) > 0).
                toList();

        if (currencies.isEmpty()) {
            logger.logInfo("No currencies found with exchange rate above [%s].", minExchangeRate);
        } else {
            currencies.forEach(currency -> logger.logInfo("[%s : %s]", currency.getCurrency(), currency.getMid()));
        }
    }

    @And("user displays currencies with exchange rates below {bigdecimal}")
    public void userDisplaysCurrenciesWithExchangeRatesBelow(BigDecimal maxExchangeRate) {
        logger.logStep("Displaying currencies with exchange rates below [%s].", maxExchangeRate);
        List<Rate> currencies = Arrays.stream(rateData).toList().stream().
                flatMap(rateD -> rateD.getRates().stream()).
                filter(currency -> currency.getMid().compareTo(maxExchangeRate) < 0).
                toList();

        if (currencies.isEmpty()) {
            logger.logInfo("No currencies found with exchange rate below [%s].", maxExchangeRate);

        } else {
            currencies.forEach(currency -> logger.logInfo("[%s : %s]", currency.getCurrency(), currency.getMid()));
        }
    }
}
