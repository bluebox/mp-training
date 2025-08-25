package com.EventManagement.EMS_Backend.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.EventManagement.EMS_Backend.Exceptions.EventnotRegistered;
import com.EventManagement.EMS_Backend.Model.Attendenceenum;
import com.EventManagement.EMS_Backend.Model.EventApproveStatus;
import com.EventManagement.EMS_Backend.Model.EventCategory;
import com.EventManagement.EMS_Backend.Model.EventStatus;
import com.EventManagement.EMS_Backend.Model.ModelAttendence;
import com.EventManagement.EMS_Backend.Model.ModelEvent;



@Repository
public class FacultyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

  
    private final RowMapper<ModelEvent> eventRowMapper = new RowMapper<ModelEvent>() {
        @Override
        public ModelEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
            ModelEvent event = new ModelEvent();
            event.setEventid(rs.getInt("eventid"));
            event.setEventname(rs.getString("eventname"));
            event.setEventdatetime(rs.getString("eventdatetime"));
            event.setEventvenue(rs.getString("eventvenue"));
            event.setEventstatus(EventApproveStatus.getEventApproveStatus(rs.getString("eventstatus")));
            event.setEventcategory(EventCategory.getEventCategorys(rs.getString("eventcategory")));
            event.setApproved_by(rs.getString("approved_by"));
            event.setEventDescription(rs.getString("eventdescription"));
            event.setApproved_time(rs.getString("approved_time"));
            event.setCreated_by(rs.getString("created_by"));
            event.setCreated_time(rs.getString("created_time"));
            event.setStatus(EventStatus.getEventStatus(rs.getString("status")));
            event.setModified_by(rs.getString("modified_by"));
            event.setModified_at(rs.getString("modified_at"));
            return event;
        }
    };

  
    private final RowMapper<ModelAttendence> attendanceRowMapper = new RowMapper<ModelAttendence>() {
        @Override
        public ModelAttendence mapRow(ResultSet rs, int rowNum) throws SQLException {
            ModelAttendence attendence = new ModelAttendence();
            attendence.setAtt_Id(rs.getInt("att_id"));
            attendence.setEventId(rs.getInt("event_id"));
            attendence.setUserId(rs.getInt("user_id"));
            attendence.setPresence(Attendenceenum.valueOf(rs.getString("presence").toUpperCase()));
            return attendence;
        }
    };

  
    public int addEvent(ModelEvent event) {
        String sql = "INSERT INTO event(eventid, eventname, eventdatetime, eventvenue, eventcategory, approved_by, eventstatus, eventdescription, approved_time, created_by, created_time, status, modified_by, modified_at) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                event.getEventid(),
                event.getEventname(),
                event.getEventdatetime(),
                event.getEventvenue(),
                event.getEventcategory().getType(),
                event.getApproved_by(),
                event.getEventstatus().getType(),
                event.getEventDescription(),
                event.getApproved_time(),
                event.getCreated_by(),
                event.getCreated_time(),
                event.getStatus().getType(),
                event.getModified_by(),
                event.getModified_at()
        );
    }

    public List<ModelEvent> getAllEvents() {
        String sql = "SELECT * FROM event";
        return jdbcTemplate.query(sql, eventRowMapper);
    }

    public ModelEvent getEventById(int id) {
        String sql = "SELECT eventid, eventname, eventdatetime, eventvenue, eventcategory, approved_by, eventstatus, eventdescription, approved_time, created_by, created_time, status, modified_by, modified_at FROM event WHERE eventid = ?";
        return jdbcTemplate.queryForObject(sql, eventRowMapper, id);
    }

    public int updateEvent(ModelEvent event) {
        String sql = "UPDATE event SET eventname=?, eventdatetime=?, eventvenue=?, eventcategory=?, approved_by=?, eventstatus=?, eventdescription=?, approved_time=?, created_by=?, created_time=?, status=?, modified_by=?, modified_at=? WHERE eventid=?";
        return jdbcTemplate.update(sql,
                event.getEventname(),
                event.getEventdatetime(),
                event.getEventvenue(),
                event.getEventcategory().getType(),
                event.getApproved_by(),
                event.getEventstatus().getType(),
                event.getEventDescription(),
                event.getApproved_time(),
                event.getCreated_by(),
                event.getCreated_time(),
                event.getStatus().getType(),
                event.getModified_by(),
                event.getModified_at(),
                event.getEventid()
        );
    }

    public int deleteEvent(int id) {
        String sql = "DELETE FROM event WHERE eventid = ?";
        return jdbcTemplate.update(sql, id);
    }

    public String approveEvent(int eventId,String userid) {
        String sql = "UPDATE event SET eventstatus = 'Approved',Approved_by=?,Approved_time=? WHERE eventid = ? AND eventstatus = 'Pending' and status='active'";
        int rows = jdbcTemplate.update(sql,userid,LocalDateTime.now(),eventId);
        return rows > 0 ? "Event approved successfully" : "Event not found or already approved";
    }

   
    public int markAttendance(String userid,int eventid,Attendenceenum presence,String Facultyid) throws EventnotRegistered {
    	ModelEvent event=getEventById(eventid);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("hi hello ");
		if(LocalDateTime.parse(event.getEventdatetime(),formatter).isBefore(LocalDateTime.now())) { 
			System.out.println("markattendance");
    	System.out.println(userid+eventid+presence+Facultyid);
    	
    	 String checkQuery = "SELECT COUNT(*) FROM registration WHERE userid = ? AND eventid = ?";
    	    Integer count = jdbcTemplate.queryForObject(checkQuery, new Object[]{userid, eventid}, Integer.class);
    	    Integer countforfaculty = jdbcTemplate.queryForObject(checkQuery, new Object[]{Facultyid, eventid}, Integer.class);
    	    System.out.println(count+" "+countforfaculty);
    	    if (count == null || count == 0 ||countforfaculty==0||countforfaculty==null) {
    	        throw new EventnotRegistered("User is not registered for this event.") ;
    	    }
        String sql = "INSERT INTO attendence( userid, eventid, presence, modify_by, modify_time) VALUES ( ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,userid,eventid,presence.getType(),Facultyid,LocalDateTime.now());
		}
		return 0;
    }

    public List<ModelAttendence> getAttendanceByEvent(int eventId) {
        String sql = "SELECT * FROM attendance WHERE event_id = ?";
        return jdbcTemplate.query(sql, attendanceRowMapper, eventId);
    }

    public List<ModelAttendence> getAttendanceByUser(int userId) {
        String sql = "SELECT * FROM attendance WHERE user_id = ?";
        return jdbcTemplate.query(sql, attendanceRowMapper, userId);
    }
    public List<ModelEvent> getpendingevents(){
        String sql = "SELECT * FROM event WHERE  eventstatus= pending";
        return jdbcTemplate.query(sql, eventRowMapper);
    }
    
}
