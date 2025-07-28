package com.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.enums.EventStatus;
import com.app.model.EventCreation;
import com.app.repository.EventCreationRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;

@Service
@Transactional
@Slf4j
public class EventCreationService {

	private EventCreationRepository eventCreationRepository;

	@Autowired
	public EventCreationService(EventCreationRepository eventCreationRepository) {
		this.eventCreationRepository = eventCreationRepository;
	}

//	create event
	public boolean createEvent(EventCreation event) throws Exception {
		boolean isEventCreated = false;
		int res = eventCreationRepository.createEvent(event);
		if (res > 0) {
			isEventCreated = true;
		}
		return isEventCreated;
	}

//	update event
	public boolean updateEvent(EventCreation event) throws Exception {
		boolean isEventUpdated = false;
		validateOneDayBefore(event.getStartDate());
		int res = eventCreationRepository.updateEvent(event);
		if (res > 0) {
			isEventUpdated = true;
		}
		return isEventUpdated;
	}

//	cancle event
	public boolean cancleEvent(int eventId) throws Exception {
		boolean isEventCanclled = false;
		EventCreation event = eventCreationRepository.getEventById(eventId);
		validateOneDayBefore(event.getStartDate());
		int res = eventCreationRepository.cancleEvent(eventId);
		if (res > 0) {
			isEventCanclled = true;
		}
		return isEventCanclled;
	}

//	Get All Events
	public List<EventCreation> getAllEvents() throws Exception {
		List<EventCreation> events = eventCreationRepository.getAllEvents();
		return events;
	}

//	Get Event By ID
	
	public EventCreation getEventById(int eventId) throws Exception {
	    EventCreation event = eventCreationRepository.getEventById(eventId);
	    if (event.getStatus() == EventStatus.ACTIVE && event.getEndDate().isBefore(LocalDateTime.now())) {
	        eventCreationRepository.updateEventStatus(eventId, EventStatus.FINISHED.getCode());
	        event.setStatus(EventStatus.FINISHED);
	        log.info("Event ID  auto-updated to FINISHED", eventId);
	    }
	    return event;
	}


//	Delete Event By ID
	public boolean deleteEventById(int eventId) throws Exception {
		boolean isEventDeleted = false;
		int res = eventCreationRepository.deleteEventById(eventId);
		if (res > 0) {
			isEventDeleted = true;
		}
		return isEventDeleted;
	}

	private void validateOneDayBefore(LocalDateTime localDateTime) throws Exception {
		LocalDate startDate = localDateTime.toLocalDate();
		LocalDate today = LocalDate.now();
		if (!startDate.isAfter(today.plusDays(1))) {
			throw new Exception("Action not allowed within 1 day before the event start date!");
		}
	}

	public List<EventCreation> getEventsByUserStatus(int userId, String status) throws Exception {
		return eventCreationRepository.getEventsByUserStatus(userId, status);
	}

	public List<EventCreation> getActiveEvents() {
		return eventCreationRepository.getActiveEvents();
	}
	
	public void updateFinishedEvents() {
	    List<EventCreation> activeEvents = eventCreationRepository.getActiveEvents();
	    LocalDateTime now = LocalDateTime.now();

	    for (EventCreation event : activeEvents) {
	        if (event.getEndDate().isBefore(now)) {
	            eventCreationRepository.updateEventStatus(event.getEventId(), EventStatus.FINISHED.getCode());
	            log.info("Event ID marked as FINISHED", event.getEventId());
	        }
	    }
	}
	
	

	@Scheduled(cron = "0 * * * * *") 
	public void scheduledEventStatusUpdate() {
	    updateFinishedEvents();
	}



}