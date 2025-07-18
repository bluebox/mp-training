package com.app.model;

import java.time.LocalDateTime;

import com.app.enums.RegistrationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class EventRegistration {
    
    @Min(value = 1, message = "User ID must be greater than or equal to 1")
    private int userId;

    @Min(value = 1, message = "Event ID must be greater than or equal to 1")
    private int eventId;

    @NotNull(message = "Registration status cannot be null")
    @JsonProperty("registration_status")
    private RegistrationStatus status;

    @Min(value = 1, message = "RegisteredBy ID must be greater than or equal to 1")
    private int registeredBy;

    @NotNull(message = "Registration time cannot be null")
    private LocalDateTime registeredAt;

    @Min(value = 1, message = "UpdatedBy ID must be greater than or equal to 1")
    private Integer updatedBy;

    @FutureOrPresent(message = "UpdatedAt must be a future or present time")
    private LocalDateTime updatedAt;
}
