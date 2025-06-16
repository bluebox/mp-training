package PustakaLokam.library.dao;

import PustakaLokam.library.model.IssueRecord;
import PustakaLokam.library.utilities.DBConnectivityUtility;
import PustakaLokam.library.exceptionhandler.IssueNotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDAO {
	
	public IssueRecord insertIssue(IssueRecord record, Connection conn) throws SQLException{
		String sqlQuery = "INSERT INTO issue_records(BookID, MemberID, Status, IssueDate) VALUES (?,?,? ,?)";
		
		try (PreparedStatement statement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)){
			statement.setInt(1, record.getbookID());
			statement.setInt(2, record.getmemberID());
			statement.setString(3, String.valueOf(record.getStatus()));
			statement.setDate(4,  Date.valueOf(record.getIssueDate()));
			statement.executeUpdate();
			
			try(ResultSet keys = statement.getGeneratedKeys()){
				if(keys.next()) {
					record.setissueID(keys.getInt(1));
				}
			}
			return record;
		}
	}
	
	public void markReturnedBooks(int issueID, LocalDate returnDate, Connection conn) throws SQLException{
		String sqlQuery = "UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueID=?";
		try(PreparedStatement statement = conn.prepareStatement(sqlQuery)){
			statement.setDate(1, Date.valueOf(returnDate));
			statement.setInt(2, issueID);
            statement.executeUpdate();
		}
	}
	
	public List<IssueRecord> findOverdueBooks(LocalDate cutoff) throws SQLException {
        String sql = "SELECT * FROM issue_records WHERE Status='I' AND IssueDate < ?";
        List<IssueRecord> list = new ArrayList<>();
        try (Connection conn = DBConnectivityUtility.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(cutoff));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    IssueRecord r = new IssueRecord(
                        rs.getInt("BookID"), rs.getInt("MemberID"));
                    r.setissueID(rs.getInt("IssueID"));
                    r.setStatus(rs.getString("Status").charAt(0));
                    r.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                    list.add(r);
                }
            }
        }
        return list;
    }
	
	public List<IssueRecord> findStatusByMember(int memberId) throws SQLException {
        String sql = "SELECT * FROM issue_records WHERE Status='I' AND MemberID=?";
        List<IssueRecord> list = new ArrayList<>();
        try (Connection conn = DBConnectivityUtility.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, memberId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    IssueRecord r = new IssueRecord(
                        rs.getInt("BookID"), rs.getInt("MemberID"));
                    r.setissueID(rs.getInt("IssueID"));
                    r.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                    list.add(r);
                }
            }
        }
        return list;
    }
	
	public IssueRecord findByID(int issueId, Connection conn) throws SQLException {
        String query = "SELECT IssueID, BookID, MemberID, IssueDate, DueDate, ReturnDate FROM issue_records WHERE IssueID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, issueId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    IssueRecord record = new IssueRecord();
                    record.setissueID(rs.getInt("IssueID"));
                    record.setbookID(rs.getInt("BookID"));
                    record.setmemberID(rs.getInt("MemberID"));
                    record.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                    record.setReturnDate(null);
                    java.sql.Date returnDate = rs.getDate("ReturnDate");
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
