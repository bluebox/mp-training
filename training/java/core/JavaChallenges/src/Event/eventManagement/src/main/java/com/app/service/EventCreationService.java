package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.EventCreation;
import com.app.repository.EventCreationRepository;

@Service
@Transactional
public class EventCreationService {

	private EventCreationRepository eventCreationRepository;
	
	@Autowired
	public EventCreationService(EventCreationRepository eventCreationRepository) {
		this.eventCreationRepository = eventCreationRepository;
	}
	
//	create event
	public boolean createEvent(EventCreation event) throws Exception {
		boolean isEventCreated=false;
		int res = eventCreationRepository.createEvent(event);
		if(res > 0) {
			isEventCreated=true;
		}
		return isEventCreated;
	}
	
//	update event
	public boolean updateEvent(EventCreation event) throws Exception {
		boolean isEventUpdated=false;
		int res = eventCreationRepository.updateEvent(event);
		if(res > 0) {
			isEventUpdated=true;
		}
		return isEventUpdated;
	}
	
//	cancle event
	public boolean cancleEvent(int event_id) throws Exception {
		boolean isEventCanclled=false;
		int res = eventCreationRepository.cancleEvent(event_id);
		if(res > 0) {
			isEventCanclled=true;
		}
		return isEventCanclled;
	}
//	Get All Events
	public List<EventCreation> getAllEvents() throws Exception {
//		boolean isEventsThere=false;
		List<EventCreation> events = eventCreationRepository.getAllEvents();
//		if(events != null) {
//			isEventsThere=true;
//		}
		return events;
	}
	
//	Get Event By ID
	public EventCreation getEventById(int event_id) throws Exception {
//		boolean isEventThere=false;
		EventCreation event = eventCreationRepository.getEventById(event_id);
//		if(event != null) {
//			isEventThere=true;
//		}
		return event;
	}
	
//	Delete Event By ID
	public boolean deleteEventById(int event_id) throws Exception {
		boolean isEventDeleted=false;
		int res = eventCreationRepository.deleteEventById(event_id);
		if(res > 0) {
			isEventDeleted=true;
		}
		return isEventDeleted;
	}
	
}
