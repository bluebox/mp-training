package librarySystem.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import librarySystem.Utils.DbConnection;
import model.MemberPojo;

public class MemberDao {

    public List<MemberPojo> getMembers() {
        List<MemberPojo> members = new ArrayList<>();
        String sql = "select * from members";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MemberPojo member = new MemberPojo();
                member.setMemberId(rs.getInt("MemberId"));
                member.setName(rs.getString("Name"));
                member.setEmail(rs.getString("Email"));
                member.setMobile(rs.getInt("Mobile"));
                member.setGender(rs.getString("Gender").charAt(0));
                member.setAddress(rs.getString("Address"));
                members.add(member);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching members: " + e.getMessage());
        }

        return members;
    }

    public boolean isPresent(MemberPojo member) {
        String sql = "select count(*) from members where Email = ? or Mobile = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getEmail());
            stmt.setInt(2, member.getMobile());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error checking member presence: " + e.getMessage());
        }

        return false;
    }

    public MemberPojo getMemberByEmailOrMobile(String email, int mobile) {
        String sql = "select * from members where Email = ? or Mobile = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setInt(2, mobile);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                MemberPojo member = new MemberPojo();
                member.setMemberId(rs.getInt("MemberId"));
                member.setName(rs.getString("Name"));
                member.setEmail(rs.getString("Email"));
                member.setMobile(rs.getInt("Mobile"));
                member.setGender(rs.getString("Gender").charAt(0));
                member.setAddress(rs.getString("Address"));
                return member;
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving member: " + e.getMessage());
        }

        return null;
    }

    public boolean registerMember(MemberPojo member) {
        

        String insertSql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            conn.setAutoCommit(false);

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setInt(3, member.getMobile());
            stmt.setString(4, String.valueOf(member.getGender()));
            stmt.setString(5, member.getAddress());

            stmt.executeUpdate();

            conn.commit();
            System.out.println("Member registered successfully.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error registering member: " + e.getMessage());

            try (Connection conn = DbConnection.getConnection()) {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction rolled back.");
                }
            } catch (SQLException rollbackEx) {
                System.err.println("Rollback failed: " + rollbackEx.getMessage());
            }

            return false;
        }
    }

    public boolean updateMember(MemberPojo existingMember, MemberPojo updatedMember) {
        String updateSql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";
        String logSql = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(updateSql);
             PreparedStatement logStmt = conn.prepareStatement(logSql)) {

            conn.setAutoCommit(false);

            MemberPojo current = getMemberByEmailOrMobile(existingMember.getEmail(), existingMember.getMobile());
            if (current == null) {
                System.out.println("Member not found for update.");
                return false;
            }

            updateStmt.setString(1, updatedMember.getName());
            updateStmt.setString(2, updatedMember.getEmail());
            updateStmt.setInt(3, updatedMember.getMobile());
            updateStmt.setString(4, String.valueOf(updatedMember.getGender()));
            updateStmt.setString(5, updatedMember.getAddress());
            updateStmt.setInt(6, current.getMemberId());

            logStmt.setInt(1, current.getMemberId());
            logStmt.setString(2, current.getName());
            logStmt.setString(3, current.getEmail());
            logStmt.setInt(4, current.getMobile());
            logStmt.setString(5, String.valueOf(current.getGender()));
            logStmt.setString(6, current.getAddress());

            updateStmt.addBatch();
            logStmt.addBatch();

            updateStmt.executeBatch();
            logStmt.executeBatch();

            conn.commit();
            System.out.println("Member updated and logged successfully.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error updating member: " + e.getMessage());
            try (Connection conn = DbConnection.getConnection()) {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction rolled back.");
                }
            } catch (SQLException rollbackEx) {
                System.err.println("Rollback failed: " + rollbackEx.getMessage());
            }

            return false;
        }
    }
}
