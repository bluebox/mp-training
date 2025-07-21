package dao;

import model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymDao {

    
    private final String URL = "jdbc:mysql://localhost:3306/gym.db";
    private final String USER = "devuser";  
    private final String PASSWORD = "Bobby@514";  

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
         Connection con= DriverManager.getConnection(URL, USER, PASSWORD);
         return con;
    }

   
    public void addMember(Member member) throws SQLException {
        String query = "INSERT INTO members (name, age, phone, plan) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, member.getName());
            ps.setInt(2, member.getAge());
            ps.setString(3, member.getPhone());
            ps.setString(4, member.getPlan());
            ps.executeUpdate();
            System.out.println("i am called")  ;      }
    }

    
    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
        	
            while (rs.next()) {
                Member m = new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("phone"),
                        rs.getString("plan")
                );
                members.add(m);
            }
        }
        return members;
    }

  
    public void deleteMember(int id) throws SQLException {
        String query = "DELETE FROM members WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


    public void updateMember(Member member) throws SQLException {
        String query = "UPDATE members SET name=?, age=?, phone=?, plan=? WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, member.getName());
            ps.setInt(2, member.getAge());
            ps.setString(3, member.getPhone());
            ps.setString(4, member.getPlan());
            ps.setInt(5, member.getId());
            ps.executeUpdate();
        }
    }
}
