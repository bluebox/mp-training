package com.VIMS.VIMSBackend.Repo;

import com.VIMS.VIMSBackend.Model.UserModel;
import com.VIMS.VIMSBackend.Model.Role;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Add User
    
     public int addUser(UserModel user) {
        return jdbcTemplate.update(
            "INSERT INTO users (UserFirstname, UserLastname, UserEmail, UserPassword, UserPhone, UserAddress, UserAge, UserRegistationDate, UserModifiedDate, Role) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            user.getUserFirstname(),
            user.getUserLastname(),
            user.getUserEmail(),
            user.getUserPassword(),
            user.getUserPhone(),
            user.getUserAddress(),
            user.getUserAge(),
            Date.valueOf(LocalDate.now()),   
            user.getUserModifiedDate(),   
            user.getRole().getType()
        );
    }
    // Update User
    public int updateUser(UserModel user) {
    	String updateuserslog="insert into user_log (UserId,UserFirstName,userLastName,UserEmail,UserPassword,UserPhone,UserAddress,UserAge,UserRegistationDate,UserModifiedDate,Role) values(?,?,?,?,?,?,?,?,?,?)";
       int updated=jdbcTemplate.update(updateuserslog,user.getUserId(),user.getUserFirstname(),user.getUserLastname(),user.getUserEmail(),user.getUserPassword(),user.getUserPhone(),user.getUserAddress(),user.getUserAge(),user.getUserRegistationDate(),user.getUserModifiedDate(),user.getRole());
    	if(updated>0) {
    		return 0;
    	}
       int rows= jdbcTemplate.update(
            "UPDATE users SET  UserEmail=?, UserPassword=?, UserPhone=?, UserAddress=?, UserAge=?,UserModifiedDate=? WHERE UserId=?",
            user.getUserEmail(),
            user.getUserPassword(),
            user.getUserPhone(),
            user.getUserAddress(),
            user.getUserAge(),  
            Date.valueOf(LocalDate.now()),
            user.getUserId()
        );
        return rows;
    	
    }
    
    // Get user by Id
    public UserModel getUserById(int userId) {
        String sql = "SELECT UserId,UserFirstname,UserLastname,UserEmail,UserPassword,UserPhone,UserAddress,UserAge,UserRegistationDate,UserModifiedDate,Role FROM users WHERE UserId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
        } catch (Exception e) {
            return null;
        }
    }

    // Get all users
    public List<UserModel> getAllUsers() {
        String sql = "SELECT  UserId,UserFirstname,UserLastname,UserEmail,UserPassword,UserPhone,UserAddress,UserAge,UserRegistationDate,UserModifiedDate,Role FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    
    // Add Review
    public String addReview(int userId, String review) {
        String sql = "INSERT INTO customerreviews (UserId, Review, CreateDate) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, userId, review, Date.valueOf(LocalDate.now()));
        return (result > 0) ? "Review added successfully" : "Failed to add review";
    }

    // Get all Reviews
    public List<String> getAllReviews() {
        String sql = "SELECT Review FROM customerreviews";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("Review"));
    
        
    }
    
    // GetUser by Email
    public UserModel getUserByEmail(String email) {
        String sql = "SELECT  UserId,UserFirstname,UserLastname,UserEmail,UserPassword,UserPhone,UserAddress,UserAge,UserRegistationDate,UserModifiedDate,Role FROM users WHERE UserEmail = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(), email);
        } catch (Exception e) {
            return null;
        }                  

    }
    
    
    
    //  RowMapper
    private static class UserRowMapper implements RowMapper<UserModel> {
        @Override
        public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserModel user = new UserModel();
            user.setUserId(rs.getInt("UserId"));
            user.setUserFirstname(rs.getString("UserFirstname"));
            user.setUserLastname(rs.getString("UserLastname"));
            user.setUserEmail(rs.getString("UserEmail"));
            user.setUserPassword(rs.getString("UserPassword"));
            user.setUserPhone(rs.getString("UserPhone"));
            user.setUserAddress(rs.getString("UserAddress"));
            user.setUserAge(rs.getInt("UserAge"));
            user.setUserRegistationDate(rs.getDate("UserRegistationDate"));
            user.setUserModifiedDate(rs.getDate("UserModifiedDate"));
            user.setRole(Role.getRole(rs.getString("Role")));
            return user;
            
            
            
            
        }
        
    }
}


