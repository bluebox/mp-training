package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Gym;
import models.Member;

public class MemberDBOpearations {
	public static int addMember(String phone, int planid, String registerdate) {
		Connection conn=DatabaseConnection.getConn();
		int memberId = 0;
		String insertPerson="insert into gym.member(phone, planid, registerdate) values(?,?,?)";
		try(PreparedStatement psInsert=conn.prepareStatement(insertPerson,Statement.RETURN_GENERATED_KEYS)) {
			psInsert.setString(1,phone);
			psInsert.setInt(2,planid);
			psInsert.setString(3,registerdate);
			psInsert.executeUpdate();
			ResultSet rs=psInsert.getGeneratedKeys();
			if(rs.next()) {
				memberId=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberId;
	}
	
	public static void updateMembershipPlan(int id,int planId) {
		Connection conn=DatabaseConnection.getConn();
		String updateQuery="update gym.member set planid=? where id=?";
		try(PreparedStatement psUpdate=conn.prepareStatement(updateQuery)){
			psUpdate.setInt(1,planId);
			psUpdate.setInt(2, id);
			psUpdate.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void selectAllAndStoreLocally(Gym gym) {
		ResultSet resultSet=null;
		String selectQuery="select p.phone as phone,p.name as name,p.age as age,m.id as id,m.planid as plainid,m.registerdate as registerdate from gym.person p natural join gym.member m";
		Statement statement;
		
		try {
			statement = DatabaseConnection.getStatement();
			resultSet=statement.executeQuery(selectQuery);
			while(resultSet.next()) {
				String phone=resultSet.getString(1);
				String name=resultSet.getString(2);
				int age=resultSet.getInt(3);
				int memberid=resultSet.getInt(4);
				int planid=resultSet.getInt(5);
				String registerdate=resultSet.getTimestamp(6).toString();
				gym.addNewMember(new Member(phone, name, age, memberid, gym.getPlanById(planid), registerdate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
