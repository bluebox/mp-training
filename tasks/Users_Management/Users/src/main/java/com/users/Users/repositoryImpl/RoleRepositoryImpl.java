package com.users.Users.repositoryImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.users.Users.model.Role;
import com.users.Users.model.UserRole;
import com.users.Users.repository.interfaces.RoleRepository;
import com.users.Users.rowmapper.RoleMapper;
import com.users.Users.rowmapper.UserRoleMapper;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public RoleRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> getRoles() {
        String sqlString = "SELECT id,role_code,role_name,status,created_at,updated_at FROM Roles";
        
        return jdbcTemplate.query(sqlString, new RoleMapper());
       
    }

    @Override
    public void addUserRole(UserRole userRole) {

        String getUserIdSql = "SELECT user_id FROM Users WHERE user_code = :user_code";
        MapSqlParameterSource userParam = new MapSqlParameterSource();
        userParam.addValue("user_code", userRole.getUsercode()); 
        Integer userId = namedParameterJdbcTemplate.queryForObject(getUserIdSql, userParam, Integer.class);

        String getRoleIdSql = "SELECT id FROM Roles WHERE role_code = :role_code";
        for (String roleCode : userRole.getRolecodes()) { 
            MapSqlParameterSource roleParam = new MapSqlParameterSource();
            roleParam.addValue("role_code", roleCode);
            
            Integer roleId = namedParameterJdbcTemplate.queryForObject(getRoleIdSql, roleParam, Integer.class);

            String getLocationIdSql = "SELECT location_id FROM Locations WHERE country = :country AND state = :state AND city = :city";
            
            MapSqlParameterSource locParam = new MapSqlParameterSource();
            locParam.addValue("country", userRole.getCountry());
            locParam.addValue("state", userRole.getState());
            locParam.addValue("city", userRole.getCity());
            LocalDateTime created = LocalDateTime.now();
            locParam.addValue("created_at", Timestamp.valueOf(created));


            List<Integer> locIds = namedParameterJdbcTemplate.query(getLocationIdSql, locParam, (rs, rowNum) -> rs.getInt("location_id"));
            
            Integer locationId;
            if (locIds.isEmpty()) {
                String insertLocationSql = "INSERT INTO Locations (country, state, city,created_at) VALUES (:country, :state, :city,:created_at)";
                namedParameterJdbcTemplate.update(insertLocationSql, locParam);
                
                locationId = namedParameterJdbcTemplate.queryForObject(getLocationIdSql, locParam, Integer.class);
            } else {
                locationId = locIds.get(0);
            }

            String insertUserRoleSql = "INSERT INTO UserRoles (user_id, role_id, location_id,created_at) VALUES (:user_id, :role_id, :location_id,:created_at)";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("user_id", userId);
            param.addValue("role_id", roleId);
            param.addValue("location_id", locationId);
            LocalDateTime createdtime = LocalDateTime.now();
            param.addValue("created_at", Timestamp.valueOf(createdtime));

            namedParameterJdbcTemplate.update(insertUserRoleSql, param);
        }
    }

    @Override
    public List<UserRole> getUserAssignedRoles(String userCode) {

        String sqlString = "SELECT us.user_code,CONCAT(us.first_name,' ',us.last_name) AS usernameString, ur.role_id, ro.role_code, ro.role_name, ur.status ,l.country, l.state, l.city,"
        		+ "ur.created_at, ur.updated_at "
                + "FROM UserRoles AS ur "
                + "JOIN Users AS us ON ur.user_id = us.user_id "
                + "JOIN Roles AS ro ON ur.role_id = ro.id "
                + "JOIN Locations AS l ON ur.location_id = l.location_id "
                + "WHERE  us.user_code = :user_code";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("user_code", userCode);
        param.addValue("status", "ACTIVE");

        return namedParameterJdbcTemplate.query(sqlString, param, new UserRoleMapper());
    }

    @Override
    public List<String> getRoleName(String userCode) {
        String sql = "SELECT ro.role_name "
                   + "FROM Roles ro "
                   + "WHERE ro.id IN (SELECT ur.role_id FROM UserRoles ur "
                   + "JOIN Users u ON ur.user_id = u.user_id "
                   + "WHERE u.user_code = :user_code AND ur.status='ACTIVE')";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("user_code", userCode);

        List<String> roleNames = namedParameterJdbcTemplate.query(sql, param, (rs, rowNum) -> rs.getString("role_name"));
        return roleNames.isEmpty() ? null : roleNames;
    }
    
    @Override
    public boolean changeRoleStatus(String roleCode) {
        String sql = "UPDATE Roles SET status = CASE WHEN status='ACTIVE' THEN 'INACTIVE' ELSE 'ACTIVE' END ,updated_at =:updated_at WHERE role_code = :role_code";
        
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("role_code", roleCode);
        LocalDateTime updatetime = LocalDateTime.now();
        param.addValue("updated_at", Timestamp.valueOf(updatetime));

        
        int rows = namedParameterJdbcTemplate.update(sql, param);
        return rows>0;
    }

	@Override
	public boolean changeUserRoleStatus(UserRole userRole) {
		
		String sql = "UPDATE UserRoles SET updated_at=:updated_at, status = CASE WHEN status='ACTIVE' THEN 'INACTIVE' ELSE 'ACTIVE' END WHERE role_id IN ( "
				+ "SELECT id from Roles WHERE role_code =:role_code "
				+ ") and user_id IN ("
				+ " SELECT user_id from Users where user_code = :user_code and location_id IN ("
				+ " SELECT location_id FROM Locations WHERE country = :country AND state = :state AND city = :city ) )";
		
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("role_code", userRole.getRolecodes().get(0));
        param.addValue("user_code", userRole.getUsercode());
        param.addValue("country", userRole.getCountry());
        param.addValue("state", userRole.getState());
        param.addValue("city", userRole.getCity());
        LocalDateTime updatetime = LocalDateTime.now();
        param.addValue("updated_at", Timestamp.valueOf(updatetime));


        
       int rows = namedParameterJdbcTemplate.update(sql, param);
       return rows>0;

	}
}
