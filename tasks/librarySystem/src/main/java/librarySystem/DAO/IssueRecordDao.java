package librarySystem.DAO;

import librarySystem.Utils.DbConnection;
import model.IssueRecordPojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDao {

    public boolean isBookIssued(int bookId) {
        String sql = "SELECT COUNT(*) FROM issue_records WHERE BookId = ? AND Status = 'I'";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking if book is already issued: " + e.getMessage());
        }
        return false;
    }

    public boolean issueBook(IssueRecordPojo record) {
        if (isBookIssued(record.getBookId())) {
            System.out.println("This book is already issued and not returned.");
            return false;
        }

        String sql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, 'I', ?, NULL)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            stmt.setInt(1, record.getBookId());
            stmt.setInt(2, record.getMemberId());
            stmt.setDate(3, record.getIssueDate());
            stmt.addBatch();

            stmt.executeBatch();
            conn.commit();

            System.out.println("Book issued successfully.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error issuing book: " + e.getMessage());
            try (Connection conn = DbConnection.getConnection()) {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback failed: " + ex.getMessage());
            }
        }
        return false;
    }

    public boolean returnBook(int bookId, int memberId, Date returnDate) {
        String sql = "UPDATE issue_records SET Status = 'R', ReturnDate = ? WHERE BookId = ? AND MemberId = ? AND Status = 'I'";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            stmt.setDate(1, returnDate);
            stmt.setInt(2, bookId);
            stmt.setInt(3, memberId);
            stmt.addBatch();

            int[] updateCounts = stmt.executeBatch();

            if (updateCounts[0] == 0) {
                System.out.println("No issued record found to return.");
                conn.rollback();
                return false;
            }

            conn.commit();
            System.out.println("Book returned successfully.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error returning book: " + e.getMessage());
            try (Connection conn = DbConnection.getConnection()) {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback failed: " + ex.getMessage());
            }
        }
        return false;
    }

    public List<IssueRecordPojo> getAllRecords() {
        List<IssueRecordPojo> records = new ArrayList<>();
        String sql = "SELECT * FROM issue_records";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                IssueRecordPojo record = new IssueRecordPojo();
                record.setIssueId(rs.getInt("IssueId"));
                record.setBookId(rs.getInt("BookId"));
                record.setMemberId(rs.getInt("MemberId"));
                record.setStatus(rs.getString("Status").charAt(0));
                record.setIssueDate(rs.getDate("IssueDate"));
                record.setReturnDate(rs.getDate("ReturnDate"));
                records.add(record);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving issue records: " + e.getMessage());
        }

        return records;
    }
}
