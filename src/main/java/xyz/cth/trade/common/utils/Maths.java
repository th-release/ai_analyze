package xyz.cth.trade.common.utils;

import java.text.DecimalFormat;
import java.util.List;

public class Maths {
    public static Double max(double... numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0.0;
        }

        double maxValue = numbers[0];
        for (double num : numbers) {
            if (num > maxValue) {
                maxValue = num;
            }
        }
        return maxValue;
    }

    public static double toFixed(double value, int decimalPlaces) {
        DecimalFormat df = new DecimalFormat("#." + "0".repeat(Math.max(0, decimalPlaces)));
        return Double.parseDouble(df.format(value));
    }

    public static double findLowest(List<Double> prices, int startIndex, int endIndex) {
        double lowest = Double.MAX_VALUE;
        startIndex = Math.max(0, startIndex);
        endIndex = Math.min(prices.size() - 1, endIndex);

        for (int i = startIndex; i <= endIndex; i++) {
            lowest = Math.min(lowest, prices.get(i));
        }
        return lowest;
    }

    public static double findHighest(List<Double> prices, int startIndex, int endIndex) {
        double highest = Double.MIN_VALUE;
        startIndex = Math.max(0, startIndex);
        endIndex = Math.min(prices.size() - 1, endIndex);

        for (int i = startIndex; i <= endIndex; i++) {
            highest = Math.max(highest, prices.get(i));
        }
        return highest;
    }
}
