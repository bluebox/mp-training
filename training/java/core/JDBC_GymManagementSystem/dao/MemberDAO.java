package dao;

import model.Member;
import model.MembershipType;
import model.Gender;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public MemberDAO() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS members (" +
                "member_id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(100) NOT NULL," +
                "age INT NOT NULL," +
                "email VARCHAR(50) NOT NULL,"+
                "gender VARCHAR(10) NOT NULL,"+
                "membership_type VARCHAR(50) NOT NULL," +
                "joined_date DATE NOT NULL," +
                "modified_date DATE NOT NULL" +
                ")";
        try (Statement stmt = DBConnection.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (name, age, email,gender, membership_type, joined_date, modified_date) VALUES (?, ?, ?, ? ,?, ?, ?)";
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setInt(2, member.getAge());
            stmt.setString(3, member.getEmail()); 
            stmt.setString(4, member.getGender().toString());
            stmt.setString(5, member.getMembershipType().toString());
            stmt.setDate(6, Date.valueOf(member.getJoinedDate()));
            stmt.setDate(7, Date.valueOf(member.getModifiedDate()));
            stmt.executeUpdate();
        }
    }

    public void updateMemberFields(int id, String name, Integer age, String email, Gender gender) throws SQLException {
        String sql = "{CALL update_member_fields(?, ?, ?, ?, ?)}";

        try (CallableStatement stmt = DBConnection.getConnection().prepareCall(sql)) {
            stmt.setInt(1, id);

            if (name != null) {
                stmt.setString(2, name);
            } else {
                stmt.setNull(2, Types.VARCHAR);
            }

            if (age != null) {
                stmt.setInt(3, age);
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            if (email != null) {
                stmt.setString(4, email);
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }

            if (gender != null) {
                stmt.setString(5, gender.name());
            } else {
                stmt.setNull(5, Types.VARCHAR);
            }

            stmt.execute();
        }
    }

    public void deleteMember(int memberId) throws SQLException {
        String sql = "DELETE FROM members WHERE member_id = ?";
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            stmt.executeUpdate();
        }
    }

    public Member getMemberById(int memberId) throws SQLException {
        String sql = "SELECT * FROM members WHERE member_id = ?";
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMember(rs);
                }
            }
        }
        return null;
    }

    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Statement stmt = DBConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                members.add(mapResultSetToMember(rs));
            }
        }
        return members;
    }

    public boolean doesMemberExist(int memberId) {
        String sql = "SELECT 1 FROM members WHERE member_id = ?";
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void assignPlan(int memberId, MembershipType plan, LocalDate assignedDate) throws SQLException {
        String sql = "UPDATE members SET membership_type = ?, modified_date = ? WHERE member_id = ?";
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, plan.name());
            stmt.setDate(2, Date.valueOf(assignedDate));
            stmt.setInt(3, memberId);
            stmt.executeUpdate();
        }
    }
    
    public void exportToCSV(String fileName) throws SQLException, IOException {
        String sql = "SELECT * FROM members";
        try (
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))
        ) {
            writer.write("Member ID,Name,Age,Gender,Email,Plan,Assigned Date,Modified Date");
            writer.newLine();

            while (rs.next()) {
                String row = rs.getInt("member_id") + "," +
                             rs.getString("name") + "," +
                             rs.getInt("age") + "," +
                             rs.getString("gender") + "," +
                             rs.getString("email") + "," +
                             rs.getString("membership_type") + "," +
                             rs.getDate("joined_date") + "," +
                             rs.getDate("modified_date");
                writer.write(row);
                writer.newLine();
            }
        }
    }


    private Member mapResultSetToMember(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setMemberId(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setAge(rs.getInt("age"));

    
        member.setEmail(rs.getString("email"));
        
        String genderStr = rs.getString("gender");
        if (genderStr != null) {
            genderStr = genderStr.trim().toUpperCase(); 
            try {
                member.setGender(Gender.valueOf(genderStr));
            } catch (IllegalArgumentException e) {
               
                System.err.println("Invalid gender value: " + genderStr);
                member.setGender(null); 
            }
        } else {
            member.setGender(null);
        }


        member.setMembershipType(MembershipType.valueOf(rs.getString("membership_type")));
        member.setJoinedDate(rs.getDate("joined_date").toLocalDate());
        member.setModifiedDate(rs.getDate("modified_date").toLocalDate());
        return member;
    }
  
  }
