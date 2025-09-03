
package com.lms.web.service.impl;



import com.lms.web.config.HolidayConfig;
import com.lms.web.exceptions.ServiceException;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class WorkdayService {

    private final HolidayConfig holidayConfig;
    private final Set<DayOfWeek> weekendDays;

    public WorkdayService(HolidayConfig holidayConfig,
                          @Value("${lms.weekend.days:SATURDAY,SUNDAY}") String weekendCsv) {
        this.holidayConfig = holidayConfig;
        this.weekendDays = Stream.of(weekendCsv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(DayOfWeek::valueOf)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(DayOfWeek.class)));
    }

    public boolean isWeekend(LocalDate date) {
     	if(date==null) {
    		throw new ServiceException("Date cannot be Null");
    	}
        return weekendDays.contains(date.getDayOfWeek());
    }

    public boolean isHoliday(LocalDate date) {
     	if(date==null) {
    		throw new ServiceException("Date cannot be Null");
    	}
        return holidayConfig.getHolidayDates().contains(date);
    }

    public boolean isWorkingDay(LocalDate date) {
     	if(date==null) {
    		throw new ServiceException("Date cannot be Null");
    	}
        return !isWeekend(date) && !isHoliday(date);
    }

    public long countWorkingDays(LocalDate start, LocalDate end) {
    	if(start==null || end==null) {
    		throw new ServiceException("Start and End dates cannot be Null");
    	}
        if (end.isBefore(start)) return 0;
        long count = 0;
        for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
            if (isWorkingDay(d)) count++;
        }
        return count;
    }

    public LocalDate previousWorkingDay(LocalDate date) {
    	if(date==null) {
    		throw new ServiceException("Date cannot be Null");
    	}
        LocalDate d = date.minusDays(1);
        while (!isWorkingDay(d)) {
            d = d.minusDays(1);
        }
        return d;
    }

    public LocalDate nextWorkingDay(LocalDate date) {
    	if(date==null) {
    		throw new ServiceException("Date cannot be Null");
    	}
        LocalDate d = date.plusDays(1);
        while (!isWorkingDay(d)) {
            d = d.plusDays(1);
        }
        return d;
    }
}
