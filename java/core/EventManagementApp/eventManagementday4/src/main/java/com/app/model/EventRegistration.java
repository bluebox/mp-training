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

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }

    public int getEvent_id() { return event_id; }
    public void setEvent_id(int event_id) { this.event_id = event_id; }

    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }

    public int getRegistered_by() { return registered_by; }
    public void setRegistered_by(int registered_by) { this.registered_by = registered_by; }

    public LocalDateTime getRegistered_at() { return registered_at; }
    public void setRegistered_at(LocalDateTime registered_at) { this.registered_at = registered_at; }

    public Integer getUpdated_by() { return updated_by; }
    public void setUpdated_by(Integer updated_by) { this.updated_by = updated_by; }

    public LocalDateTime getUpdated_at() { return updated_at; }
    public void setUpdated_at(LocalDateTime updated_at) { this.updated_at = updated_at; }
}
