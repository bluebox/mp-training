package com.lms.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "spring")
public class HolidayConfig {

    private Map<String, String> holidays = new HashMap<>();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Map<String, LocalDate> getHolidays() {
        return holidays.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> LocalDate.parse(entry.getValue(), formatter)
            ));
    }

    public void setHolidays(Map<String, String> holidays) {
        this.holidays = holidays;
    }

    public Set<LocalDate> getHolidayDates() {
        return getHolidays().values().stream().collect(Collectors.toSet());
    }

    public boolean isHoliday(LocalDate date) {
        return getHolidays().containsValue(date);
    }
}
