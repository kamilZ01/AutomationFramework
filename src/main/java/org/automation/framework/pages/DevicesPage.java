package org.automation.framework.pages;

import org.automation.framework.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DevicesPage {
    private final ElementUtil elementUtil;

    @FindBy(xpath = "//div[contains(@class,'dt_productCards')]")
    private List<WebElement> devices;
    @FindBy(xpath = "//div[@data-qa='LST_FilterButton']")
    private WebElement filterButton;

    public DevicesPage(WebDriver driver) {
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getDevicesCount() {
        elementUtil.waitForElementClickable(filterButton);
        return elementUtil.getWebElements(devices);
    }

    public void clickOnElement(WebElement element) {
        elementUtil.moveToElement(element);
        elementUtil.click(element);
    }
}
