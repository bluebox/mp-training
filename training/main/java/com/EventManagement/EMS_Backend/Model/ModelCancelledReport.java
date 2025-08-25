package com.EventManagement.EMS_Backend.Model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ModelCancelledReport {
    private String userId;
    private int eventId;
    private String eventName;
    private String status;
    private Timestamp modifiedAt;

}
