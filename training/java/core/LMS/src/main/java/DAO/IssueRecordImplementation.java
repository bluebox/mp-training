package DAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Domain.Book;
import Domain.BookAvailability;
import Domain.BookStatus;
import Domain.IssueRecord;
import Domain.IssueStatus;

public class IssueRecordImplementation implements IssueRecordInterface {
	private static final String url="jdbc:mysql://127.0.0.1:3306/librarymanagementsystem";
	private static  final String username="root";
	private static final String password="root";
	private BookImplementation book=new BookImplementation();
	
	public static  Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
			System.out.println("connection established with LibraryManagementSystemDB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	@Override
	public int createBookIssue(int BookId,  int MemberId,IssueStatus status, LocalDate issueDate,LocalDate ReturnDate) throws SQLException {		
		String query="insert into issue_records (BookId,MemberId,status,issuedate,ReturnDate) values(?,?,?,?,?)";
		Connection connection=getConnection();
		if(!checkBookIssue(BookId,MemberId)) {
		try(PreparedStatement preparestatement=connection.prepareStatement(query);
					){
			book.updateAvailability(BookId,(status==IssueStatus.RETURNED)?BookAvailability.AVAILABLE:BookAvailability.ISSUED);		
		    preparestatement.setInt(1, BookId); 
            preparestatement.setInt(2,MemberId); 
            preparestatement.setString(3,status.getType()); 
            preparestatement.setDate(4,java.sql.Date.valueOf(issueDate));
            preparestatement.setDate(5,java.sql.Date.valueOf(ReturnDate));
       
            int b=0;
	            if((b=preparestatement.executeUpdate())>0) {
			    connection.commit();
			    System.out.println("Added new Issue to the Table ");
			    return b;
	            }
		}catch(Exception e) {
			connection.rollback();
			System.out.println("Failed to add new Issue to the Table ");
		    throw new SQLException("Error occured with the message :"+e.getMessage());
		}finally {
			connection.close();
		}
		}else {
			System.out.println("Issue already exists");
		}
		return 0;
	}

	@Override
	public int returnBook(int BookId,int MemebrId) throws SQLException {
		System.out.println("Setting  dao status to: R for book " + BookId + " and member " + MemebrId);

		String returnquery="update issue_records set Status=? where BookId=? and MemberId=? and Status=?";
		
		String query="insert into issue_recordslog (IssueId,BookId,MemberId,Status,IssueDate,ReturnDate) values(?,?,?,?,?,?)";
		
		
		if(checkBookIssue(BookId,MemebrId)) {
			System.out.println("Checkbook Issue completed");
			Connection connection=getConnection();
			int b=0;
		try(PreparedStatement preparestatement=connection.prepareStatement(query);
				PreparedStatement preparestatement_return=connection.prepareStatement(returnquery);
				){
			IssueRecord record=getIssueRecord(BookId,MemebrId);
		//	System.out.println("hi this is"+record.getId());
			    preparestatement.setInt(1,record.getId() );
			    preparestatement.setInt(2, record.getBookId()); 
	            preparestatement.setInt(3,record.getMemberId()); 
	            preparestatement.setString(4,record.getStatus().getType()); 
	            preparestatement.setDate(5,java.sql.Date.valueOf(record.getIssueDate()));
	            preparestatement.setDate(6,java.sql.Date.valueOf(record.getReturnDate()));
	           
	            preparestatement_return.setString(1,"R");
	            preparestatement_return.setInt(2,BookId);
	            preparestatement_return.setInt(3,MemebrId);
	            preparestatement_return.setString(4, "I");  
	            if((b=preparestatement.executeUpdate())==1 &&((b=preparestatement_return.executeUpdate())==1)) {
	            	book.updateAvailability(BookId,BookAvailability.AVAILABLE);
		           connection.commit();
			    System.out.println("updated the Issue in the Table in dao implementation");
	            }
		}catch(Exception e) {
			connection.rollback();
			System.out.println("Failed to update the Issue to the Table ");
			 throw new SQLException("Error occured with the message :"+e.getMessage());
		}finally {
			connection.close();
		}
		}else {
			System.out.println("Issue not found");
		}
		return 0;
	}

	@Override
	public boolean checkBookIssue(int BookId,int MemberId) throws SQLException {
		System.out.println("Checking book issue for BookId: " + BookId + ", MemberId: " + MemberId);
		  
		String query="select IssueId,BookId,MemberId,status,issuedate,ReturnDate from issue_records where BookId=? and MemberId=? and Status=?";
		Connection connection=getConnection();
		try(PreparedStatement preparestatement=connection.prepareStatement(query);){
			preparestatement.setInt(1,BookId);
			preparestatement.setInt(2,MemberId );
			preparestatement.setString(3, "I");
			ResultSet rs=preparestatement.executeQuery();
			if(rs.next()) {
			return true;
			}
		}catch(Exception e){
			 throw new SQLException("Error occured with the message :"+e.getMessage());
		}
		finally {
			connection.close();
		}
		return false;
	}
	@Override
	public List<IssueRecord> getAllIssueRecords() throws SQLException{
		String getString ="select IssueId,BookId,MemberId,status,issuedate,ReturnDate from issue_records";
	    Connection connection=getConnection();
		try(PreparedStatement preparestatement=connection.prepareStatement(getString);){
			ResultSet rs=preparestatement.executeQuery();
			List<IssueRecord> records=new ArrayList<>();
			while(rs.next()) {
			  records.add(new IssueRecord(rs.getInt("issueid"),rs.getInt("BookId"),rs.getInt("MemberId"),IssueStatus.getIssueStatus(rs.getString("status")),LocalDate.parse(""+rs.getDate("IssueDate")),LocalDate.parse(""+rs.getDate("ReturnDate"))));
			}
			return records;
		}catch(Exception e) {
			System.out.println("Failed to get the Issue from the Table ");
			 throw new SQLException("Error occured with the message :"+e.getMessage());
		}finally {
			connection.close();
		}
	}
	@Override
	public IssueRecord getIssueRecord(int bookId,int MemberId) throws SQLException {
		String query="select IssueId,BookId,MemberId,Status,IssueDate,ReturnDate from issue_records where BookId=? and MemberId=? and Status=?";
		Connection connection=getConnection();
		try(PreparedStatement preparestatement=connection.prepareStatement(query);){
			preparestatement.setInt(1,bookId);
			preparestatement.setInt(2,MemberId );
			preparestatement.setString(3, "I");
			ResultSet rs=preparestatement.executeQuery();
			if(rs.next()) {
			return new IssueRecord(rs.getInt("issueid"),rs.getInt("BookId"),rs.getInt("MemberId"),IssueStatus.getIssueStatus(rs.getString("status")),LocalDate.parse(""+rs.getDate("IssueDate")),LocalDate.parse(""+rs.getDate("ReturnDate")));
			}
		}catch(Exception e) {
			System.out.println("Failed to find the Issue of the Table ");
			 throw new SQLException("Error occured with the message :"+e.getMessage());
		}finally {
			connection.close();
		}
		return null;
	}

}
