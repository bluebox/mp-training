package com.library.test.dao;

import com.library.dao.IssueBookDAO;
import com.library.domain.IssueRecord;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class IssueBookDAOTest {

    private static Connection connection;
    private static IssueBookDAO issueBookDAO;

    @BeforeClass
    public static void setupDatabaseConnection() throws Exception {
    	connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library",
                "root",
                "Root@32!"
            );

            issueBookDAO = new IssueBookDAO();
            String sql = "DELETE FROM issue_records";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            sql = "DELETE FROM issue_log";
            PreparedStatement ps1 = connection.prepareStatement(sql);
            ps1.executeUpdate();
//            issueBookDAO.setTestMode(true);
    }

    @Test
    public void testNull() throws Exception{
    	IssueRecord issueRecord =null;
		assertEquals(false,issueBookDAO.issueBook(issueRecord));
//		fail();
	}
    
    @Test
    public void testInsertion() throws Exception{
    	IssueRecord issueRecord =new IssueRecord(1,1);
		assertEquals(true,issueBookDAO.issueBook(issueRecord));
//		fail();
	}
    
    @Test
    public void testInsertionSize() throws Exception{
    	IssueRecord issueRecord =new IssueRecord(2,2);
    	issueBookDAO.issueBook(issueRecord);
    	
//    	To check if the data is correctly stored in the issue_records table
    	String sql = "SELECT COUNT(*) AS total FROM issue_records";
    	Statement st = connection.createStatement();  
    	ResultSet rs= st.executeQuery(sql);
    	int x=0;
    	if(rs.next()) {
    		x=rs.getInt("total");
    	}
    	assertEquals(3,x);
	}
    
    @Test
    public void testInsertionSizeIssueLog() throws Exception{
    	IssueRecord issueRecord =new IssueRecord(3,3);
    	issueBookDAO.issueBook(issueRecord);
    	
//    	To check if the data is correctly stored in the issue_log table
    	String sql1 = "SELECT COUNT(*) AS total FROM issue_log";
    	Statement st1 = connection.createStatement();  
    	ResultSet rs1= st1.executeQuery(sql1);
    	int y=0;
    	if(rs1.next()) {
    		y=rs1.getInt("total");
    	}
		assertEquals(1,y);
//		fail();
	}
}

