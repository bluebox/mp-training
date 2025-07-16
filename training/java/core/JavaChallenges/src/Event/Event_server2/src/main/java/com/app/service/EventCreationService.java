package com.app.service;

import com.app.model.EventCreation;
import com.app.repository.EventCreationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class EventCreationService {

	@Autowired
	private EventCreationRepository repo;

	public boolean createEvent(EventCreation event) {
		return repo.createEvent(event);
	}

	public boolean updateEvent(EventCreation event) throws Exception {
		validateOneDayBefore(event.getStartDate());
		return repo.updateEvent(event);
	}

	public boolean cancelEvent(int event_id) throws Exception {
		EventCreation event = repo.getEventById(event_id);
		validateOneDayBefore(event.getStartDate());
		return repo.cancelEvent(event_id);
	}

	public boolean deleteEventById(int event_id) throws Exception {
		EventCreation event = repo.getEventById(event_id);
		validateOneDayBefore(event.getStartDate());
		return repo.deleteEventById(event_id);
	}

	public List<EventCreation> getAllEvents() {
		return repo.getAllEvents();
	}
	
	public List<EventCreation> getActiveEvents() {
		return repo.getActiveEvents();
	}
	
	public List<EventCreation> getCancelledEvents() {
		return repo.getCancelledEvents();
	}
	public List<EventCreation> getFinishedEvents() {
		return repo.getFinishedEvents();
	}

	public List<EventCreation> findAllEventsAttendedByUser(int user_id) {
		return repo.findAllEventsAttendedByUser(user_id);
	}

	public List<EventCreation> findAllEventsCancelledByUser(int user_id) {
		return repo.findAllEventsCancelledByUser(user_id);
	}

	public List<EventCreation> findAllEventsRegisteredByUser(int user_id) {
		return repo.findAllEventsRegisteredByUser(user_id);
	}

	public List<EventCreation> findAllEventsNotAttendedUser(int user_id) {
		return repo.findAllEventsNotAttendedUser(user_id);
	}

	public EventCreation getEventById(int event_id) {
		return repo.getEventById(event_id);
	}

	private void validateOneDayBefore(LocalDateTime localDateTime) throws Exception {
		LocalDate startDate = localDateTime.toLocalDate();
		LocalDate today = LocalDate.now();
		if (!startDate.isAfter(today.plusDays(1))) {
			throw new Exception("Action not allowed within 1 day before the event start date");
		}
	}
//    public List<EventCreation> getEventsForUser(int userId) {
//        return repo.findAllEventsAttendedByUser(userId);
//    }

}