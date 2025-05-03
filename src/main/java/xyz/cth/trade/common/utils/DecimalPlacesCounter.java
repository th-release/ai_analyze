package xyz.cth.trade.common.utils;

import java.math.BigDecimal;

public class DecimalPlacesCounter {
    public static int run(double value) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value)).stripTrailingZeros();
        String valueStr = bigDecimal.toPlainString();

        int decimalIndex = valueStr.indexOf('.');
        if (decimalIndex == -1) {
            return 0;
        }

        return valueStr.length() - decimalIndex - 1;
    }
}
