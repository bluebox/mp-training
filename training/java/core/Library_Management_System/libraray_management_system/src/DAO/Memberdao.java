// File: dao/Memberdao.java
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
            stmt.setInt(3, member.getMobile());
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
    public boolean updateMember(Member member, int memberid) {
        String sql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE Memberid = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, member.getGender().getType());
            stmt.setString(5, member.getAddress());
            stmt.setInt(6, memberid);
            int rows = stmt.executeUpdate();
            return rows > 0;

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
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getInt("Mobile"),
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
                        rs.getInt("Mobile"),
                        rs.getString("Address"),
                        Gender.getstatus(rs.getString("Gender"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
