package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pojo.IssueRecord;
import pojo.IssueRecord.Status;
import utility.DBUtil;
import utility.DBqueries;

public class IssueRecordDAO {

    public void issueBook(IssueRecord issueRecord) throws SQLException {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DBqueries.INSERT_ISSUE_RECORD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, issueRecord.getBookid());
            preparedStatement.setInt(2, issueRecord.getMemberid());
            preparedStatement.setDate(3, new java.sql.Date(issueRecord.getIssuedate().getTime()));
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    issueRecord.setIssueid(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void returnBook(int issueId, Date returnDate) throws SQLException {
        try (Connection connection = DBUtil.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement insertLogStmt = connection.prepareStatement(DBqueries.INSERT_ISSUE_LOG)) {
                insertLogStmt.setInt(1, issueId);
                insertLogStmt.executeUpdate();
            }

            try (PreparedStatement updateStmt = connection.prepareStatement(DBqueries.UPDATE_ISSUE_STATUS_RETURN)) {
                updateStmt.setDate(1, new java.sql.Date(returnDate.getTime()));
                updateStmt.setInt(2, issueId);
                updateStmt.executeUpdate();
            }

            connection.commit(); 
        }
    }

    public List<IssueRecord> getAllIssuedRecords() throws SQLException {
        List<IssueRecord> issueRecords = new ArrayList<>();
        String sql = "SELECT * FROM issue_records";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                IssueRecord record = new IssueRecord();
                record.setIssueid(resultSet.getInt("issueid"));
                record.setBookid(resultSet.getInt("bookid"));
                record.setMemberid(resultSet.getInt("memberid"));
                record.setStatus(Status.valueOf(resultSet.getString("status").equals("I") ? "ISSUED" : "RETURNED"));
                record.setIssuedate(resultSet.getDate("issuedate"));
                record.setReturndate(resultSet.getDate("returndate"));
                issueRecords.add(record);
            }
        }
        return issueRecords;
    }

    public IssueRecord getIssueRecordById(int issueId) throws SQLException {
        IssueRecord record = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM issue_records WHERE issueid = ?")) {
            preparedStatement.setInt(1, issueId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    record = new IssueRecord();
                    record.setIssueid(resultSet.getInt("issueid"));
                    record.setBookid(resultSet.getInt("bookid"));
                    record.setMemberid(resultSet.getInt("memberid"));
                    record.setStatus(Status.valueOf(resultSet.getString("status").equals("I") ? "ISSUED" : "RETURNED"));
                    record.setIssuedate(resultSet.getDate("issuedate"));
                    record.setReturndate(resultSet.getDate("returndate"));
                }
            }
        }
        return record;
    }
}