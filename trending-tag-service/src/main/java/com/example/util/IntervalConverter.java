package com.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntervalConverter {
    public static String convertToPostgresInterval(String duration) {
        // Regular expression to match the components of the duration
        Pattern pattern = Pattern.compile("(\\d+)([dhm])");
        Matcher matcher = pattern.matcher(duration);

        StringBuilder intervalBuilder = new StringBuilder();
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            String unit = matcher.group(2);

            switch (unit) {
                case "d" -> intervalBuilder.append(number).append(" day ");
                case "h" -> intervalBuilder.append(number).append(" hour ");
                case "m" -> intervalBuilder.append(number).append(" minute ");
            }
        }

        return intervalBuilder.toString().trim();
    }
}
