package com.spring.gym.beans;

import java.util.Date;

import lombok.Data;

@Data
public class DateRangeRequest {
    private Date startDate;
    private Date endDate;
}
