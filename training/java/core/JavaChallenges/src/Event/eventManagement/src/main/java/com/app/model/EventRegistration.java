package com.app.model;

import java.time.LocalDateTime;

import com.app.enums.RegistrationStatus;

import lombok.Data;

@Data
public class EventRegistration {
	int user_id;
	int event_id;
	RegistrationStatus status;
	int registered_by;
	LocalDateTime registered_at;
	int updated_by;
	LocalDateTime updated_at;
	

}
