package com.app.model;

import com.app.enums.EventStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class EventCreation {

    private int event_id;
    private String name;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String venue;
    private String event_organization;
    private int event_capacity;
    private int participant_count;

    @JsonProperty("event_status")
    private EventStatus status;

    private int created_by;
    private LocalDateTime created_at;
    private Integer updated_by;
    private LocalDateTime updated_at;
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}
	public LocalDateTime getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getEvent_organization() {
		return event_organization;
	}
	public void setEvent_organization(String event_organization) {
		this.event_organization = event_organization;
	}
	public int getEvent_capacity() {
		return event_capacity;
	}
	public void setEvent_capacity(int event_capacity) {
		this.event_capacity = event_capacity;
	}
	public int getParticipant_count() {
		return participant_count;
	}
	public void setParticipant_count(int participant_count) {
		this.participant_count = participant_count;
	}
	public EventStatus getStatus() {
		return status;
	}
	public void setStatus(EventStatus status) {
		this.status = status;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public Integer getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
}
