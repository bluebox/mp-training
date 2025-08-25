package com.VIMS.VIMSBackend.Repo;

import com.VIMS.VIMSBackend.Model.UserModel;
import com.VIMS.VIMSBackend.Model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
            user.getUserRegistationDate(),
            user.getUserModifiedDate(),
            user.getRole().getType()
        );
    }
    
    

    
    public int updateUser(UserModel user) {
        return jdbcTemplate.update(
            "UPDATE users SET  UserEmail=?, UserPassword=?, UserPhone=?, UserAddress=?, UserAge=?, WHERE UserId=?",
            user.getUserEmail(),
            user.getUserPassword(),
            user.getUserPhone(),
            user.getUserAddress(),
            user.getUserAge(),        
            user.getUserId()
        );
    }
    

    // Get user by Id
    public UserModel getUserById(int userId) {
        String sql = "SELECT UserId, UserFirstname, UserLastname, UserEmail, UserPassword, UserPhone, UserAddress, UserAge, UserRegistationDate, UserModifiedDate, Role FROM users WHERE UserId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new RowMapper<UserModel>() {
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
            , userId 
            
            		);
        } catch (Exception e) {
            return null;
        }
    }
    // Get all users
    public List<UserModel> getAllUsers() {
        String sql = "SELECT UserId, UserFirstname, UserLastname, UserEmail, UserPassword, UserPhone, UserAddress, UserAge, UserRegistationDate, UserModifiedDate, Role FROM users";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
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
        });
    }
    
    
    public UserModel getUserByEmail(String email) {
		String sql = "SELECT UserId, UserFirstname, UserLastname, UserEmail, UserPassword, UserPhone, UserAddress, UserAge, UserRegistationDate, UserModifiedDate, Role FROM users WHERE UserEmail = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new RowMapper<UserModel>() {
				@Override
				public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserModel user = new UserModel();
					user.setUserId(rs.getInt("UserId"));
					user.setUserFirstname(rs.getString("userFirstname"));
					user.setUserLastname(rs.getString("userLastname"));
					user.setUserEmail(rs.getString("userEmail"));
					user.setUserPassword(rs.getString("userPassword"));
					user.setUserPhone(rs.getString("userPhone"));
					user.setUserAddress(rs.getString("userAddress"));
					user.setUserAge(rs.getInt("UserAge"));
					user.setUserRegistationDate(rs.getDate("UserRegistationDate"));
					user.setUserModifiedDate(rs.getDate("UserModifiedDate"));
					user.setRole(Role.getRole(rs.getString("Role")));
					return user;
				}
			}, email);
		} catch (Exception e) {
			return null;
		}}

    // Add Review
    public String addReview(int userId, String review) {
        String sql = "INSERT INTO customerreviews (UserId, Review, CreateDate) VALUES (?, ?, ?)";
        java.time.LocalDate today = java.time.LocalDate.now();
        int result = jdbcTemplate.update(sql, userId, review, today);
        return (result > 0) ? "Review added successfully " : "Failed to add review ";
    }
    // Get Reviews by UserId
 //   public List<String> getReviewsByUserId(int userId) {
//		String sql = "SELECT Review FROM customerreviews WHERE UserId = ?";
//		return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> rs.getString("Review"));
//	}
    
    // Get all Reviews
    public List<String> getAllReviews() {
		String sql = "SELECT Review,UserId FROM customerreviews";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("Review")+"   by the UserName_"+rs.getInt("UserId"));
	}
    
}
