package com.library.dao;

import java.sql.*;
import java.util.*;
import java.sql.Date;
import java.time.LocalDate;

import com.library.controller.IssueRecord;
import com.library.util.AlertMsg;
import com.library.util.DBConnect;

public class IssueRecordDao {
    private Connection getConnection() throws Exception {
        return DBConnect.getConnection();
    }

    public void issueBook(IssueRecord record) {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            String s = "insert into IssueRecords (BookId, MemberId, BookStatus, IssueDate) values (?, ?, 'I', ?)";
            try (PreparedStatement ps = conn.prepareStatement(s)) {
                ps.setInt(1, record.getBookId());
                ps.setInt(2, record.getMemberId());
                ps.setDate(3, Date.valueOf(record.getIssueDate()));
                ps.executeUpdate();
            }

            String updateBook = "update Books set Availability='I' where BookId=?";
            try (PreparedStatement ps = conn.prepareStatement(updateBook)) {
                ps.setInt(1, record.getBookId());
                ps.executeUpdate();
            }

            conn.commit();
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
    }

    public void returnBook(int issueId, LocalDate returnDate) {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            String s = "update IssueRecords set BookStatus='R', ReturnDate=? where IssueId=?";
            try (PreparedStatement ps = conn.prepareStatement(s)) {
                ps.setDate(1,Date.valueOf(returnDate));
                ps.setInt(2, issueId);
                ps.executeUpdate();
            }

            String getBookId = "select BookId from IssueRecords where IssueId=?";
            int bookId = -1;
            try (PreparedStatement ps = conn.prepareStatement(getBookId)) {
                ps.setInt(1, issueId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    bookId = rs.getInt("BookId");
                }
            }

            String updateBook = "update Books set Availability='A' where BookId=?";
            try (PreparedStatement ps = conn.prepareStatement(updateBook)) {
                ps.setInt(1, bookId);
                ps.executeUpdate();
            }

            conn.commit();
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
    }

    public List<IssueRecord> getAllIssuedRecords() {
        List<IssueRecord> list = new ArrayList<>();
        String s = "select * from IssueRecords";
        try (Connection conn = getConnection(); 
        		Statement stmt = conn.createStatement(); 
        		ResultSet rs = stmt.executeQuery(s)) {
            while (rs.next()) {
                list.add(new IssueRecord(
                    rs.getInt("IssueId"),
                    rs.getInt("BookId"),
                    rs.getInt("MemberId"),
                    rs.getString("BookStatus").charAt(0),
                    rs.getDate("IssueDate")!=null?rs.getDate("IssueDate").toLocalDate():null,
                    rs.getDate("ReturnDate")!=null?rs.getDate("ReturnDate").toLocalDate():null
                ));
            }
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
        return list;
    }
    public int getBookIdByIssueId(int issueId) throws Exception{
    	String s="select BookId from IssueRecords where IssueId=?";
    	try(Connection conn=getConnection();
    			PreparedStatement ps=conn.prepareStatement(s)){
    		ps.setInt(1,issueId);
    		ResultSet rs=ps.executeQuery();
    		if(rs.next()) {
    			return rs.getInt("BookId");
    		}
    	}catch(Exception e) {
    		AlertMsg.showError(e.getMessage());
    	}
    	return -1;
    }
}