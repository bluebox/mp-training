package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import domain.IssueRecord;
import domain.IssueStatus;

public class IssueRecordDao {
    private Connection conn;

    public IssueRecordDao(Connection conn) {
        this.conn = conn;
    }

    public IssueRecordDao() {}

    public String issueBook(int bookId, int memberId, String issuedBy) {
        String checkBookQuery = "SELECT Status, Availability FROM books WHERE BookId = ?";
        String insertIssueQuery = "INSERT INTO issue_record (BookId, MemberId, Status, IssueDate, IssuedBy) VALUES (?, ?, ?, ?, ?)";
        String updateBookQuery = "UPDATE books SET Availability = ? WHERE BookId = ?";

        try (
            PreparedStatement checkStmt = conn.prepareStatement(checkBookQuery);
            PreparedStatement insertStmt = conn.prepareStatement(insertIssueQuery);
            PreparedStatement updateStmt = conn.prepareStatement(updateBookQuery)
        ) {
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) return "Book not found.";

            String status = rs.getString("Status");
            String availability = rs.getString("Availability");
            if (!"A".equalsIgnoreCase(status)) return "This book is inactive.";
            if (!"A".equalsIgnoreCase(availability)) return "This book is already issued.";

            insertStmt.setInt(1, bookId);
            insertStmt.setInt(2, memberId);
            insertStmt.setString(3, IssueStatus.Issued.getCode());
            insertStmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            insertStmt.setString(5, issuedBy);
            insertStmt.executeUpdate();
            updateStmt.setString(1, "I");
            updateStmt.setInt(2, bookId);
            updateStmt.executeUpdate();

            return "Book issued successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error issuing book: " + e.getMessage();
        }
    }

    public String returnBook(int bookId, int memberId) {
        String checkBookQuery = "SELECT Availability FROM books WHERE BookId = ?";
        String findIssueQuery = "SELECT IssueId FROM issue_record WHERE BookId = ? AND MemberId = ? AND Status = ? ORDER BY IssueDate DESC LIMIT 1";
        String updateIssueQuery = "UPDATE issue_record SET Status = ?, ReturnDate = ? WHERE IssueId = ?";
        String updateBookQuery = "UPDATE books SET Availability = ? WHERE BookId = ?";

        try (
            PreparedStatement checkStmt = conn.prepareStatement(checkBookQuery);
            PreparedStatement findStmt = conn.prepareStatement(findIssueQuery);
            PreparedStatement updateIssueStmt = conn.prepareStatement(updateIssueQuery);
            PreparedStatement updateBookStmt = conn.prepareStatement(updateBookQuery)
        ) {
            checkStmt.setInt(1, bookId);
            ResultSet bookRs = checkStmt.executeQuery();
            if (!bookRs.next()) return "Book not found.";

            String availability = bookRs.getString("Availability");
            if (!"I".equalsIgnoreCase(availability)) return "This book is not currently issued.";

            findStmt.setInt(1, bookId);
            findStmt.setInt(2, memberId);
            findStmt.setString(3, IssueStatus.Issued.getCode());
            ResultSet issueRs = findStmt.executeQuery();

            if (!issueRs.next()) return "No active issue record found.";
            int issueId = issueRs.getInt("IssueId");

            logIssueRecord(issueId, "RETURN");

            updateIssueStmt.setString(1, IssueStatus.Returned.getCode());
            updateIssueStmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            updateIssueStmt.setInt(3, issueId);
            updateIssueStmt.executeUpdate();

            updateBookStmt.setString(1, "A");
            updateBookStmt.setInt(2, bookId);
            updateBookStmt.executeUpdate();

            return "Book returned successfully.";
        } catch (Exception e) {
            return "Error returning book: " + e.getMessage();
        }
    }

    public List<IssueRecord> getAllIssueRecords() {
        List<IssueRecord> records = new ArrayList<>();
        String query = "SELECT * FROM issue_record";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                IssueRecord record = new IssueRecord();

                record.setIssueId(rs.getInt("IssueId"));
                record.setBookId(rs.getInt("BookId"));
                record.setMemberId(rs.getInt("MemberId"));

                String statusCode = rs.getString("Status");
                if (statusCode != null) {
                    record.setStatus(IssueStatus.fromCode(statusCode));
                }

                Timestamp issueTs = rs.getTimestamp("IssueDate");
                if (issueTs != null) {
                    record.setIssueDate(issueTs.toLocalDateTime());
                }

                Timestamp returnTs = rs.getTimestamp("ReturnDate");
                if (returnTs != null) {
                    record.setReturnDate(returnTs.toLocalDateTime());
                }

                record.setIssuedBy(rs.getString("IssuedBy"));

                records.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
    private void logIssueRecord(int issueId, String operation) {
        String selectQuery = "SELECT * FROM issue_record WHERE IssueId = ?";
        String insertLogQuery = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate, IssuedBy, Operation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            PreparedStatement insertStmt = conn.prepareStatement(insertLogQuery)
        ) {
            selectStmt.setInt(1, issueId);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                insertStmt.setInt(1, rs.getInt("IssueId"));
                insertStmt.setInt(2, rs.getInt("BookId"));
                insertStmt.setInt(3, rs.getInt("MemberId"));
                insertStmt.setString(4, rs.getString("Status"));
                insertStmt.setTimestamp(5, rs.getTimestamp("IssueDate"));
                insertStmt.setTimestamp(6, rs.getTimestamp("ReturnDate"));
                insertStmt.setString(7, rs.getString("IssuedBy"));
                insertStmt.setString(8, operation);
                insertStmt.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isBookAlreadyIssued(int bookId) {
        String checkQuery = "SELECT Availability FROM books WHERE BookId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String availability = rs.getString("Availability");
                return "I".equalsIgnoreCase(availability);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String canBookBeIssued(int bookId) {
        String query = "SELECT Status, Availability FROM books WHERE BookId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) return "Book not found.";

            String status = rs.getString("Status");
            String availability = rs.getString("Availability");

            if (!"A".equalsIgnoreCase(status)) return "Book is inactive.";
            if (!"A".equalsIgnoreCase(availability)) return "Book is already issued.";
            return "OK";
        } catch (Exception e) {
            return "Error checking book: " + e.getMessage();
        }
    }
}  