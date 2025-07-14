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
        EventCreation event = eventRepo.getEventById(er.getEvent_id());
        validateOneDayBefore(event.getStart_date());
        return repo.registerEvent(er);
    }

    public boolean updateEventRegistration(EventRegistration er) throws Exception {
        EventCreation event = eventRepo.getEventById(er.getEvent_id());
        validateOneDayBefore(event.getStart_date());
        return repo.updateRegistration(er);
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

    private void validateOneDayBefore(LocalDateTime localDateTime) throws Exception {
        LocalDate startDate = localDateTime.toLocalDate();
        LocalDate today = LocalDate.now();
        if (!startDate.isAfter(today.plusDays(1))) {
            throw new Exception("Action not allowed within 1 day before the event start date");
        }
    }
}
