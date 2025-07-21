package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDBOperations{
	public static void addPerson(String phone,String name, int age) {
		Connection conn=DatabaseConnection.getConn();
		String insertPerson="insert into gym.person(phone,name,age) values(?,?,?)";
		try(PreparedStatement psInsert=conn.prepareStatement(insertPerson);) {
			psInsert.setString(1,phone);
			psInsert.setString(2,name);
			psInsert.setInt(3,age);
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
