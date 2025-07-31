package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Domain.Gender;
import Domain.Member;

public class MemberDAOImpl implements MemberInterface {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/librarymanagementsystem";
        String user = "root";
        String password ="root";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public int addMember(Member member) throws Exception {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
     
        try (Connection conn = getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getMobile());
            stmt.setString(4, ""+member.getGender().getType());
            stmt.setString(5, member.getAddress());
             
        		int b=stmt.executeUpdate();
        		System.out.println(b+"returning");
             return b;
        }catch(Exception e) {
        	throw new Exception(e.getMessage());
        }
   
    }

    @Override
  
    public Member updateMember(Member member) throws Exception {
        String updateSql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";
        String logSql = "INSERT INTO memberslog (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection()){
            conn.setAutoCommit(false);

            // 1. Update actual member table
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, member.getName());
                updateStmt.setString(2, member.getEmail());
                updateStmt.setString(3, member.getMobile());
                updateStmt.setString(4, member.getGender().name().substring(0, 1));
                updateStmt.setString(5, member.getAddress());
                updateStmt.setInt(6, member.getId());

                int rows = updateStmt.executeUpdate();
               
                if (rows == 0) {
                    conn.rollback();
                    return null;
                }
            }

            // 2. Insert into log table
            try (PreparedStatement logStmt = conn.prepareStatement(logSql)) {
                logStmt.setInt(1, member.getId());
                logStmt.setString(2, member.getName());
                logStmt.setString(3, member.getEmail());
                //logStmt.setInt(4, Integer.parseInt(member.getMobile()));
                logStmt.setString(4, member.getMobile());
                logStmt.setString(5, member.getGender().name().substring(0, 1));
                logStmt.setString(6, member.getAddress());
                logStmt.executeUpdate();
            }

            conn.commit();
            return member;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to update member and log changes.");
        }
    }
    

            

            



    @Override
    public List<Member> getAllMembers() throws Exception {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT MemberId,Name, Email, Mobile, Gender, Address FROM members";  
        try (Connection conn = getConnection();
        		PreparedStatement stmt = conn.prepareStatement(sql); 
        		ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getInt("MemberId"));
                member.setName(rs.getString("Name"));
                member.setEmail(rs.getString("Email"));
                member.setMobile(rs.getString("Mobile"));
                member.setGender(Gender.getGender(rs.getString("Gender").charAt(0)));
                member.setAddress(rs.getString("Address"));
                members.add(member);
            }
        }
        return members;
    }
    @Override
    public void deleteMember(int memberId) throws Exception {
        String sql = "DELETE FROM members WHERE MemberId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            stmt.executeUpdate();
        }
    }


    @Override
    public Member getMemberById(int memberId) throws Exception {
        String sql = "SELECT MemberId,Name, Email, Mobile, Gender, Address FROM members WHERE MemberId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Member member = new Member();
                    member.setId(rs.getInt("MemberId"));
                    member.setName(rs.getString("Name"));
                    member.setEmail(rs.getString("Email"));
                    member.setMobile(rs.getString("Mobile"));
                    member.setGender(Gender.getGender(rs.getString("Gender").charAt(0)));
                    member.setAddress(rs.getString("Address"));
                    return member;
                }
            }
        }
        return null;
    }
}
