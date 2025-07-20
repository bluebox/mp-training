import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gym {
	Connection connection;
	public Gym() {
		String url = "jdbc:mysql://localhost:3306/GYM_DATABASE";
        String user = "root";
        String password = "Akash@123";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	public void addMember(int id,String name,int age,int plan_id) throws SQLException {
		PreparedStatement p=connection.prepareStatement("insert into Members_Table values (?,?,?,?);");
		p.setInt(1,id);
		p.setString(2,name);
		p.setInt(3, age);
		p.setInt(4, plan_id);
		p.executeUpdate();
		p.close();
		System.out.println("Member Added successfully.");
    }
	
	
	
	
	public void viewAllMembers() throws SQLException {
		PreparedStatement p=connection.prepareStatement("select * from Members_Table;");
		ResultSet res=p.executeQuery();
		while(res.next()) {
			System.out.println(res.getInt(1)+" " + res.getString(2)+" "+res.getInt(3)+" "+res.getInt(4));
		}
		p.close();
		res.close();
    }
	
	
	public void updateMember(int memberId, String newName, int newAge) throws SQLException {
		PreparedStatement p=connection.prepareStatement("UPDATE Members_Table SET name = ?, age = ? WHERE mem_id =?;");
		p.setString(1, newName); 
	    p.setInt(2, newAge); 
	    p.setInt(3,memberId);
		p.executeUpdate();
		p.close();
    }
	

	public void assignPlan(int  memberId, int planIndex) throws SQLException {
		PreparedStatement p=connection.prepareStatement("UPDATE Members_Table SET plan_id=? WHERE mem_id =?;");
		p.setInt(1, planIndex); 
	    p.setInt(2,memberId);
		p.executeUpdate();
		p.close();
    }
	
	
	public void deleteMember(int memberId) throws SQLException {
		PreparedStatement p=connection.prepareStatement("Delete from Members_Table WHERE mem_id = ?");
		p.setInt(1,memberId);
		p.executeUpdate();
		p.close();
    }
	public void displayMemberDetails(int memberId) throws SQLException {
		PreparedStatement p=connection.prepareStatement("select * from Members_Table where mem_id=?;");
		p.setInt(1,memberId);
		ResultSet res=p.executeQuery();
		while(res.next()) {
			System.out.println(res.getInt(1)+" " + res.getString(2)+" "+res.getInt(3)+" "+res.getInt(4));
		}
		p.close();
		res.close();
    }

}
