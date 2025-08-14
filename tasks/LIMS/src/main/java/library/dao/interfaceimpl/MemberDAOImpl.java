package library.dao.interfaceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    public boolean addMember(Member member) {
        String query = "INSERT INTO members (name, email, phoneNumber, gender, address, created_by) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setLong(3, member.getPhoneNumber());
            pstmt.setString(4, String.valueOf(member.getGender().getCode()));
            pstmt.setString(5, member.getAddress());
            pstmt.setString(6, member.getCreatedBy() != null ? member.getCreatedBy() : "SYSTEM");
            int result = pstmt.executeUpdate();
            
            if(result>0) {
            	return true;
            }

        } catch (Exception e) {
            throw new LibraryException("Error inserting member: " + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void updateMember(Member newMemberData) {
        Member criteriaForOld = new Member();
        criteriaForOld.setMemberID(newMemberData.getMemberID());
        List<Member> oldMembers = findMembers(criteriaForOld);
        Member oldMember = oldMembers.isEmpty() ? null : oldMembers.get(0);

        if (oldMember != null) {
        
            try {
				logMemberChange(oldMember);
			} catch (Exception e) {
				e.printStackTrace();
			}

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
                pstmt.executeUpdate();
              
            } catch (Exception e) {
            		throw new LibraryException("Error updating member: " + e.getMessage(), e);            		
            }
        }
    }

    @Override
    public void deleteMembers(List<Integer> memberIds) {
        if (memberIds == null || memberIds.isEmpty()) {
            return;
        }

        for (Integer memberId : memberIds) {
            Member criteria = new Member();
            criteria.setMemberID(memberId);
            List<Member> membersToLog = findMembers(criteria);
            if (!membersToLog.isEmpty()) {
                try {
					logMemberChange(membersToLog.get(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }

        String query = "DELETE FROM members WHERE memberID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            for (Integer id : memberIds) {
                pstmt.setInt(1, id);
                pstmt.addBatch();
            }

            pstmt.executeBatch();

        } catch (Exception e) {
        	if(e.getCause() instanceof SQLIntegrityConstraintViolationException) {
        		throw new LibraryException("Member is issued with a book so can't be deleted", e);            		
        	}else {
        		throw new LibraryException("Error Deleting member: " + e.getMessage(), e);            		
        	}   
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
        } catch (Exception e) {
            throw new LibraryException("Error while searching for members: " + e.getMessage(), e);
        }
        return members;
    }

    private void logMemberChange(Member member) throws Exception {
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
        } catch (SQLException e) {
            throw new LibraryException("Error logging member change: " + e.getMessage(), e);
        }
    }
}