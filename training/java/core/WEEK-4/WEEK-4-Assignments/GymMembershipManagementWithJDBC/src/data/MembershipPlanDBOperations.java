package data;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.Gym;
import models.MembershipPlan;

public class MembershipPlanDBOperations {
	public static void addAllPlans(List<MembershipPlan> list) {
		Connection conn=DatabaseConnection.getConn();
		String insertMembershipPlans="insert into gym.membershipplan(id,name,duration,fee) values(?,?,?,?)";
		try(PreparedStatement psInsert=conn.prepareStatement(insertMembershipPlans)) {
			conn.setAutoCommit(false);
			for(int i=0; i<list.size(); i++) {
				psInsert.setInt(1, list.get(i).getId());
				psInsert.setString(2, list.get(i).getName());
				psInsert.setInt(3, list.get(i).getDuration());
				psInsert.setDouble(4, list.get(i).getFee());
				psInsert.addBatch();
			}
			psInsert.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static boolean selectAllAndStoreLocally(Gym gym) {
		ResultSet resultSet=null;
		String selectQuery="select * from gym.membershipplan";
		Statement statement;
		boolean flag=false;
		
		try {
			statement = DatabaseConnection.getStatement();
			resultSet=statement.executeQuery(selectQuery);
			while(resultSet.next()) {
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				int duration=resultSet.getInt("duration");
				double fee=resultSet.getDouble("fee");
				gym.addPlan(new MembershipPlan(id, name, duration, fee));
				flag=true;
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
		return flag;
		
	}
}
