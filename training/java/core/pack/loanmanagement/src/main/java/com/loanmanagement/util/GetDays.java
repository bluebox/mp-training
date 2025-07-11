package com.loanmanagement.util;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class GetDays {
    public static int betweenDays(Date start) {
        LocalDate startDate;
        if (start instanceof java.sql.Date) {
            startDate = ((java.sql.Date) start).toLocalDate();
        } else {
            startDate = start.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        }

        LocalDate dueDate = LocalDate.now();

        return (int) ChronoUnit.DAYS.between(startDate, dueDate);
    }
}
