package org.automation.framework.models;

import java.math.BigDecimal;

import static org.automation.framework.utils.PriceParser.parsePrice;

public class Product {
    String name;
    private BigDecimal startPrice;
    private BigDecimal monthlyPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(String monthlyPrice) {
        this.monthlyPrice = parsePrice(monthlyPrice);
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = parsePrice(startPrice);
    }
}
