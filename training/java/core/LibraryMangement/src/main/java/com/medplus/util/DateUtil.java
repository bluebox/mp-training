package com.medplus.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String format(LocalDate date) {
        return date != null ? formatter.format(date) : null;
    }

    public static LocalDate parse(String dateStr) {
        return dateStr != null && !dateStr.isEmpty() ? LocalDate.parse(dateStr, formatter) : null;
    }

    public static boolean isOverdue(LocalDate returnDate) {
        return returnDate != null && returnDate.isBefore(LocalDate.now());
    }
}
