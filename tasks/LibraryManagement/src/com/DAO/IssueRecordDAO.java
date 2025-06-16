package com.DAO;

import com.models.IssueRecord;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IssueRecordDAO {
	 private static final String URL = "jdbc:mysql://localhost:3306/library";
	 private static final String USER = "pavan";
	 private static final String PASSWORD = "Pavan@02";
    public boolean insert(IssueRecord record) {
        String sql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, record.getBookId());
            stmt.setInt(2, record.getMemberId());
            stmt.setString(3, String.valueOf(record.getStatus()));
            stmt.setDate(4, record.getIssueDate());
            stmt.setDate(5, record.getReturnDate()); // can be null if issuing

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isBookAvailable(int id) {
    	String sql = "SELECT * FROM issue_records i Where i.BookId = ? and i.Status='I'";

   try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = conn.prepareStatement(sql)) {

       stmt.setInt(1,id);

       ResultSet rs=stmt.executeQuery();
       return !rs.next();

   } catch (Exception e) {
       e.printStackTrace();
       return false;
	}
    }
    public boolean returnBook(int bookId, int memberId, Date returnDate) {
        String sql = "UPDATE issue_records " +
                     "SET Status = 'R', ReturnDate = ? " +
                     "WHERE BookId = ? AND MemberId = ? AND Status = 'I'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, returnDate);
            stmt.setInt(2, bookId);
            stmt.setInt(3, memberId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
