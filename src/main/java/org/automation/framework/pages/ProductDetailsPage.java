package org.automation.framework.pages;

import org.automation.framework.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {

    private final ElementUtil elementUtil;
    @FindBy(xpath = "//h1[@data-qa='PRD_ProductName']")
    private WebElement productName;
    @FindBy(xpath = "//span//div[contains(@class,'priceInfoSection')]//div[.//div[text()='Do zapłaty na start'] and contains(@class,'PriceInfo')]//div[@class='dt_price_change']")
    private WebElement startPrice;
    @FindBy(xpath = "//span//div[contains(@class,'priceInfoSection')]//div[.//div[text()='Do zapłaty miesięcznie'] and contains(@class,'PriceInfo')]//div[@class='dt_price_change']")
    private WebElement monthlyPrice;
    @FindBy(xpath = "//span//button[.//div[text()='Dodaj do koszyka']]")
    private WebElement addToCartButton;

    public ProductDetailsPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isProductNameDisplayed() {
        return elementUtil.isElementDisplayed(productName);
    }

    public String getProductName() {
        return elementUtil.getElementText(productName);
    }

    public String getStartPrice() {
        return elementUtil.getElementText(startPrice);
    }

    public String getMonthlyPrice() {
        return elementUtil.getElementText(monthlyPrice);
    }

    public void clickAddToCart() {
        elementUtil.click(addToCartButton);
    }
}
