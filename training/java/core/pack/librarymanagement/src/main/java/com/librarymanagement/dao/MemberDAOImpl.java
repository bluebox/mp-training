package com.librarymanagement.dao;

import com.librarymanagement.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class MemberDAOImpl implements MemberDAO {

    private final DataSource dataSource;

    @Autowired
    public MemberDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getMobile());
            ps.setString(4, String.valueOf(member.getGender()));
            ps.setString(5, member.getAddress());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateMember(Member member) throws SQLException {
        String fetchOld = "SELECT Name, Email, Mobile, Gender, Address FROM members WHERE MemberId=?";
        String insertLog = "INSERT INTO memberUpdate_logs (MemberId, Name, Email, Mobile, Gender, Address, Time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String updateMember = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement psFetch = connection.prepareStatement(fetchOld)) {
                psFetch.setInt(1, member.getMemberId());
                try (ResultSet rs = psFetch.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Member with ID " + member.getMemberId() + " not found.");
                    }

                    String oldName = rs.getString("Name");
                    String oldEmail = rs.getString("Email");
                    String oldMobile = rs.getString("Mobile");
                    String oldGender = rs.getString("Gender");
                    String oldAddress = rs.getString("Address");

                    try (PreparedStatement psLog = connection.prepareStatement(insertLog)) {
                        psLog.setInt(1, member.getMemberId());
                        psLog.setString(2, oldName);
                        psLog.setString(3, oldEmail);
                        psLog.setString(4, oldMobile);
                        psLog.setString(5, oldGender);
                        psLog.setString(6, oldAddress);
                        psLog.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                        psLog.executeUpdate();
                    }

                    try (PreparedStatement psUpdate = connection.prepareStatement(updateMember)) {
                        psUpdate.setString(1, member.getName());
                        psUpdate.setString(2, member.getEmail());
                        psUpdate.setString(3, member.getMobile());
                        psUpdate.setString(4, String.valueOf(member.getGender()));
                        psUpdate.setString(5, member.getAddress());
                        psUpdate.setInt(6, member.getMemberId());
                        psUpdate.executeUpdate();
                    }

                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    @Override
    public Member getMemberById(int memberId) throws SQLException {
        String sql = "SELECT MemberId, Name, Email, Mobile, Gender, Address FROM members WHERE MemberId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToMember(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT MemberId, Name, Email, Mobile, Gender, Address FROM members";
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                members.add(mapRowToMember(rs));
            }
        }
        return members;
    }

    private Member mapRowToMember(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setMemberId(rs.getInt("MemberId"));
        member.setName(rs.getString("Name"));
        member.setEmail(rs.getString("Email"));
        member.setMobile(rs.getString("Mobile"));
        member.setGender(rs.getString("Gender").charAt(0));
        member.setAddress(rs.getString("Address"));
        return member;
    }
}
