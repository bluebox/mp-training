package com.EventManagement.EMS_Backend.Model;

import lombok.Data;

@Data
public class ModelEventReport {
    private int eventId;
    private String eventName;
    private int totalRegistrations;

}
