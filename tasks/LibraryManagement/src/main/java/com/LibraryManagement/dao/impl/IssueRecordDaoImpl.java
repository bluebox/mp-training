package com.LibraryManagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.LibraryManagement.dao.IssueRecordDao;
import com.LibraryManagement.utilites.DBConnection;
import com.LibraryManagement.utilites.DBQueries;
import com.LibraryManagement.utilites.pojos.IssueRecord;

public class IssueRecordDaoImpl implements IssueRecordDao{
	BookDaoImpl bd=new BookDaoImpl();
	
	@Override
	public boolean issueBook(int bookId, int memberId) {
		try {
			Connection con=DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.insertToIssueRecords);
			pst.setInt(1, bookId);
			pst.setInt(2, memberId);
			pst.setDate(3, Date.valueOf(LocalDate.now()));
			pst.executeUpdate();
			bd.updateBook(bookId,'I');
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean verifyBookAndMember(int bookId, int memberId) {
		try {
			Connection con=DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.getBookWithId);
			pst.setInt(1, bookId);
			ResultSet rs=pst.executeQuery();
			PreparedStatement pst1=con.prepareStatement(DBQueries.getMemberWithId);
			pst1.setInt(1, memberId);
			ResultSet rs1=pst1.executeQuery();
			if(rs.next() && rs1.next()) {
				if(rs.getString(5).equals("A") && rs.getString(6).equals("A")) {
					return true;
				}else {
					System.out.println("Book is not Available");
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean verifyRecord(int issuedId) {
		try {
			Connection con=DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.getIssueRecordWithId);
			pst.setInt(1, issuedId);
			pst.executeQuery();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void addToIssueRecordLog(int issueId) {
		try {
			Connection con=DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.getIssueRecordWithId);
			pst.setInt(1, issueId);
			ResultSet rs=pst.executeQuery();
			rs.next();
			pst=con.prepareStatement(DBQueries.insertToIssueRecordsLog);
			pst.setInt(1, rs.getInt(1));
			pst.setInt(2,rs.getInt(2));
			pst.setInt(3, rs.getInt(3));
			pst.setString(4,String.valueOf(rs.getString(4)));
			pst.setDate(5,rs.getDate(5));
			pst.setDate(6,rs.getDate(6));
			pst.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateIssueRecord(int issueId) throws Exception {
		addToIssueRecordLog(issueId);
		Connection con=DBConnection.connectDB();
		PreparedStatement pst=con.prepareStatement(DBQueries.updateIssueRecord);
		pst.setDate(1, Date.valueOf(LocalDate.now()));
		pst.setInt(2, issueId);
		pst.executeUpdate();
	}
	
	@Override
	public boolean returnBook(int issueId) throws Exception {
		Connection con=DBConnection.connectDB();
		PreparedStatement pst;
		pst = con.prepareStatement(DBQueries.getIssueRecordWithId);
		pst.setInt(1,issueId);
		ResultSet rs=pst.executeQuery();
		if(rs.next()) {
			bd.updateBook(rs.getInt(2), 'A');
			updateIssueRecord(issueId);
		}
		return false;
	}

	@Override
	public ArrayList<IssueRecord> viewAllIssuedRecords() throws Exception {
		ArrayList<IssueRecord> arr= new ArrayList<>();
		Connection con=DBConnection.connectDB();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(DBQueries.getAllIssueRecords);
		while(rs.next()) {
			Character ch=rs.getString(4).charAt(0);
			IssueRecord ir=new IssueRecord(rs.getInt(1),rs.getInt(2),rs.getInt(3),ch,rs.getDate(5),rs.getDate(6));
			arr.add(ir);
		}
		return arr;
	}

	@Override
	public ArrayList<IssueRecord> viewIssueRecordLog() throws Exception{
		ArrayList<IssueRecord> arr= new ArrayList<>();
		Connection con=DBConnection.connectDB();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(DBQueries.getAllIssueRecordsLog);
		while(rs.next()) {
			Character ch=rs.getString(4).charAt(0);
			IssueRecord ir=new IssueRecord(rs.getInt(1),rs.getInt(2),rs.getInt(3),ch,rs.getDate(5),rs.getDate(6));
			arr.add(ir);
		}
		return arr;
	}

	
	

}
