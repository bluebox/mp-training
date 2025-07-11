package com.app.model;

import java.time.LocalDateTime;

import com.app.enums.EventStatus;

import lombok.Data;

@Data
public class EventCreation {
	private int event_id;
	private String name;
	private LocalDateTime start_date;
	private LocalDateTime end_date;
	private String venue;
	private String event_organization;
	private int event_capacity;
	private int participant_count;
	private EventStatus status;
	private int created_by;
	private LocalDateTime created_at;
	private int updated_by;
	private LocalDateTime updated_at;
}