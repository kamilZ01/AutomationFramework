package org.automation.framework.pages;


import org.automation.framework.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final ElementUtil elementUtil;

    @FindBy(xpath = "(//a[@id=\"back-top\"]//*[name()='svg'])[1]")
    private WebElement logo;
    @FindBy(xpath = "//button[@id='didomi-notice-agree-button' and contains(@aria-label, 'Zaakceptuj i zamknij')]")
    private WebElement acceptCookies;
    @FindBy(xpath = "//nav[@id='main-menu']/following-sibling::div/a[@title='Koszyk']/div")
    private WebElement basketItemCount;

    public HomePage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isLogoDisplayed() {
        return elementUtil.isElementDisplayed(logo);
    }

    public void clickAcceptCookies() {
        elementUtil.click(acceptCookies);
    }

    public int getBasketItemCount() {
        return Integer.parseInt(elementUtil.getElementText(basketItemCount));
    }
}
