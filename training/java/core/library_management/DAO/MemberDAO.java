package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.Member;
import pojo.Member.Gender;
import utility.DBUtil;
import utility.DBqueries;

public class MemberDAO {

    public void addMember(Member member) throws SQLException {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DBqueries.INSERT_MEMBER)) {
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setLong(3, member.getMobile());
            preparedStatement.setString(4, member.getGender().toString().substring(0, 1));
            preparedStatement.setString(5, member.getAddress());
            preparedStatement.executeUpdate();
        }
    }

    public void updateMember(Member member) throws SQLException {
        try (Connection connection = DBUtil.getConnection()) {
            connection.setAutoCommit(false); 

            try (PreparedStatement insertLogStmt = connection.prepareStatement(DBqueries.INSERT_MEMBER_LOG)) {
                insertLogStmt.setInt(1, member.getMemberId());
                insertLogStmt.executeUpdate();
            }

            try (PreparedStatement updateStmt = connection.prepareStatement(DBqueries.UPDATE_MEMBER)) {
                updateStmt.setString(1, member.getName());
                updateStmt.setString(2, member.getEmail());
                updateStmt.setLong(3, member.getMobile());
                updateStmt.setString(4, member.getGender().toString().substring(0, 1));
                updateStmt.setString(5, member.getAddress());
                updateStmt.setInt(6, member.getMemberId());
                updateStmt.executeUpdate();
            }

            connection.commit();
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DBqueries.SELECT_ALL_MEMBERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Member member = new Member();
                member.setMemberId(resultSet.getInt("memberid"));
                member.setName(resultSet.getString("name"));
                member.setEmail(resultSet.getString("email"));
                member.setMobile(resultSet.getLong("mobile"));
                member.setGender(Gender.valueOf(resultSet.getString("gender").equals("M") ? "MALE" : "FEMALE"));
                member.setAddress(resultSet.getString("address"));
                members.add(member);
            }
        }
        return members;
    }

    public Member getMemberById(int memberId) throws SQLException {
        Member member = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM members WHERE memberid = ?")) {
            preparedStatement.setInt(1, memberId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    member = new Member();
                    member.setMemberId(resultSet.getInt("memberid"));
                    member.setName(resultSet.getString("name"));
                    member.setEmail(resultSet.getString("email"));
                    member.setMobile(resultSet.getLong("mobile"));
                    member.setGender(Gender.valueOf(resultSet.getString("gender").equals("M") ? "MALE" : "FEMALE"));
                    member.setAddress(resultSet.getString("address"));
                }
            }
        }
        return member;
    }
}