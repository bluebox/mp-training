package com.EventManagement.EMS_Backend.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.EventManagement.EMS_Backend.DAO.FacultyDAO;
import com.EventManagement.EMS_Backend.Exceptions.EventnotRegistered;
import com.EventManagement.EMS_Backend.Model.Attendenceenum;
import com.EventManagement.EMS_Backend.Model.ModelEvent;

import java.util.List;

@Service       
@Scope("singleton") 
public class FacultyService {

    @Autowired
    private FacultyDAO facultyDAO;

    public int addEvent(ModelEvent event) {
        return facultyDAO.addEvent(event);
    }

    public List<ModelEvent> getAllEvents() {
        return facultyDAO.getAllEvents();
    }

    public ModelEvent getEventById(int id) {
        return facultyDAO.getEventById(id);
    }

    public int updateEvent(ModelEvent event) {
        return facultyDAO.updateEvent(event);
    }

    public int deleteEvent(int id) {
        return facultyDAO.deleteEvent(id);
    }

    public String approveEvent(int eventId,String userid) {
        return facultyDAO.approveEvent(eventId,userid);
    }
    public int markattendence(String userid,int eventid,Attendenceenum presence,String Facultyid) throws EventnotRegistered {
    	return facultyDAO.markAttendance( userid, eventid, presence, Facultyid);
    	
    }
    public List<ModelEvent> getpendingevents(){
    	return facultyDAO.getpendingevents();
    }
}


