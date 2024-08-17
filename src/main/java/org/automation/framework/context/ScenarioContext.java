package org.automation.framework.context;

import org.automation.framework.models.Product;

public class ScenarioContext {
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
