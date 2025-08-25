package com.EventManagement.EMS_Backend.DAO;



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
	private UserRowMapper userrowmapper;
	@Autowired 
	private JdbcTemplate jdbctemplate;
	
	public int userregister(ModelUser user) {
		System.out.println("user "+user);
		
		String query="insert into user(UserId,UserName,Email,MobileNumber,Password,UserType) values (?,?,?,?,?,?)";
		 int res=jdbctemplate.update(query,String.valueOf(user.getUsertype().getType().charAt(0))+user.getUserId(),user.getUserName(),user.getEmail(),user.getMobileNumber(),user.getPassword(),user.getUsertype().getType());
		 return res;
		
	}
	
	public int eventregister(UserEnum usertype,String userid,int eventid) {
		String query="insert into registration(UserId,EventId,Status,Modified_at,Modified_by) values (?,?,?,?,?)";
		
		System.out.println(userid+eventid+usertype);

		
		 int res=jdbctemplate.update(query,userid,eventid,RegistrationEnum.REGISTERED.getType(),LocalDateTime.now(),userid);
		 return res;
	}
	public List<ModelUser> viewallregStudents(int eventid){
		String sql="select user.UserId,UserName,Email,MobileNumber,Password,UserType from registration Inner Join user on registration.UserId=user.UserId where UserType=? and registration.Status=? and eventid=?";
	    List<ModelUser> students = jdbctemplate.query(sql,userrowmapper,UserEnum.STUDENT.getType(),RegistrationEnum.REGISTERED.getType(),eventid);
	    return students;

	}
	public int cancelregister(String userid,int eventid) {
		String query="insert into registration_log(UserId,EventId,Status,Modified_at,Modified_by) values (?,?,?,?,?)";

		Map<String,Object>result=findmodifieddetials(eventid,userid);
		String Modified_at= (String) result.get("Modified_at");
	    Integer Modified_by = (Integer) result.get("Modified_by");
		 jdbctemplate.update(query,userid,eventid,RegistrationEnum.REGISTERED.getType(),Modified_at,Modified_by);

		String sql="update registration set Status=? where userid=? and eventid=?";
		
		 int res=jdbctemplate.update(sql,RegistrationEnum.CANCELLED.getType(),userid,eventid);
		 return res;
		
	}
	public ModelUser findbyemail(String email) {
		System.out.println("email"+email);
		String sql="select UserId,UserName,Email,MobileNumber,Password,UserType from user where Email=?";
        return jdbctemplate.queryForObject(sql, userrowmapper, email);

	}  
	public Map<String,Object> findmodifieddetials(int eventid,String userid){
	    String sql = "SELECT Modified_at, Modified_by FROM event WHERE eventid = ? and userid=?";
	    return  jdbctemplate.queryForMap(sql, eventid,userid);
	     
	}
	
	

}




@Component
class UserRowMapper implements RowMapper<ModelUser> {
	

	@Override
    public ModelUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		ModelUser user=new ModelUser();
        user.setUserId(rs.getString("UserId"));
        user.setUserName(rs.getString("UserName"));
        user.setEmail(rs.getString("Email"));
        user.setMobileNumber(rs.getString("MobileNumber"));
        user.setPassword(rs.getString("Password"));
        user.setUsertype(UserEnum.getUsertype(rs.getString("UserType")));
        return user;
    	
}
}
