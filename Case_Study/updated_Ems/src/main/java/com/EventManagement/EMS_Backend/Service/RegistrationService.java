package com.EventManagement.EMS_Backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.EventManagement.EMS_Backend.DAO.RegistrationDAO;
import com.EventManagement.EMS_Backend.Model.ModelUser;
import com.EventManagement.EMS_Backend.Model.RegistrationEnum;
import com.EventManagement.EMS_Backend.Model.UserEnum;
@Service
public class RegistrationService {
	@Autowired
	private RegistrationDAO registrationdao;
	public int userRegister(ModelUser user) {
		return registrationdao.userregister(user);
	}
	public int eventRegister(UserEnum usertype,String userid,int eventid) {
	return registrationdao.eventregister(usertype, userid, eventid);
	
	}
	public List<ModelUser> ViewallregStudents(int eventid){
		return registrationdao.viewallregStudents( eventid);
	}
	public int cancelRegister(String userid,int eventid) {
		return registrationdao.cancelregister(userid,eventid);
	}
	public ModelUser findbyemail(String email) {
		return registrationdao.findbyemail(email);
	}
	
	public List<ModelUser> getReguserstomarkattd(int eventid) {
	return registrationdao.getReguserstomarkattd(eventid);
	
	}
}
