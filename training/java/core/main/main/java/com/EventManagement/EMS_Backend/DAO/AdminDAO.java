package com.EventManagement.EMS_Backend.DAO;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.EventManagement.EMS_Backend.Model.EventApproveStatus;
import com.EventManagement.EMS_Backend.Model.EventCategory;
import com.EventManagement.EMS_Backend.Model.EventStatus;
import com.EventManagement.EMS_Backend.Model.ModelEvent;



@Repository
public class AdminDAO {
	
	@Autowired 
	private EventRowMapper eventrowmapper;

	@Autowired 
	private JdbcTemplate jdbctemplate;
	public int addevent(String Userid, ModelEvent event) {
		String query="insert into event(EventId,Eventname,Eventdatetime,Eventvenue,Eventcategory,Eventstatus,Approved_by,Approved_time,created_by,created_time,EventDescription,Status,Modified_by,Modified_at) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 int res=jdbctemplate.update(query,event.getEventid(),event.getEventname(),event.getEventdatetime(),event.getEventvenue(),event.getEventcategory().getType(),event.getEventstatus().getType(),null,null,Userid,LocalDateTime.now(),event.getEventDescription(),event.getStatus().getType(),null,null);
	
	return res;
	}
	
	public int updateevent(String userid, ModelEvent event) {
		ModelEvent oldevent=findEventbyid(event.getEventid());
		String querylog="insert into event_log(EventId,Eventname,Eventdatetime,Eventvenue,Eventcategory,Eventstatus,Approved_by,Approved_time,created_by,created_time,EventDescription,Status,Modified_by,Modified_at) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 jdbctemplate.update(querylog,oldevent.getEventid(),oldevent.getEventname(),oldevent.getEventdatetime(),oldevent.getEventvenue(),oldevent.getEventcategory().getType(),oldevent.getEventstatus().getType(),null,null,oldevent.getCreated_by(),oldevent.getCreated_time(),oldevent.getEventDescription(),oldevent.getStatus().getType(),oldevent.getModified_by(),oldevent.getModified_at());

		
		String query="update event set Eventname=?,Eventdatetime=?,Eventvenue=?,Eventcategory=?,Eventstatus=?,EventDescription=?,Status=?,Modified_by=?,Modified_at=? where Eventid=?";
		int res=jdbctemplate.update(query,event.getEventname(),event.getEventdatetime(),event.getEventvenue(),event.getEventcategory().getType(),event.getEventstatus().getType(),event.getEventDescription(),event.getStatus().getType(),userid,LocalDateTime.now(),event.getEventid());
	return res;
	
	}
	
	public List<ModelEvent> viewallevents(){
		String sql="select Eventid,Eventname,Eventdatetime,Eventvenue,Eventcategory,Eventstatus,Approved_by,Approved_time,created_by,created_time,EventDescription,Status,Modified_by,Modified_at from event";
		
	    List<ModelEvent> events = jdbctemplate.query(sql,eventrowmapper);
	    return events;

		
	}
	
	public int deleteevent(int eventid) {
		ModelEvent oldevent=findEventbyid(eventid);
		String querylog="insert into event_log(EventId,Eventname,Eventdatetime,Eventvenue,Eventcategory,Eventstatus,Approved_by,Approved_time,created_by,created_time,EventDescription,Status,Modified_by,Modified_at) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 jdbctemplate.update(querylog,oldevent.getEventid(),oldevent.getEventname(),oldevent.getEventdatetime(),oldevent.getEventvenue(),oldevent.getEventcategory().getType(),oldevent.getEventstatus().getType(),null,null,oldevent.getCreated_by(),oldevent.getCreated_time(),oldevent.getEventDescription(),oldevent.getStatus().getType(),oldevent.getModified_by(),oldevent.getModified_at());

		
		String query="update event set status=?,eventstatus=?,modified_at=? where eventid=?";
		 int res=jdbctemplate.update(query,EventStatus.INACTIVE.getType(),EventApproveStatus.CANCELLED.getType(),LocalDateTime.now(),eventid);
	return res;
	}
	public ModelEvent findEventbyid(int eventid) {
		String sql="select Eventid,Eventname,Eventdatetime,Eventvenue,Eventcategory,Eventstatus,Approved_by,Approved_time,created_by,created_time,EventDescription,Status,Modified_by,Modified_at from event where eventid=?";
		return jdbctemplate.queryForObject(sql, eventrowmapper,eventid);
	}

}





@Component
class EventRowMapper implements RowMapper<ModelEvent> {
	

	@Override
    public ModelEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
		ModelEvent event=new ModelEvent();
		event.setEventid(rs.getInt("Eventid"));
		event.setEventname(rs.getString("Eventname"));
		event.setEventdatetime(rs.getString("Eventdatetime"));
		event.setEventstatus(EventApproveStatus.getEventApproveStatus(rs.getString("EventStatus").toLowerCase()));
		event.setEventcategory(EventCategory.getEventCategorys(rs.getString("Eventcategory")));
		event.setEventvenue(rs.getString("Eventvenue"));
		String approved_by=rs.getString("Approved_by");
        event.setApproved_by(approved_by!=null?approved_by:"");
        Date approvedDate = rs.getDate("Approved_time");
        event.setApproved_time(approvedDate != null ? approvedDate.toString() : "");

        event.setCreated_by(rs.getString("created_by"));
        Date createdTime = rs.getDate("created_time");
      
        event.setCreated_time(createdTime != null ? createdTime.toString() : "");
        System.out.println("createdtime"+createdTime+event.getCreated_time());
        event.setEventDescription(rs.getString("EventDescription"));
        event.setStatus(EventStatus.getEventStatus(rs.getString("Status")));
        Date ModifiedTime = rs.getDate("Modified_at");
        event.setModified_at(ModifiedTime != null ? ModifiedTime.toString() : "");
        String modifiedby=rs.getString("Modified_by");
        event.setModified_by(modifiedby!=null?modifiedby.toString():"");

        return event;
    	
}
}
