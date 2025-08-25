package com.EventManagement.EMS_Backend.Model;

import lombok.Data;

@Data
public class ModelAverageRating {
    private int eventId;
    private int totalFeedbacks;
    private double avgRating;

}
