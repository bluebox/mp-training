package com.library.dao;

import com.library.util.DB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDAO {

    public List<IssueRecord> getAllRecords() {
        List<IssueRecord> records = new ArrayList<>();
        //pavan
        return records;
    }
    
    public void insertIssueRecord(Connection conn, int bookId, int memberId) throws SQLException {
    	
    	String checkRowExists = "SELECT * from issue_records where BookId=?";
    	Boolean flagBoolean=false;
        try (PreparedStatement ps = conn.prepareStatement(checkRowExists)) {
        	ps.setInt(1, bookId);
       
        	flagBoolean= ps.execute();
        
        if(flagBoolean)
        {
        	String update = "UPDATE issue_records SET Status='I', IssueDate=? WHERE BookId=?";
            try (PreparedStatement updatequery = conn.prepareStatement(update)) {
            	updatequery.setDate(1, Date.valueOf(LocalDate.now()));
            	updatequery.setInt(2, bookId);
            	updatequery.executeUpdate();
            }
        }
        else {
        	String sql = "INSERT INTO issue_records(BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', CURRENT_DATE)";
            try (PreparedStatement insertquery = conn.prepareStatement(sql)) {
            	insertquery.setInt(1, bookId);
            	insertquery.setInt(2, memberId);
            	insertquery.executeUpdate();
            }
        	
        }
        }
        
        
    }

    public void updateReturn(Connection conn, int bookId ) throws SQLException {
        String update = "UPDATE issue_records SET status='R', returnDate=? WHERE bookId=? AND status='I'";
        try (PreparedStatement ps = conn.prepareStatement(update)) {
        	ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, bookId);
            ps.executeUpdate();
        }
        
    }
        
        public static void updateReturnAndLog(Connection conn, int bookId) throws SQLException {
//        	// Insert into issue_records_log (copy record)
//           
//        	String insertLog = "INSERT INTO issue_records_log SELECT * FROM issue_records WHERE bookId=? ORDER BY issueId DESC LIMIT 1";
//            try (PreparedStatement ps = conn.prepareStatement(insertLog)) {
//                ps.setInt(1, bookId);
//                ps.executeUpdate();
//            }
             // Assume latest issue record is the one to return
            String update = "UPDATE issue_records SET status='R', returnDate=? WHERE bookId=? AND status='I'";
            try (PreparedStatement ps = conn.prepareStatement(update)) {
            	ps.setDate(1, Date.valueOf(LocalDate.now()));
                ps.setInt(2, bookId);
                ps.executeUpdate();
            }
        
        
    }
}