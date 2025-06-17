package PustakaLokam.library.dao;

import PustakaLokam.library.model.IssueRecord;
import PustakaLokam.library.utilities.DBConnectivityUtility;
import PustakaLokam.library.exceptionhandler.IssueNotFoundException;
import PustakaLokam.library.enums.IssueStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDAO {

    public void insertIssue(IssueRecord record, Connection conn) throws SQLException {
        String sqlQuery = "INSERT INTO issue_records(BookID, MemberID, Status, IssueDate) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, record.getBookID());
            statement.setInt(2, record.getMemberID());
            statement.setString(3, record.getStatus().name());
            statement.setDate(4, Date.valueOf(record.getIssueDate()));
            statement.executeUpdate();

//            try (ResultSet keys = statement.getGeneratedKeys()) {
//            	System.out.println(keys.getString(0));
//                if (keys.next()) {
//                    record.setIssueID(keys.getInt(1));
//                }
//            }
        }
    }

    public void markReturnedBook(int issueID, LocalDate returnDate, Connection conn) throws SQLException {
        String sqlQuery = "UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueID=?";

        try (PreparedStatement statement = conn.prepareStatement(sqlQuery)) {
            statement.setDate(1, Date.valueOf(returnDate));
            statement.setInt(2, issueID);
            statement.executeUpdate();
        }
    }

    public List<IssueRecord> findActiveIssuesByMember(int memberId) throws SQLException {
        String sql = "SELECT * FROM issue_records WHERE Status='I' AND MemberID=?";
        List<IssueRecord> list = new ArrayList<>();

        try (Connection conn = DBConnectivityUtility.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, memberId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    IssueRecord r = new IssueRecord(
                            rs.getInt("BookID"),
                            rs.getInt("MemberID")
                    );
                    r.setIssueID(rs.getInt("IssueID"));
                    r.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                    r.setStatus(IssueStatus.valueOf(rs.getString("Status")));
                    list.add(r);
                }
            }
        }
        return list;
    }

    public IssueRecord findByID(int issueId, Connection conn) throws SQLException, IssueNotFoundException {
        String query = "SELECT * FROM issue_records WHERE IssueID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, issueId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    IssueRecord record = new IssueRecord();
                    record.setIssueID(rs.getInt("IssueID"));
                    record.setBookID(rs.getInt("BookID"));
                    record.setMemberID(rs.getInt("MemberID"));
                    record.setIssueDate(rs.getDate("IssueDate").toLocalDate());

                    String statusStr = rs.getString("Status");
                    if (statusStr != null) {
                        record.setStatus(IssueStatus.valueOf(statusStr));
                    }

                    Date returnDate = rs.getDate("ReturnDate");
                    if (returnDate != null) {
                        record.setReturnDate(returnDate.toLocalDate());
                    }

                    return record;
                } else {
                    throw new IssueNotFoundException("No issue record found for IssueID: " + issueId);
                }
            }
        }
    }
}
