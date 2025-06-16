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
	BookDaoImpl bookDao=new BookDaoImpl();
	
	@Override
	public boolean issueBook(int bookId, int memberId) {
		try {
			Connection con=DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.INSERT_TO_ISSUE_RECORDS);
			pst.setInt(1, bookId);
			pst.setInt(2, memberId);
			pst.setDate(3, Date.valueOf(LocalDate.now()));
			pst.executeUpdate();
			bookDao.updateBook(bookId,'I');
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean verifyBookAndMember(int bookId, int memberId) {
		try {
			Connection con=DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.GET_BOOK_WITH_ID);
			pst.setInt(1, bookId);
			ResultSet rs=pst.executeQuery();
			PreparedStatement pst1=con.prepareStatement(DBQueries.GET_MEMBER_WITH_ID);
			pst1.setInt(1, memberId);
			ResultSet rs1=pst1.executeQuery();
			if(rs.next() && rs1.next()) {
				if(rs.getString(5).equals("A") && rs.getString(6).equals("A")) {
					return true;
				}else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	@Override
	public boolean verifyRecord(int issuedId) {
		try {
			Connection con=DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.GET_ISSUE_RECORD_WITH_ID);
			pst.setInt(1, issuedId);
			ResultSet rs=pst.executeQuery();
			if(!rs.next()) return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void addToIssueRecordLog(int issueId) {
		try {
			Connection con=DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.GET_ISSUE_RECORD_WITH_ID);
			pst.setInt(1, issueId);
			ResultSet rs=pst.executeQuery();
			rs.next();
			pst=con.prepareStatement(DBQueries.INSERT_TO_ISSUE_RECORDS_LOG);
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
		PreparedStatement pst=con.prepareStatement(DBQueries.UPDATE_ISSUE_RECORD);
		pst.setDate(1, Date.valueOf(LocalDate.now()));
		pst.setInt(2, issueId);
		pst.executeUpdate();
	}
	
	@Override
	public boolean returnBook(int issueId) throws Exception {
		Connection con=DBConnection.connectDB();
		PreparedStatement pst;
		pst = con.prepareStatement(DBQueries.GET_ISSUE_RECORD_WITH_ID);
		pst.setInt(1,issueId);
		ResultSet rs=pst.executeQuery();
		if(rs.next()) {
			if(rs.getDate(6)!=null) return false;
			bookDao.updateBook(rs.getInt(2), 'A');
			updateIssueRecord(issueId);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<IssueRecord> viewAllIssuedRecords() throws Exception {
		ArrayList<IssueRecord> arr= new ArrayList<>();
		Connection con=DBConnection.connectDB();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(DBQueries.GET_ALL_ISSUE_RECORDS);
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
		ResultSet rs=st.executeQuery(DBQueries.GET_ALL_ISSUE_RECORDS_LOG);
		while(rs.next()) {
			Character ch=rs.getString(4).charAt(0);
			IssueRecord ir=new IssueRecord(rs.getInt(1),rs.getInt(2),rs.getInt(3),ch,rs.getDate(5),rs.getDate(6));
			arr.add(ir);
		}
		return arr;
	}

	
	

}
