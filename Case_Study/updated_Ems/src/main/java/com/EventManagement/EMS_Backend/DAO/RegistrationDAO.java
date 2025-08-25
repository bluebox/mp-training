package com.EventManagement.EMS_Backend.DAO;



import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.EventManagement.EMS_Backend.Model.ModelUser;
import com.EventManagement.EMS_Backend.Model.RegistrationEnum;
import com.EventManagement.EMS_Backend.Model.UserEnum;



@Repository
public class RegistrationDAO {
	 
	@Autowired 
	private JdbcTemplate jdbctemplate;
	
	public int userregister(ModelUser user) {
		System.out.println("user "+user);
		
		String query="insert into user(UserId,UserName,Email,MobileNumber,Password,UserType) values (?,?,?,?,?,?)";
		 int res=jdbctemplate.update(query,String.valueOf(user.getUsertype().getType().charAt(0))+user.getUserId(),user.getUserName(),user.getEmail(),user.getMobileNumber(),user.getPassword(),user.getUsertype().getType());
		 return res;
		
	}
	
	public int eventregister(UserEnum usertype,String userid,int eventid) {
		if(iseventvalid(eventid).size()>0 && isRegisteredbefore(eventid,userid).size()==0){
			
		
		String query="insert into registration(UserId,EventId,Status,Modified_at,Modified_by) values (?,?,?,?,?)";
		
		System.out.println(userid+eventid+usertype);

		
		 int res=jdbctemplate.update(query,userid,eventid,RegistrationEnum.REGISTERED.getType(),LocalDateTime.now(),userid);
		 
		 return res;}
		return 0;
	}
	public List<ModelUser> viewallregStudents(int eventid){
		String sql="select user.UserId,UserName,Email,MobileNumber,Password,UserType from registration Inner Join user on registration.UserId=user.UserId where UserType=? and registration.Status=? and eventid=?";
	    List<ModelUser> students = jdbctemplate.query(sql,(rs,rownum)->
	    {ModelUser user=new ModelUser();
        user.setUserId(rs.getString("UserId"));
        user.setUserName(rs.getString("UserName"));
        user.setEmail(rs.getString("Email"));
        user.setMobileNumber(rs.getString("MobileNumber"));
        user.setPassword(rs.getString("Password"));
        user.setUsertype(UserEnum.getUsertype(rs.getString("UserType")));
        return user;},UserEnum.STUDENT.getType(),RegistrationEnum.REGISTERED.getType(),eventid);
	    return students;

	}
	public int cancelregister(String userid,int eventid) {
		String query="insert into registration_log(UserId,EventId,Status,Modified_at,Modified_by) values (?,?,?,?,?)";

		Map<String,Object>result=findmodifieddetials(eventid,userid);
		LocalDateTime Modified_at=  (LocalDateTime) result.get("Modified_at");
	    String Modified_by = (String) result.get("Modified_by");
		 jdbctemplate.update(query,userid,eventid,RegistrationEnum.REGISTERED.getType(),Modified_at,Modified_by);

		String sql="update registration set Status=? where userid=? and eventid=?";
		
		 int res=jdbctemplate.update(sql,RegistrationEnum.CANCELLED.getType(),userid,eventid);
		 System.out.println("this is to"+res);
		 
		 return res;
		
	}
	public ModelUser findbyemail(String email) {
		System.out.println("email"+email);
		String sql="select UserId,UserName,Email,MobileNumber,Password,UserType from user where Email=?";
        return jdbctemplate.queryForObject(sql, (rs,rownum)->
	    {ModelUser user=new ModelUser();
        user.setUserId(rs.getString("UserId"));
        user.setUserName(rs.getString("UserName"));
        user.setEmail(rs.getString("Email"));
        user.setMobileNumber(rs.getString("MobileNumber"));
        user.setPassword(rs.getString("Password"));
        user.setUsertype(UserEnum.getUsertype(rs.getString("UserType")));
        return user;}, email);

	}  
	public Map<String,Object> findmodifieddetials(int eventid,String userid){
	    String sql = "SELECT Modified_at, Modified_by FROM registration WHERE eventid = ? and userid=?";
	    return  jdbctemplate.queryForMap(sql, eventid,userid);
	     
	}
	public Map<String,Object> iseventvalid(int eventid) {
		String sql="Select eventid from event where eventid=? and eventstatus='Approved' and status='active'";
	    return  jdbctemplate.queryForMap(sql, eventid);

	}
	public Map<String, Object> isRegisteredbefore(int eventid,String userid) {
		String sql="Select id from registration where eventid=? and userid=?";
	    return  jdbctemplate.queryForMap(sql, eventid,userid);
	}
	public List<ModelUser> getReguserstomarkattd(int eventid) {
		String sql="select u.UserId,UserName,Email,MobileNumber,Password,UserType from registration Inner Join user u on registration.UserId=u.UserId left join attendence a on a.userid=u.userid  where a.userid is null and registration.eventid=?";
	    return jdbctemplate.query(sql,(rs,rownum)->
	    {ModelUser user=new ModelUser();
        user.setUserId(rs.getString("UserId"));
        user.setUserName(rs.getString("UserName"));
        user.setEmail(rs.getString("Email"));
        user.setMobileNumber(rs.getString("MobileNumber"));
        user.setPassword(rs.getString("Password"));
        user.setUsertype(UserEnum.getUsertype(rs.getString("UserType")));
        return user;},eventid);

		
	}
	
	
	

}



