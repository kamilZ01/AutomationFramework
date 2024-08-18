package org.automation.framework.context;

import org.automation.framework.models.Product;
import org.automation.framework.utils.ConfigReader;

public class ScenarioContext {
    private Product product;
    private ConfigReader configReader;

    public ConfigReader getConfigReader() {
        return configReader;
    }

    public void setConfigReader(ConfigReader configReader) {
        this.configReader = configReader;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
