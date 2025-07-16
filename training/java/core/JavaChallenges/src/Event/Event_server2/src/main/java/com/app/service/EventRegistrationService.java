package com.app.service;

import com.app.model.EventCreation;
import com.app.model.EventRegistration;
import com.app.model.User;
import com.app.repository.EventCreationRepository;
import com.app.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventRegistrationService {

	@Autowired
	private EventRegistrationRepository repo;

	@Autowired
	private EventCreationRepository eventRepo;

	public boolean registrationForEvent(EventRegistration er) throws Exception {
		EventCreation event = eventRepo.getEventById(er.getEventId());
		validateOneDayBefore(event.getStartDate());
		boolean p = eventRepo.increaseParticipantsCount(er.getEventId(), event.getEventCapacity(),
				event.getParticipantCount());
		boolean r = p ? repo.registerEvent(er) : false;
		return r;
	}

	public boolean updateEventRegistration(EventRegistration er) throws Exception {
		EventCreation event = eventRepo.getEventById(er.getEventId());
		validateOneDayBefore(event.getStartDate());
		return repo.updateRegistration(er);
	}

	public boolean updateAttendanceOfUser(String status, int event_id, int user_id, int updated_by) throws Exception {

		return repo.updateAttendanceOfUser(status, event_id, user_id, updated_by);
	}

	public boolean cancelRegistration(int event_id) throws Exception {
		EventCreation event = eventRepo.getEventById(event_id);
		validateOneDayBefore(event.getStartDate());
		return eventRepo.decreaseParticipantsCount(event_id, event.getParticipantCount());
	}

	public List<User> eventAttendents(int event_id) {
		return repo.getAttendants(event_id);
	}

	public List<User> eventAbsenties(int event_id) {
		return repo.getAbsenties(event_id);
	}

	public List<User> noOfUsersCancelledEventRegistration(int event_id) {
		return repo.getCancelledRegistrations(event_id);
	}

	public List<User> getEventRegistredUsers(int event_id) {
		return repo.getEventRegistredUsers(event_id);
	}

	private void validateOneDayBefore(LocalDateTime localDateTime) throws Exception {
		LocalDate startDate = localDateTime.toLocalDate();
		LocalDate today = LocalDate.now();
		if (!startDate.isAfter(today.plusDays(1))) {
			throw new Exception("Action not allowed within 1 day before the event start date");
		}
	}
}
