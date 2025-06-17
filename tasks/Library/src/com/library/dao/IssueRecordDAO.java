package com.library.dao;

import com.library.DaoInterface.IssueRecordDAOInterface;
import com.library.domain.IssueRecord;
import com.library.sqlQueryLoader.sqlQueryStore;
import com.library.util.DB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDAO extends sqlQueryStore implements IssueRecordDAOInterface{

    public List<IssueRecord> getAllRecords(Connection conn) {
        List<IssueRecord> records = new ArrayList<>();
        //pavan
        return records;
    }
    
    public void insertIssueRecord(Connection conn, int bookId, int memberId) throws SQLException {
        
        String insertSQL =insertIntoIssueRecords ;
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
            insertStmt.setInt(1, bookId);
            insertStmt.setInt(2, memberId);
            insertStmt.setDate(3, Date.valueOf(LocalDate.now()));
            insertStmt.executeUpdate();
        }
    }


    public ResultSet getIssueRecord(Connection conn, int BookId) throws Exception
    {
    	ResultSet rs=null;
    	
    	try {
			
    		String query= getIssueRecordByBookId;
    		PreparedStatement statement= conn.prepareStatement(query);
    		statement.setInt(1, BookId);
    		rs=statement.executeQuery();
		} catch (Exception e) {
			throw new Exception("error while retiving issue record");
		}
    	return rs;
    	
    	
    	
    }
    
    
    public void updateReturn(Connection conn, int bookId) throws SQLException {

        String fetchSQL =getBookByIdAndStatusI;
        try (PreparedStatement fetchStmt = conn.prepareStatement(fetchSQL)) {
            fetchStmt.setInt(1, bookId);
            ResultSet rs = fetchStmt.executeQuery();

            if (rs.next()) {
                int issueId = rs.getInt("IssueId");

                
                String insertLogSQL = insertIntoIssueRecordsLog;
                try (PreparedStatement logStmt = conn.prepareStatement(insertLogSQL)) {
                    logStmt.setInt(1, rs.getInt("IssueId"));
                    logStmt.setInt(2, rs.getInt("BookId"));
                    logStmt.setInt(3, rs.getInt("MemberId"));
                    logStmt.setString(4, rs.getString("Status"));
                    logStmt.setDate(5, rs.getDate("IssueDate"));
                    logStmt.setDate(6, rs.getDate("ReturnDate"));
                    logStmt.executeUpdate();
                }

                String updateSQL = updateIssueRecordStatusToR;
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                    updateStmt.setDate(1, Date.valueOf(LocalDate.now()));
                    updateStmt.setInt(2, issueId);
                    updateStmt.executeUpdate();
                }
            } else {
                throw new SQLException("No active issued record found for bookId: " + bookId);
            }
        }
    }

}