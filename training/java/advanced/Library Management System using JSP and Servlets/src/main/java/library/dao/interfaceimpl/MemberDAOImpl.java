package library.dao.interfaceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import library.dao.interfaces.MemberDAO;
import library.exception.LibraryException;
import library.model.Member;
import library.model.enums.Gender;
import library.util.DBConnection;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public String addMember(Member member) {
        String query = "INSERT INTO members (name, email, phoneNumber, gender, address, created_by) VALUES (?, ?, ?, ?, ?, ?)";
        String message;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setLong(3, member.getPhoneNumber());
            pstmt.setString(4, String.valueOf(member.getGender().getCode()));
            pstmt.setString(5, member.getAddress());
            pstmt.setString(6, member.getCreatedBy() != null ? member.getCreatedBy() : "SYSTEM");
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    message = "Member added successfully with ID " + generatedId + ": " + member.getName();
                } else {
                    message = "Member added successfully, but failed to retrieve auto-generated ID: "
                            + member.getName();
                }
            }

        } catch (SQLException e) {
            throw new LibraryException("Error inserting member: " + e.getMessage(), e);
        }
        System.out.println(message);
        return message;
    }

    @Override
    public void updateMember(Member newMemberData) {
        Member criteriaForOld = new Member();
        criteriaForOld.setMemberID(newMemberData.getMemberID());
        List<Member> oldMembers = findMembers(criteriaForOld);
        Member oldMember = oldMembers.isEmpty() ? null : oldMembers.get(0);

        if (oldMember != null) {
            List<String> changedColumns = new ArrayList<>();

            if (!oldMember.getName().equals(newMemberData.getName())) {
                changedColumns.add("Name");
            }
            if (!oldMember.getEmail().equals(newMemberData.getEmail())) {
                changedColumns.add("Email");
            }
            if (oldMember.getPhoneNumber() != newMemberData.getPhoneNumber()) {
                changedColumns.add("PhoneNumber");
            }
            if (oldMember.getGender().getCode() != newMemberData.getGender().getCode()) {
                changedColumns.add("Gender");
            }
            if (!oldMember.getAddress().equals(newMemberData.getAddress())) {
                changedColumns.add("Address");
            }

            if (changedColumns.isEmpty()) {
                System.out.println("No changes detected for Member ID: " + newMemberData.getMemberID());
                return;
            }
            String changeType = "UPDATE: " + String.join(", ", changedColumns);

            logMemberChange(oldMember, changeType);

            String query = "UPDATE members SET name = ?, email = ?, phoneNumber = ?, gender = ?, address = ?, updated_by = ? WHERE memberID = ?";
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, newMemberData.getName());
                pstmt.setString(2, newMemberData.getEmail());
                pstmt.setLong(3, newMemberData.getPhoneNumber());
                pstmt.setString(4, String.valueOf(newMemberData.getGender().getCode()));
                pstmt.setString(5, newMemberData.getAddress());
                pstmt.setString(6, newMemberData.getUpdatedBy() != null ? newMemberData.getUpdatedBy() : "SYSTEM");
                pstmt.setInt(7, newMemberData.getMemberID());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Member updated successfully: " + newMemberData.getName() + " (ID: " + newMemberData.getMemberID() + ")");
                } else {
                    System.out.println("No member found with ID: " + newMemberData.getMemberID() + " to update (0 rows affected).");
                }
            } catch (SQLException e) {
                throw new LibraryException("Error updating member: " + e.getMessage(), e);
            }
        } else {
            System.out.println("No member found with ID: " + newMemberData.getMemberID() + " to update (cannot retrieve old data for logging).");
        }
    }

    @Override
    public void deleteMember(Member member) {
        Member criteriaForDelete = new Member();
        criteriaForDelete.setMemberID(member.getMemberID());
        List<Member> membersToDeleteList = findMembers(criteriaForDelete);
        Member memberToDelete = membersToDeleteList.isEmpty() ? null : membersToDeleteList.get(0);

        if (memberToDelete != null) {
            logMemberChange(memberToDelete, "DELETE");

            String query = "DELETE FROM members WHERE memberID = ?";
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, member.getMemberID());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Member deleted successfully: " + memberToDelete.getName() + " (ID: " + memberToDelete.getMemberID() + ")");
                } else {
                    System.out.println("No member found with ID: " + member.getMemberID() + " to delete (0 rows affected).");
                }
            } catch (SQLException e) {
                throw new LibraryException("Error deleting member: " + e.getMessage(), e);
            }
        } else {
            System.out.println("No member found with ID: " + member.getMemberID() + " to delete (cannot retrieve data for logging).");
        }
    }

    @Override
    public void deleteMembersInBatch(List<Integer> memberIds) {
        if (memberIds == null || memberIds.isEmpty()) {
            return;
        }

        for (Integer memberId : memberIds) {
            Member criteria = new Member();
            criteria.setMemberID(memberId);
            List<Member> membersToLog = findMembers(criteria);
            if (!membersToLog.isEmpty()) {
                logMemberChange(membersToLog.get(0), "BATCH_DELETE");
            }
        }

        String query = "DELETE FROM members WHERE memberID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            for (Integer id : memberIds) {
                pstmt.setInt(1, id);
                pstmt.addBatch();
            }

            int[] rowsAffectedArray = pstmt.executeBatch();
            int totalRowsAffected = 0;
            for (int count : rowsAffectedArray) {
                totalRowsAffected += count;
            }
            System.out.println("Batch delete successful. Total members deleted: " + totalRowsAffected);

        } catch (SQLException e) {
            throw new LibraryException("Error performing batch delete for members: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Member> findMembers(Member criteria) {
        List<Member> members = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT memberID, name, email, phoneNumber, gender, address, created_at, created_by, updated_at, updated_by FROM members WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (criteria.getMemberID() != 0) {
            sqlBuilder.append(" AND memberID = ?");
            params.add(criteria.getMemberID());
        }
        if (criteria.getName() != null && !criteria.getName().trim().isEmpty()) {
            sqlBuilder.append(" AND name LIKE ?");
            params.add("%" + criteria.getName().trim() + "%");
        }
        if (criteria.getEmail() != null && !criteria.getEmail().trim().isEmpty()) {
            sqlBuilder.append(" AND email LIKE ?");
            params.add("%" + criteria.getEmail().trim() + "%");
        }
        if (criteria.getPhoneNumber() != 0) {
            sqlBuilder.append(" AND phoneNumber = ?");
            params.add(criteria.getPhoneNumber());
        }
        if (criteria.getGender() != null && criteria.getGender().getCode() != '\u0000') {
            sqlBuilder.append(" AND gender = ?");
            params.add(String.valueOf(criteria.getGender().getCode()));
        }
        if (criteria.getAddress() != null && !criteria.getAddress().trim().isEmpty()) {
            sqlBuilder.append(" AND address LIKE ?");
            params.add("%" + criteria.getAddress().trim() + "%");
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString())) {

            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("memberID");
                    String foundName = resultSet.getString("name");
                    String foundEmail = resultSet.getString("email");
                    long foundPhoneNumber = resultSet.getLong("phoneNumber");
                    char foundGenderCode = resultSet.getString("gender").charAt(0);
                    String foundAddress = resultSet.getString("address");
                    LocalDateTime createdAtTimestamp = resultSet.getTimestamp("created_at") != null ? resultSet.getTimestamp("created_at").toLocalDateTime() : null;
                    String createdBy = resultSet.getString("created_by");
                    LocalDateTime updatedAtTimestamp = resultSet.getTimestamp("updated_at") != null ? resultSet.getTimestamp("updated_at").toLocalDateTime() : null;
                    String updatedBy = resultSet.getString("updated_by");

                    Gender foundGender = Gender.fromCode(foundGenderCode);

                    Member member = new Member(
                        id, foundName, foundEmail, foundPhoneNumber, foundGender, foundAddress,
                        createdAtTimestamp, createdBy, updatedAtTimestamp, updatedBy
                    );
                    members.add(member);
                }
            }
        } catch (SQLException e) {
            throw new LibraryException("Database error while searching for members: " + e.getMessage(), e);
        }
        return members;
    }

    private void logMemberChange(Member member, String changeType) {
        String logQuery = "INSERT INTO members_log (MemberId, Name, Email, PhoneNumber, Gender, Address, LogDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(logQuery)) {
            pstmt.setInt(1, member.getMemberID());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getEmail());
            pstmt.setLong(4, member.getPhoneNumber());
            pstmt.setString(5, String.valueOf(member.getGender().getCode()));
            pstmt.setString(6, member.getAddress());
            pstmt.setTimestamp(7, Timestamp.from(Instant.now()));
            pstmt.executeUpdate();
            System.out.println("Logged member change: " + changeType + " for Member ID " + member.getMemberID());
        } catch (SQLException e) {
            throw new LibraryException("Error logging member change: " + e.getMessage(), e);
        }
    }
}