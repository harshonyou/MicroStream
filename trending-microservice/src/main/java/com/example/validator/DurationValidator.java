package com.example.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class DurationValidator {

    // Regex pattern to match duration strings in the format of number followed by a 'd', 'h', or 'm'.
    private static final Pattern DURATION_PATTERN = Pattern.compile("(\\d+)([dhm])");

    public static boolean isValidDuration(String duration) {
        if (duration == null || duration.trim().isEmpty()) {
            return false;
        }

        Matcher matcher = DURATION_PATTERN.matcher(duration);
        int lastIndex = 0;
        char lastUnit = '0';

        while (matcher.find()) {
            if (matcher.start() != lastIndex) {
                return false;
            }

            lastIndex = matcher.end();

            int value = Integer.parseInt(matcher.group(1));
            char currentUnit = matcher.group(2).charAt(0);

            if (!isValidValueForUnit(value, currentUnit) || currentUnit <= lastUnit) {
                return false;
            }

            lastUnit = currentUnit;
        }

        return lastIndex == duration.length();
    }


    private static boolean isValidValueForUnit(int value, char unit) {
        int maxDays = 365, maxHours = 23, maxMinutes = 59;

        return switch (unit) {
            case 'd' -> value >= 0 && value <= maxDays;
            case 'h' -> value >= 0 && value <= maxHours;
            case 'm' -> value >= 0 && value <= maxMinutes;
            default -> false;
        };
    }

}
