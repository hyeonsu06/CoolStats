package io.github.hyeonsu06.coolstats;

public class NumberFormatter {
    public static String format1(double number) {
        if (number >= 1000000000) return String.format("%.2fB", number / 1000000000.0);
        if (number >= 1000000) return String.format("%.2fM", number / 1000000.0);
        if (number >= 1000) return String.format("%.2fk", number / 1000.0);
        return String.valueOf(number);
    }

    public static String format2(double number) {
        return String.format("%,d", number);
    }
}