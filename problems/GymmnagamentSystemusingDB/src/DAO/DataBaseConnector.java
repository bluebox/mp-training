package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

// --------------------------------------------DataBaseTable Creation in mysql---------------------------------
//use gym;
//CREATE TABLE IF NOT EXISTS PLAN (
//	    Id INT AUTO_INCREMENT PRIMARY KEY,
//	    planName VARCHAR(255) NOT NULL,
//	    planDuration INT NOT NULL,
//	    fee DECIMAL(10, 2) NOT NULL
//	);
//	CREATE TABLE IF NOT EXISTS MEMBER (
//	    Id INT AUTO_INCREMENT PRIMARY KEY,
//	    name VARCHAR(255) NOT NULL,
//	    age INT NOT NULL,
//	    PLANId INT,
//	    FOREIGN KEY (PLANId) REFERENCES PLAN(Id) ON DELETE CASCADE
//	);




public class DataBaseConnector {

    private static final String username = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/GYM"; 

    public static Connection createConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try( Connection con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);) {
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addMember(String memberName, int memberAge) throws ClassNotFoundException, SQLException {
        Connection conn = createConnection();
        String insertMember = "INSERT INTO MEMBER (name, age) values (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertMember)) {
            ps.setString(1, memberName);
            ps.setInt(2, memberAge);
            int value = ps.executeUpdate();
            if (value == 1) {
                System.out.println("Added member");
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } 
    }

    public void addPlan(String name, Double planFee, int durationInMonths) throws ClassNotFoundException, SQLException {
        Connection conn = createConnection();
        String insertPlan = "INSERT INTO PLAN (planName, planDuration, fee) values (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertPlan)) {
            ps.setString(1, name);
            ps.setInt(2, durationInMonths);
            ps.setDouble(3, planFee);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
    }

    public void removePlan(int id) throws ClassNotFoundException, SQLException {
        Connection conn = createConnection();
        String removePlan = "UPDATE MEMBER SET PLANId = 0 WHERE Id = ?";
        try (PreparedStatement ps = conn.prepareStatement(removePlan)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
    }

    // Show plans
    public List<Plan> showPlans() throws ClassNotFoundException, SQLException {
        Connection conn = createConnection();
        String getPlans = "SELECT Id,planName,fee,planDuration FROM PLAN";
        try (PreparedStatement ps = conn.prepareStatement(getPlans);
             ResultSet rs = ps.executeQuery()) {
            StringBuilder result = new StringBuilder();
            List<Plan> list=new ArrayList<>();
            while (rs.next()) {
                result.append(rs.getInt("Id")).append(" ")
                      .append(rs.getString("planName")).append(" ")
                      .append(rs.getInt("planDuration")).append(" ")
                      .append(rs.getDouble("fee")).append("\n");
                list.add(new Plan(rs.getInt("Id"),rs.getString("planName"),rs.getInt("planDuration"),rs.getDouble("fee")));
            }
            System.out.println(result);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show members
    public List<Member> showMembers() throws ClassNotFoundException, SQLException {
        Connection conn = createConnection();
        String getMembers = "SELECT MEMBER.Id, MEMBER.name, MEMBER.age, PLAN.planName FROM MEMBER " +
                             "JOIN PLAN ON MEMBER.PLANId = PLAN.Id";
        try (PreparedStatement ps = conn.prepareStatement(getMembers);
             ResultSet rs = ps.executeQuery()) {
            StringBuilder result = new StringBuilder();
            List<Member> list=new ArrayList<>();
            while (rs.next()) {
                result.append(rs.getInt("Id")).append(" ")
                      .append(rs.getString("name")).append(" ")
                      .append(rs.getInt("age")).append(" ")
                      .append(rs.getString("planName")).append("\n");
                list.add(new Member(rs.getInt("Id"),rs.getString("name"),rs.getInt("age"),rs.getString("planName")));
       
            }
            System.out.println(result);
            return list;
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
    }

    // Find member
    public int findMember(int memberId) throws ClassNotFoundException, SQLException {
        Connection conn = createConnection();
        String findMember = "SELECT id,name,age FROM MEMBER WHERE Id = ?";
        try (PreparedStatement ps = conn.prepareStatement(findMember)) {
            ps.setInt(1, memberId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1; // Member found since one record found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Member not found
    }

    // Update plan or add plan to member
    public int updatePlan(int memberId, int planId) throws ClassNotFoundException, SQLException {
        Connection conn = createConnection();
        String updatePlan = "UPDATE MEMBER SET PLANId = ? WHERE Id = ?";
        try (PreparedStatement ps = conn.prepareStatement(updatePlan)) {
            ps.setInt(1, planId);
            ps.setInt(2, memberId);
            int value = ps.executeUpdate();
            conn.commit();
            return value;
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } 
        return 0;
    }
}
