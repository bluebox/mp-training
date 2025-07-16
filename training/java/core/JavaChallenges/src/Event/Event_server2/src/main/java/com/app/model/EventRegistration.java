package com.app.model;

import java.time.LocalDateTime;

import com.app.enums.RegistrationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EventRegistration {
    private int userId;
    private int eventId;

    @JsonProperty("registration_status")
    private RegistrationStatus status;

    private int registeredBy;
    private LocalDateTime registeredAt;
    private Integer updatedBy;
    private LocalDateTime updatedAt;
}
