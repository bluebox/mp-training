package com.medplus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Gym {
	private final LinkedHashMap<Integer, Member> gymMembers = new LinkedHashMap<Integer, Member>();
	private String url = "jdbc:mysql://localhost:3306/GymApplication"; // replace with your DB name
	private String user = "root"; // your username
	private String password = "Medplus@321"; // your password
	private Connection conn;

	public Gym() {
		try {
			// 1. Load and register JDBC driver (optional for modern versions)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Create connection
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the database!");

			// 3. Create a statement
			Statement stmt = conn.createStatement();

//			// 4. Execute query
//			ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");
//
//			// 5. Process the result
//			while (rs.next()) {
//				System.out.println("name : " + rs.getString("name") + " Age : " + rs.getInt("Age"));
//				// Add more columns as needed
//			}
//			String sql = "INSERT INTO Employee (name,Age) VALUES (?,?)";
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setString(1, "Ajay");
//			statement.setInt(2, 42);
////			int Check = statement.executeUpdate();
////			if (Check > 0 ) {
////				System.out.println("Insertion done !!!");
////			}else {
////				System.out.println("Some Error Occured ");
////			}
//			String delete = "DELETE FROM Employee where name = ?";
//			PreparedStatement deleteStatement = conn.prepareStatement(delete);
//			deleteStatement.setString(1, "Ajay");
//			deleteStatement.executeUpdate();
//			System.out.println("Deletion done !!!");
//
//			// 6. Close resources
//			rs.close();
//			stmt.close();
//			conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver not found: " + e.getMessage());
		}  
	}

	public void add(Member member) {
		System.out.println("calling");
		String adding = "INSERT INTO GymMembers (memberId,name,age,plan,startDate,endDate)Values (?,?,?,?,?,?)";
		try {
			PreparedStatement add = conn.prepareStatement(adding);
			add.setInt(1, member.getMemberId());
			add.setString(2, member.getName());
			add.setInt(3, member.getAge());
			add.setString(4, member.getPlan().name());
			add.setDate(5, Date.valueOf(member.getStartDate()));
			add.setDate(6, Date.valueOf(member.getEndDate()));
			add.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection Failed !!!");
			e.printStackTrace();
		}
		gymMembers.put(member.getMemberId(), member);
	}

	public void remove(Member member) {
		System.out.println("callin hgfyhfyhfry");
		String deleting = "DELETE FROM GymMembers where memberId = ?";
		try {
			PreparedStatement delete = conn.prepareStatement(deleting);
			System.out.println("deleting mermeber : " + member.getMemberId());
			delete.setInt(1, member.getMemberId());
			delete.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection Failed !!!");
			e.printStackTrace();
		}
		gymMembers.remove(member.getMemberId());
	}

	public ArrayList<Member> serachByName(String name) {

		ArrayList<Member> arrayListByName = new ArrayList<>();
		for (var i : gymMembers.values()) {
			if (i.getName() == name) {
				arrayListByName.add(i);
			}
		}
		return arrayListByName;
	}

	public ArrayList<Member> getAllMembers() {

		return new ArrayList<Member>(gymMembers.values());
	}

	public boolean checkById(int id) {
		return (gymMembers.containsKey(id));
	}

	public void remove(int id) {
		String deleting = "DELETE FROM GymMembers where memberId = ?";
		try {
			PreparedStatement delete = conn.prepareStatement(deleting);
			delete.setInt(1, id);
			delete.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection Failed !!!");
			e.printStackTrace();
		}
		gymMembers.remove(id);
	}

	public Member getMember(int id) {
		return gymMembers.get(id);
	}
}
