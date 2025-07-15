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
        validateOneDayBefore(event.getStart_date());
        return repo.updateEvent(event);
    }

    public boolean cancelEvent(int event_id) throws Exception {
        EventCreation event = repo.getEventById(event_id);
        validateOneDayBefore(event.getStart_date());
        return repo.cancelEvent(event_id);
    }

    public boolean deleteEventById(int event_id) throws Exception {
        EventCreation event = repo.getEventById(event_id);
        validateOneDayBefore(event.getStart_date());
        return repo.deleteEventById(event_id);
    }

    public List<EventCreation> getAllEvents() {
        return repo.getAllEvents();
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
    public List<EventCreation> getEventsForUser(int userId) {
        return repo.findAllWithUserStatus(userId);
    }

}