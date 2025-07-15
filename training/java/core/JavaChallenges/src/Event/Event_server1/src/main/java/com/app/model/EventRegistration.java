package com.app.model;

import java.time.LocalDateTime;

import com.app.enums.RegistrationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EventRegistration {
    private int user_id;
    private int event_id;

    @JsonProperty("registration_status")
    private RegistrationStatus status;

    private int registered_by;
    private LocalDateTime registered_at;
    private Integer updated_by;
    private LocalDateTime updated_at;
}
