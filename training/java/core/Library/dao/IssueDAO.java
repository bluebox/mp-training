package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.IssueRecord;
import utils.DBConnection;
import enums.Availability;

public class IssueDAO {

    public boolean issueBook(IssueRecord issue) throws Exception {
        Connection con = null;
        PreparedStatement psInsert = null;
        PreparedStatement psUpdateBook = null;
        PreparedStatement psCheck = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // Check if book is available
            String checkQuery = "SELECT Status FROM books WHERE BookId = ?";
            psCheck = con.prepareStatement(checkQuery);
            psCheck.setInt(1, issue.getBookId());
            rs = psCheck.executeQuery();

            if (rs.next()) {
                String currentStatusCode = rs.getString("Status");
                Availability bookStatus = Availability.fromCode(currentStatusCode);
                if (bookStatus == Availability.Issued) {
                    throw new Exception("Book is already issued.");
                }
            } else {
                throw new Exception("Book not found.");
            }

            // Insert issue record
            String insertSql = "INSERT INTO issue_records (BookId, MemberId, IssueDate) VALUES (?, ?, ?)";
            psInsert = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, issue.getBookId());
            psInsert.setInt(2, issue.getMemberId());
            psInsert.setDate(3, Date.valueOf(issue.getIssue_date()));
            psInsert.executeUpdate();

            // Update book status to "Issued"
            String updateBookSql = "UPDATE books SET Status = ? WHERE BookId = ?";
            psUpdateBook = con.prepareStatement(updateBookSql);
            psUpdateBook.setString(1, Availability.Issued.getCode());
            psUpdateBook.setInt(2, issue.getBookId());
            psUpdateBook.executeUpdate();

            con.commit();
            return true;
        } catch (Exception e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (psInsert != null) psInsert.close();
            if (psUpdateBook != null) psUpdateBook.close();
            if (psCheck != null) psCheck.close();
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }

    public boolean returnBook(int issueId, int bookId) throws Exception {
        Connection con = null;
        PreparedStatement psUpdateIssue = null;
        PreparedStatement psUpdateBook = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // Update return date
            String updateIssueSql = "UPDATE issue_records SET ReturnDate = CURRENT_DATE WHERE IssueId = ?";
            psUpdateIssue = con.prepareStatement(updateIssueSql);
            psUpdateIssue.setInt(1, issueId);
            psUpdateIssue.executeUpdate();

            // Update book status to "Available"
            String updateBookSql = "UPDATE books SET Status = ? WHERE BookId = ?";
            psUpdateBook = con.prepareStatement(updateBookSql);
            psUpdateBook.setString(1, Availability.Available.getCode());
            psUpdateBook.setInt(2, bookId);
            psUpdateBook.executeUpdate();

            con.commit();
            return true;
        } catch (Exception e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (psUpdateIssue != null) psUpdateIssue.close();
            if (psUpdateBook != null) psUpdateBook.close();
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }
}
