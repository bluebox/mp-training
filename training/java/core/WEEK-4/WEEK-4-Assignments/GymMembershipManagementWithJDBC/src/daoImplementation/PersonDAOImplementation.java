package daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.PersonDAO;
import db.DatabaseConnection;

public class PersonDAOImplementation implements PersonDAO{
	public void addPerson(String phone,String name, int age) {
		Connection conn=DatabaseConnection.getConn();
		String insertPerson="insert into gym.person(phone,name,age) values(?,?,?)";
		try(PreparedStatement psInsert=conn.prepareStatement(insertPerson);) {
			psInsert.setString(1,phone);
			psInsert.setString(2,name);
			psInsert.setInt(3,age);
			psInsert.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Invalid details Entered...");
		}
	}
	
	public void updateName(String phone,String name) {
		Connection conn=DatabaseConnection.getConn();
		String updateQuery="update gym.person set name=? where phone=?";
		try(PreparedStatement psUpdate=conn.prepareStatement(updateQuery)){
			psUpdate.setString(1,name);
			psUpdate.setString(2, phone);
			psUpdate.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Invalid details Entered...");
		}
	}
	
	public void updateAge(String phone, int age) {
		Connection conn=DatabaseConnection.getConn();
		String updateQuery="update gym.person set age=? where phone=?";
		try(PreparedStatement psUpdate=conn.prepareStatement(updateQuery)){
			psUpdate.setInt(1,age);
			psUpdate.setString(2, phone);
			psUpdate.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Invalid details Entered...");
		}
	}
	
	public void deleteUser(String phone) {
		Connection conn=DatabaseConnection.getConn();
		String deleteQuery="delete from gym.person where phone=?";
		try(PreparedStatement psDelete=conn.prepareStatement(deleteQuery)){
			psDelete.setString(1, phone);
			psDelete.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Invalid details Entered...");
		}
	}
}
