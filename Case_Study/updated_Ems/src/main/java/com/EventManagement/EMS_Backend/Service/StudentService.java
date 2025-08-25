package com.EventManagement.EMS_Backend.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.EventManagement.EMS_Backend.DAO.StudentDAO;
import com.EventManagement.EMS_Backend.Model.ModelEvent;


@Service      
@Scope("singleton")  
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    public int registerForEvent(int userId, int eventId) {
        return studentDAO.registerForEvent(userId, eventId);
    }

    public String getRegistrationStatus(int userId, int eventId) {
        return studentDAO.getRegistrationStatus(userId, eventId);
    }

    public int cancelRegistration(int userId, int eventId) {
        return studentDAO.cancelRegistration(userId, eventId);
    }
    public List<ModelEvent> viewallevents(String userid){
    	return studentDAO.viewallevents(userid);
    }
    public String getuserid(String email) {
    	return studentDAO.getuserid(email);
    }
    public int feedback(int eventid,String userid,String description,int rating) {
    	return studentDAO.feedback(eventid, userid, description, rating);
}
}
