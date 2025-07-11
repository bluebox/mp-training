package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.EventRegistration;
import com.app.model.User;
import com.app.repository.EventRegistrationRepository;

@Service
public class EventRegistrationService 
{
	@Autowired
	private final EventRegistrationRepository ev;
	
	public EventRegistrationService(EventRegistrationRepository ev)
	{
		this.ev=ev;
	}
	
	public boolean registrationForEvent(EventRegistration er) throws Exception
	{
		boolean isRegistred=false;
		int rows=ev.registrationForEvent(er);
		if(rows>0)
		{
			isRegistred=true;
		}
		return isRegistred;
	}
	
	public boolean updateEventRegistration(EventRegistration er) throws Exception
	{
		boolean isUpdated=false;
		int rows=ev.updateEventRegistration(er);
		if(rows>0)
		{
			isUpdated=true;
		}
		return isUpdated;
	}
	
	public List<User> eventAttendents(int event_id) throws Exception
	{
		return ev.eventAttendents(event_id);
	}
	
	public List<User> eventAbsenties(int event_id) throws Exception
	{
		return ev.eventAbsenties(event_id);
	}
	
	public List<User> noOfUsersCancelledEventRegistration(int event_id) throws Exception
	{
		return ev.noOfUsersCancelledEventRegistration(event_id);
	}

}
