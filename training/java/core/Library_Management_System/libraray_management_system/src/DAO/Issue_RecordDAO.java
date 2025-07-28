package DAO;

import domain.Issue_records;
import domain.checking_enum.Status_issue;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Issue_RecordDAO implements issuerecordinterface {

    private static final String URL = "jdbc:mysql://localhost:3306/library_management_system";
    private static final String USER = "devuser";
    private static final String PASSWORD = "Bobby@514";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean isMemberRegistered(int memberId) throws SQLException {
        String sql = "SELECT * FROM members WHERE MemberId = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    @Override
    public boolean isBookStatusActive(int bookId) throws SQLException {
        String sql = "SELECT * FROM books WHERE BookId = ? AND Status = 'A'";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    @Override
    public boolean isBookAvailable(int bookId) throws SQLException {
        if (isBookStatusActive(bookId)) {
            String sql = "SELECT * FROM books WHERE BookId = ? AND Availability = 'A'";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, bookId);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        }
        return false;
    }

    @Override
    public boolean issueBook(int bookId, int memberId) throws SQLException {
        if (isMemberRegistered(memberId) && isBookAvailable(bookId)) {
            String updateBookSql = "UPDATE books SET Availability = 'I' WHERE BookId = ?";
            String insertIssueSql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";
            String selectBookSql = "SELECT * FROM books WHERE BookId = ?";
            String insertBookLogSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = getConnection()) {
                conn.setAutoCommit(false);
                try (
                        PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql);
                        PreparedStatement insertIssueStmt = conn.prepareStatement(insertIssueSql);
                        PreparedStatement selectStmt = conn.prepareStatement(selectBookSql);
                        PreparedStatement insertLogStmt = conn.prepareStatement(insertBookLogSql)
                ) {
                    updateBookStmt.setInt(1, bookId);
                    updateBookStmt.executeUpdate();

                    insertIssueStmt.setInt(1, bookId);
                    insertIssueStmt.setInt(2, memberId);
                    insertIssueStmt.setDate(3, Date.valueOf(LocalDate.now()));
                    insertIssueStmt.executeUpdate();

                    selectStmt.setInt(1, bookId);
                    ResultSet rs = selectStmt.executeQuery();
                    if (rs.next()) {
                        insertLogStmt.setInt(1, rs.getInt("BookId"));
                        insertLogStmt.setString(2, rs.getString("Title"));
                        insertLogStmt.setString(3, rs.getString("Author"));
                        insertLogStmt.setString(4, rs.getString("Category"));
                        insertLogStmt.setString(5, rs.getString("Status"));
                        insertLogStmt.setString(6, rs.getString("Availability"));
                        insertLogStmt.executeUpdate();
                    }

                    conn.commit();
                    return true;
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        }
        return false;
    }

    @Override
    public boolean isBookIssued(int bookId, int memberId) throws SQLException {
        String sql = "SELECT * FROM issue_records WHERE BookId = ? AND MemberId = ? AND Status = 'I'";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.setInt(2, memberId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    @Override
    public boolean returnBook(int bookId, int memberId) throws SQLException {
        if (isBookIssued(bookId, memberId) && isMemberRegistered(memberId)) {
            String updateBookSql = "UPDATE books SET Availability = 'A' WHERE BookId = ? AND Availability = 'I'";
            String selectBookSql = "SELECT * FROM books WHERE BookId = ? AND Availability = 'A'";
            String insertBookLogSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)";
            String updateIssueSql = "UPDATE issue_records SET Status = 'R', ReturnDate = ? WHERE BookId = ? AND MemberId = ? AND Status = 'I'";
            String selectIssueSql = "SELECT * FROM issue_records WHERE BookId = ? AND MemberId = ? AND Status = 'R'";
            String insertIssueLogSql = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = getConnection()) {
                conn.setAutoCommit(false);
                try (
                        PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql);
                        PreparedStatement selectBookStmt = conn.prepareStatement(selectBookSql);
                        PreparedStatement insertBookLogStmt = conn.prepareStatement(insertBookLogSql);
                        PreparedStatement updateIssueStmt = conn.prepareStatement(updateIssueSql);
                        PreparedStatement selectIssueStmt = conn.prepareStatement(selectIssueSql);
                        PreparedStatement insertIssueLogStmt = conn.prepareStatement(insertIssueLogSql)
                ) {
                    updateBookStmt.setInt(1, bookId);
                    updateBookStmt.executeUpdate();

                    selectBookStmt.setInt(1, bookId);
                    ResultSet rs1 = selectBookStmt.executeQuery();
                    if (rs1.next()) {
                        insertBookLogStmt.setInt(1, rs1.getInt("BookId"));
                        insertBookLogStmt.setString(2, rs1.getString("Title"));
                        insertBookLogStmt.setString(3, rs1.getString("Author"));
                        insertBookLogStmt.setString(4, rs1.getString("Category"));
                        insertBookLogStmt.setString(5, rs1.getString("Status"));
                        insertBookLogStmt.setString(6, rs1.getString("Availability"));
                        insertBookLogStmt.executeUpdate();
                    }

                    updateIssueStmt.setDate(1, Date.valueOf(LocalDate.now()));
                    updateIssueStmt.setInt(2, bookId);
                    updateIssueStmt.setInt(3, memberId);
                    updateIssueStmt.executeUpdate();

                    selectIssueStmt.setInt(1, bookId);
                    selectIssueStmt.setInt(2, memberId);
                    ResultSet rs2 = selectIssueStmt.executeQuery();
                    if (rs2.next()) {
                        insertIssueLogStmt.setInt(1, rs2.getInt("IssueId"));
                        insertIssueLogStmt.setInt(2, rs2.getInt("BookId"));
                        insertIssueLogStmt.setInt(3, rs2.getInt("MemberId"));
                        insertIssueLogStmt.setString(4, rs2.getString("Status"));
                        insertIssueLogStmt.setDate(5, rs2.getDate("IssueDate"));
                        insertIssueLogStmt.setDate(6, rs2.getDate("ReturnDate"));
                        insertIssueLogStmt.executeUpdate();
                    }

                    conn.commit();
                    return true;
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        }
        return false;
    }

    @Override
    public List<Issue_records> printAllIssueRecords() throws SQLException {
        List<Issue_records> issueRecords = new ArrayList<>();
        String sql = "SELECT * FROM issue_records";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Issue_records record = new Issue_records(
                        rs.getInt("BookId"),
                        rs.getInt("MemberId"),
                        Status_issue.getstatus(rs.getString("Status")),
                        rs.getDate("IssueDate"),
                        rs.getDate("ReturnDate")
                );
                issueRecords.add(record);
            }
        }
        return issueRecords;
    }
}
