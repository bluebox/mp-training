package com.lms.service.impl;

import com.lms.dao.IssueRecordDao;

import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lms.Utils.ValidatorsUtil;
import com.lms.dao.BookDao;
import com.lms.dao.DataBookDao;
import com.lms.dao.IssueRecordDaoImpl;
import com.lms.dao.MemberDao;
import com.lms.dao.MemberDaoImpl;
import com.lms.exceptions.IdNotExistException;
import com.lms.model.Book;
import com.lms.model.Issue_Records;
import com.lms.model.Member;
import com.lms.model.OverDueList;
import com.lms.model.ReportMember;
import com.lms.service.IssueLogService;

public class IssueLogServiceImpl implements IssueLogService{

	private BookDao bookDao;
	private MemberDao memberDao;
	private IssueRecordDao issueRecordDao;

	public IssueLogServiceImpl() {
		this.bookDao = new DataBookDao();
		this.memberDao = new MemberDaoImpl();
		this.issueRecordDao = new IssueRecordDaoImpl();
	}
	
	@Override
	public void addIssueRecord(Issue_Records newRecord) throws IdNotExistException{
		try {
			
			ValidatorsUtil.validateIssueRecord(newRecord);
			
			issueRecordDao.addIssueRecord(newRecord);
			bookDao.updateBookAvailability(newRecord.getBookId(), false);
		}
		catch (IdNotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	@Override
	public void returnIssuedBook(int issueId, boolean isReturned) {
		Issue_Records issue = issueRecordDao.getRecordById(issueId);
		if(issue == null) {
			return;
		}
		if(issue.getStatus() == 'I') {
			issueRecordDao.updateIssueRecord(issueId, isReturned);
		}
	}
	
	
	// Report
	public List<ReportMember> booksOfMemberReport(){
		Map<Integer,List<Integer>> map = getAllIssuedRecords().stream()
										.filter(i-> i.getStatus() == 'I')
										.collect(Collectors.groupingBy(
													i-> i.getMemberId(),
													Collectors.mapping(t -> t.getBookId() , Collectors.toList())));
		
		List<ReportMember> reportMembersList = map.entrySet().stream()
						.map((e)->{
							int memberId = e.getKey();
							List<Book> booksIds= e.getValue().stream().map((id)->bookDao.getBookById(id)).collect(Collectors.toList());
							Member member = memberDao.getMemberById(memberId);
							if(member == null)
								return null;
							return new ReportMember(memberId,memberDao.getMemberById(memberId).getMember_Name(),booksIds);
						})
						.collect(Collectors.toList());
		
		return reportMembersList;
	}
	
	@Override
	public List<Issue_Records> getAllIssuedRecords() {
		return issueRecordDao.getAllIssuedRecords();
	}
	
	public List<Issue_Records> getAllIssuedRecordLogs(){
		return issueRecordDao.getAllIssuedRecordLogs();
	}
	
	
	//this is different from report , this gives all the previous and current books taken by a member
	public List<Book> booksOfMember(int memberId){
		List<Book> books = new ArrayList<Book>();
		List<Issue_Records> logs = issueRecordDao.getAllIssuedRecords();
		
		
		books = logs.stream()
			.filter((log)->{
				return log.getMemberId() == memberId;
			})
			//.distinct()
			.map((log)->{
				 return bookDao.getBookById(log.getBookId());
			})
			.collect(Collectors.toList());
		
		
		return books;
	}

	
	
	public List<OverDueList> getOverDueBooks(){
		List<OverDueList> booksDueLists=issueRecordDao.getOverdueRecords().stream().filter(record->record.getOverDueDate().before(Date.valueOf(LocalDate.now()))).collect(Collectors.toList());
		return booksDueLists;
	}

}
