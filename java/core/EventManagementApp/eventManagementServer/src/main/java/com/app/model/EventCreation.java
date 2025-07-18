package com.app.model;

import com.app.enums.EventStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EventCreation {

    @NotNull(message="Event Id not to be Null!")
	private int eventId;
	@NotBlank(message="Event name not to be Null!")
	@Size(min = 3, message = "Event name has atleast 3 characters")
	private String name;
    @NotNull(message="Event Start date not to be Null!")
	private LocalDateTime startDate;
    @NotNull(message="Event End date not to be Null!")
	private LocalDateTime endDate;
	@NotBlank(message="Event venue not to be Null!")
	@Size(min = 3, message = "Event venue has atleast 3 characters")
	private String venue;
	@NotBlank(message="Event organization name not to be Null!")
	@Size(min = 2, message = "Event organization name has atleast 2 characters")
	private String eventOrganizer;
    @NotNull(message="Event capacity not to be Null!")
	private int eventCapacity;
	private int participantCount;
	 @JsonProperty("event_status")
	 private EventStatus status;
//	private EventStatus event_status;
    @NotNull(message="Event Created By not to be Null!")
	private int createdBy;
	private LocalDateTime createdAt;
	private int updatedBy;
	private LocalDateTime updatedAt;
	

}
