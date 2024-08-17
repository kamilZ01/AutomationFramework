package org.automation.framework.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PriceParser {

    public static BigDecimal parsePrice(String price) {
        NumberFormat format = NumberFormat.getInstance(Locale.of("pl", "PL"));
        Number number;
        try {
            number = format.parse(price.replace("zł", "").trim());
        } catch (ParseException e) {
            throw new RuntimeException("An error occurred while parsing price.");
        }
        return BigDecimal.valueOf(number.doubleValue());
    }
}
