package com.app.model;

import com.app.enums.EventStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventCreation {

    private int eventId;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String venue;
    private String eventOrganization;
    private int eventCapacity;
    private int participantCount;

    @JsonProperty("event_status")
    private EventStatus status;

    private int createdBy;
    private LocalDateTime createdAt;
    private Integer updatedBy;
    private LocalDateTime updatedAt;


}
