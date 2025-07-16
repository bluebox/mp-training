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
		validateOneDayBefore(eventRepo.getEventById(er.getEventId()).getStartDate());
		boolean participantsUpdated = eventRepo.increaseParticipantsCount(er.getEventId(), event.getEventCapacity(),
				event.getParticipantCount());
		int rowsAffected=0;
		if(participantsUpdated)
		{
			rowsAffected=repo.registerEvent(er);
		}
		return rowsAffected>0;
	}

	public boolean updateEventRegistration(EventRegistration er) throws Exception {

		validateOneDayBefore(eventRepo.getEventById(er.getEventId()).getStartDate());
		return repo.updateRegistration(er)>0;
	}

	public boolean updateAttendanceOfUser(EventRegistration er) throws Exception {

		return repo.updateRegistration(er)>0;
	}

//	public boolean cancelRegistration(int event_id) throws Exception {
//		EventCreation event = eventRepo.getEventById(event_id);
//		validateOneDayBefore(event.getStartDate());
//		return eventRepo.decreaseParticipantsCount(event_id, event.getParticipantCount());
//	}

	public List<User> getUsersByRegistrationStatus(int eventId,String status) throws Exception {
		return repo.getUsersByRegistrationStatus(eventId,status);
	}



	private void validateOneDayBefore(LocalDateTime localDateTime) throws Exception {
		LocalDate startDate = localDateTime.toLocalDate();
		LocalDate today = LocalDate.now();
		if (!startDate.isAfter(today.plusDays(1))) {
			throw new Exception("Action not allowed within 1 day before the event start date");
		}
	}
}
