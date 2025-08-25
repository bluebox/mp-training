package com.EventManagement.EMS_Backend.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.EventManagement.EMS_Backend.DAO.AdminDAO;
import com.EventManagement.EMS_Backend.Model.ModelEvent;



@Service
public class AdminService {

	@Autowired
	private AdminDAO admindao;
	
	 public int addEvent(String Userid, ModelEvent event) {
		 return admindao.addevent(Userid, event);
	}
	 
		public int updateEvent(String userid, ModelEvent event) {
		return admindao.updateevent(userid, event);
		
		}
		public List<ModelEvent> Viewllevents(){
			return admindao.viewallevents();
		}
		public int deleteEvent(int eventid) {
			return admindao.deleteevent(eventid);
		} 
}
