/*package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.IssueRecord;
import util.DBUtil;

public class IssueRecordDao {
	 public void issueBook(IssueRecord record) {
		 String sql = "insert into issue_records(BookId,MemberId,Status,IssueDate) values (?, ?, ?, ?, ?)";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1,record.getBookId());
	            ps.setInt(2,record.getMemberId());
	            ps.setString(3,record.getStatus().name());
	            ps.setString(4,record.getIssueDate().toString());
	            ps.executeUpdate();
	            System.out.println("Book Assigned.");
	        } 
	        catch (Exception e) {
	            System.out.println("Error assigning Book ");
	        }
	 }
	 public  Boolean isBookAvailable(int bookId) {
	        String sql = "select * from books where BookId=?";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                return true;
	            }
	        } 
	        catch (Exception e) {
	            System.out.println("Error fetching members");
	        }
	        return false;
	  }
}
*/
package dao;

import domain.IssueRecord;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDate;

public class IssueRecordDao {

    public boolean isBookAlreadyIssued(int bookId) throws Throwable {
        String query = "SELECT COUNT(*) FROM issue_records WHERE BookId=? AND Status='I'";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public void issueBook(IssueRecord record) throws Throwable {
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            // Insert into issue_records
            String insert = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insert)) {
                ps.setInt(1, record.getBookId());
                ps.setInt(2, record.getMemberId());
                ps.setString(3, record.getStatus().name());
                ps.setDate(4, Date.valueOf(record.getIssueDate()));
                ps.executeUpdate();
            }

            // Update book availability
            String updateBook = "UPDATE books SET Availability='I' WHERE BookId=?";
            try (PreparedStatement ps = con.prepareStatement(updateBook)) {
                ps.setInt(1, record.getBookId());
                ps.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }

    public void returnBook(int bookId, int memberId) throws Throwable {
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            String updateIssue = "UPDATE issue_records SET Status='R', ReturnDate=? WHERE BookId=? AND MemberId=? AND Status='I'";
            try (PreparedStatement ps = con.prepareStatement(updateIssue)) {
                ps.setDate(1, Date.valueOf(LocalDate.now()));
                ps.setInt(2, bookId);
                ps.setInt(3, memberId);
                int updated = ps.executeUpdate();
                if (updated == 0) throw new SQLException("No matching issue found to return.");
            }

            String updateBook = "UPDATE books SET Availability='A' WHERE BookId=?";
            try (PreparedStatement ps = con.prepareStatement(updateBook)) {
                ps.setInt(1, bookId);
                ps.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }
}
