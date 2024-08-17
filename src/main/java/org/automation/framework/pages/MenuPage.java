package org.automation.framework.pages;

import org.automation.framework.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MenuPage {
    private final ElementUtil elementUtil;

    @FindBy(xpath = "//button[contains(@class,'menu-dropdown-item') and text()='Urządzenia']")
    private WebElement devicesMenu;
    @FindBy(xpath = "//li[./button[text()=\"Urządzenia\"]]//li[contains(@class,'group menu-dropdown')]/span")
    private List<WebElement> devicesSubMenuGroups;
    private final String elementFromMenu = ".//div[contains(@class,'menu-dropdown-submenu')]//li[.//p[text()='%s']]//a[.//span[text()='%s']]";

    public MenuPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectDevicesFromMenu() {
        elementUtil.moveToElement(devicesMenu);
    }

    public List<String> getSubMenuGroupsTitle() {
        List<String> groupsTitles = new ArrayList<>();
        List<WebElement> groupTitlesList = elementUtil.getWebElements(devicesSubMenuGroups);
        groupTitlesList.forEach(element -> {
            String text = element.getText();
            groupsTitles.add(text);
        });

        return groupsTitles;
    }

    public WebElement getClickableWebElementWithProvidedXpath(String xPath) {
        WebElement e = elementUtil.getWebElement(xPath);
        elementUtil.waitForElementClickable(e);
        return e;
    }

    public void clickOnSelectItemFromMenu(String menuOption, String menuGroup) {
        String xPath = String.format(elementFromMenu, menuGroup, menuOption);
        WebElement element = getClickableWebElementWithProvidedXpath(xPath);
        elementUtil.clickByJavaScript(element);
    }

}
