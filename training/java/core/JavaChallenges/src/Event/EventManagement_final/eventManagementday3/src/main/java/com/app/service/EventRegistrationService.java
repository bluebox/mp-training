package com.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.EventCreation;
import com.app.model.EventRegistration;
import com.app.model.User;
import com.app.repository.EventCreationRepository;
import com.app.repository.EventRegistrationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EventRegistrationService {

	@Autowired
	private EventRegistrationRepository repo;

	@Autowired
	private EventCreationRepository eventRepo;

	public boolean registrationForEvent(EventRegistration er) throws Exception {

		int rowsAffected = 0;

		EventCreation ec = eventRepo.getEventById(er.getEventId());
		if (ec.getParticipantCount() + 1 <= ec.getEventCapacity()) {
			rowsAffected = repo.registerEvent(er);
			eventRepo.updateParticipantsCount(ec.getParticipantCount()+1,er.getEventId());
		}
		return rowsAffected > 0;
	}

	public boolean updateEventRegistration(EventRegistration er) throws Exception {
		int rowsAffected=0;
		EventCreation ec = eventRepo.getEventById(er.getEventId());
		if(String.valueOf(er.getStatus())=="CANCELLED")
		{
			validateOneDayBefore(eventRepo.getEventById(er.getEventId()).getStartDate());
			rowsAffected=repo.updateRegistration(er);
			eventRepo.updateParticipantsCount(ec.getParticipantCount()-1,er.getEventId());
		}
		log.info(er.getStatus().toString());
		return rowsAffected>0;
	}

	public boolean updateAttendanceOfUser(EventRegistration er) throws Exception {

		return repo.updateRegistration(er) > 0;
	}

	public List<User> getUsersByRegistrationStatus(int eventId, String status) throws Exception {
		return repo.getUsersByRegistrationStatus(eventId, status);
	}

	private void validateOneDayBefore(LocalDateTime localDateTime) throws Exception {
		LocalDate startDate = localDateTime.toLocalDate();
		LocalDate today = LocalDate.now();
		if (!startDate.isAfter(today.plusDays(1))) {
			throw new Exception("Action not allowed within 1 day before the event start date");
		}
	}
}
