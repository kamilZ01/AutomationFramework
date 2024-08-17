package org.automation.framework.pages;

import org.automation.framework.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasketPage {

    private final ElementUtil elementUtil;

    @FindBy(xpath = "//h1[contains(@class,'basketHeaderText')]")
    private WebElement basketHeader;
    @FindBy(xpath = "//div[.//div[text()='Płatność na start'] and contains(@class,'accordionHeader')]//span[@data-qa='BKT_TotalupFront']")
    private WebElement startPrice;
    @FindBy(xpath = "//div[.//div[text()='Miesięcznie'] and contains(@class,'accordionHeader')]//span[@data-qa='BKT_TotalMonthly']")
    private WebElement monthlyPrice;

    public BasketPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isHeaderDisplayed() {
        return elementUtil.isElementDisplayed(basketHeader);
    }

    public String getStartPrice() {
        return elementUtil.getElementText(startPrice);
    }

    public String getMonthlyPrice() {
        return elementUtil.getElementText(monthlyPrice);
    }
}
