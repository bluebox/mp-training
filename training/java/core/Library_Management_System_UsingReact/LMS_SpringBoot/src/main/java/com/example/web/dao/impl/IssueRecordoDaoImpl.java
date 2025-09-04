package com.example.web.dao.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.web.dao.BookDao;
import com.example.web.dao.IssueRecordDao;
import com.example.web.dao.MemberDao;
import com.example.web.exceptions.DBConstrainsException;
import com.example.web.exceptions.IdNotExistException;
import com.example.web.model.Book;
import com.example.web.model.Issue_Records;
import com.example.web.model.OverDueList;

@Repository
public class IssueRecordoDaoImpl implements IssueRecordDao{
	@Autowired
	private BookDao bookDao;
	@Autowired
	private MemberDao memberDao;
	private JdbcTemplate template;
	private RowMapper<Issue_Records> mapper;

	public JdbcTemplate getTemplate() {
		return template;
	}
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public int addIssueRecord(Issue_Records newRecord) {
		int rowsAffected = 0;
	    int bookId=newRecord.getBookId();
	    int memberId=newRecord.getMemberId();
		try{
			if((bookDao.getBookById(bookId)!=null) && (memberDao.getMemberById(memberId)!=null)) {
			    Book book=bookDao.getBookById(bookId);
				char status=book.getBook_Status();
				char available=book.getBook_Availability();
				
				if((!String.valueOf(status).equals("I")) && (!String.valueOf(available).equals("I"))) {
					String query="INSERT INTO issue_records(BookId,MemberId,Status,IssueDate,ReturnDate) VALUES (?,?,?,?,?)";
					rowsAffected = template.update(query
							,newRecord.getBookId()
							,newRecord.getMemberId()
							,newRecord.getStatus()
							,newRecord.getIssueDate()
							,newRecord.getReturnDate()
					);
					if (rowsAffected == 0) {
			        	 throw new SQLException("SQL ERROR: Failed to insert issueRecord");
					}
				}
				else {
					throw new DBConstrainsException("Book or Member not existed");
				}
			} 
		}
		catch (SQLException | DBConstrainsException e) {
			System.out.println(e.getMessage());
		}	
		return rowsAffected;
	}

	@Override
	public void updateIssueRecord(int issueId, boolean isReturned) {
		String query="UPDATE issue_records SET Status=?,ReturnDate=? WHERE IssueId=?";
		int rowsAffected = template.update(query,(isReturned ? "R" : "I"),(isReturned ? java.sql.Date.valueOf(LocalDate.now()) : null),issueId);
		try {
			if(rowsAffected == 0) {
				throw new SQLException("Error In Updating the Issue Record");
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Issue_Records> getAllIssuedRecords() {
		List<Issue_Records> issue_Records;
		String query = "SELECT IssueId,BookId,MemberId,Status,IssueDate,ReturnDate FROM issue_records";
		mapper = (rs, rowNum)->{
			Issue_Records issue_Record = new Issue_Records(rs.getInt("IssueId"),rs.getInt("BookId"),rs.getInt("MemberId"),rs.getString("Status"),rs.getDate("IssueDate"),rs.getDate("ReturnDate"));
			return issue_Record;
		};
		issue_Records = template.query(query, mapper);
		try {
			if(issue_Records == null) {
				throw new SQLException("Error In Fetching IssuedRecords");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return issue_Records;
	}

	@Override
	public int addIssue_Records_Log(Issue_Records record) {
		String query="INSERT INTO issue_records_log(IssueId,BookId,MemberId,Status,IssueDate,ReturnDate) VALUES (?,?,?,?,?,?)";
		int rowsAffected = template.update(query
				,record.getIssueId()
				,record.getBookId()
				,record.getMemberId()
				,record.getStatus()
				,record.getIssueDate()
				,record.getReturnDate()
			);
		try {
			if(rowsAffected == 0) {
				throw new SQLException("SQL ERROR: Failed to insert issueRecordlogs");
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected;
	}

	@Override
	public Issue_Records getRecordById(int issueId) {
		String query="SELECT IssueId,BookId,MemberId,Status,IssueDate,ReturnDate FROM issue_records WHERE IssueId=?";
		Issue_Records issue_record=null;
		try {
			issue_record= template.queryForObject(query, mapper, issueId);
			if(issue_record == null) {
				throw new IdNotExistException("Issue Id not Found");
			}
		}
		catch (IdNotExistException e) {
			System.out.println(e.getMessage());
		}
		return issue_record;
	}

	@Override
	public List<OverDueList> getOverdueRecords() {
		String query = "SELECT ir.IssueId, ir.BookId, b.Title, m.Name AS Member, DATE_ADD(ir.IssueDate, INTERVAL 30 DAY) AS DueDate FROM issue_records ir JOIN members m ON ir.MemberId = m.MemberId JOIN books b ON ir.BookId = b.BookId WHERE ir.Status = 'I'";
		RowMapper<OverDueList> mapper = (rs, rowNum) -> {
			OverDueList overdueRecord = new OverDueList(rs.getInt("IssueId"), rs.getInt("BookId"), rs.getString("Title"), rs.getString("Member"), rs.getDate("DueDate"));
			return overdueRecord;
		};
		List<OverDueList> duerecordsList = template.query(query, mapper);
		return duerecordsList;
	}

	@Override
	public List<Issue_Records> getAllIssuedRecordLogs() {
		String query="SELECT IssueId,BookId,MemberId,Status,IssueDate,ReturnDate FROM issue_records_log";
		mapper = (rs, rowNum)->{
			Issue_Records issue_Record = new Issue_Records(rs.getInt("IssueId"),rs.getInt("BookId"),rs.getInt("MemberId"),rs.getString("Status"),rs.getDate("IssueDate"),rs.getDate("ReturnDate"));
			return issue_Record;
		};
		List<Issue_Records> issue_Records_Logs = template.query(query, mapper);
		return issue_Records_Logs;
	}

}
