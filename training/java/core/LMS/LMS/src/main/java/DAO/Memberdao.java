package DAO;

import domain.Member;
import domain.checking_enum.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Memberdao implements MemberInterface {

    private final String URL = "jdbc:mysql://127.0.0.1:3306/library_management_system";
    private final String USER = "devuser";
    private final String PASSWORD = "Bobby@514";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean addMember(Member member) {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, member.getGender().getType());
            stmt.setString(5, member.getAddress());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMember(Member member, int memberid) throws SQLException {
        String logSql = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";

        String updateSql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE Memberid = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false); 

            try (
                PreparedStatement logStmt = conn.prepareStatement(logSql);
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);

            ) {
            	logStmt.setInt(1, memberid);
                logStmt.setString(2, member.getName());
                logStmt.setString(3, member.getEmail());
                logStmt.setLong(4, member.getMobile());
                logStmt.setString(5, member.getGender().getType());
                logStmt.setString(6, member.getAddress());

                int rows=logStmt.executeUpdate();

                


                if (rows > 0) {
                    updateStmt.setString(1, member.getName());
                    updateStmt.setString(2, member.getEmail());
                    updateStmt.setLong(3, member.getMobile());
                    updateStmt.setString(4, member.getGender().getType());
                    updateStmt.setString(5, member.getAddress());
                    updateStmt.setInt(6, memberid);

                    updateStmt.executeUpdate();

                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Update failed, rolling back.");
                throw new SQLException("Error during update and log: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT Memberid, Name, Email, Mobile, Gender, Address FROM members";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Member member = new Member(
                    rs.getInt("Memberid"),
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getLong("Mobile"),
                    rs.getString("Address"),
                    Gender.getstatus(rs.getString("Gender"))
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    @Override
    public Member getById(int memberId) {
        String sql = "SELECT Name, Email, Mobile, Gender, Address FROM members WHERE Memberid = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Member(
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getLong("Mobile"),
                    rs.getString("Address"),
                    Gender.getstatus(rs.getString("Gender"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
    	Memberdao m=new Memberdao();
    	Member m1=new Member("nihaa","basbbszdeesdn@hsha.com",8967857782L,"asdasd",Gender.FEMALE);
    	System.out.println(m.updateMember(m1, 1));
    	
    }
}
