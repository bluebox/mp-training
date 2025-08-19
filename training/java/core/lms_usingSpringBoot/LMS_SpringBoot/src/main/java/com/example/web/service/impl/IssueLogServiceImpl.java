package com.example.web.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.annotations.ValidateIssueRecords;
import com.example.web.dao.BookDao;
import com.example.web.dao.IssueRecordDao;
import com.example.web.dao.MemberDao;
import com.example.web.model.Book;
import com.example.web.model.Issue_Records;
import com.example.web.model.Member;
import com.example.web.model.OverDueList;
import com.example.web.model.ReportMember;
import com.example.web.service.IssueLogService;

@Service
public class IssueLogServiceImpl implements IssueLogService{
	@Autowired
	private BookDao bookDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private IssueRecordDao issueRecordDao;
	
	@Override
	@ValidateIssueRecords
	public void addIssueRecord(Issue_Records newRecord) {
		issueRecordDao.addIssueRecord(newRecord);
		bookDao.updateBookAvailability(newRecord.getBookId(), false);
	}

	@Override
	public List<Issue_Records> getAllIssuedRecords() {
		return issueRecordDao.getAllIssuedRecords();
	}

	@Override
	@ValidateIssueRecords
	public void returnIssuedBook(int issueId, boolean isReturned) {
		Issue_Records issue = issueRecordDao.getRecordById(issueId);
		if(issue == null) {
			return;
		}
		if(issue.getStatus().equals("I")) {
			issueRecordDao.updateIssueRecord(issueId, isReturned);
		}
	}

	@Override
	public List<ReportMember> booksOfMemberReport() {
		Map<Integer,List<Integer>> map = getAllIssuedRecords().stream()
				.filter(i-> i.getStatus().equals("I"))
				.collect(Collectors.groupingBy(
							i-> i.getMemberId(),
							Collectors.mapping(t -> t.getBookId() , Collectors.toList())));
		List<ReportMember> reportMembersList = map.entrySet().stream().map((e)->{
			int memberId = e.getKey();
			List<Book> booksIds= e.getValue().stream().map((id)->bookDao.getBookById(id)).collect(Collectors.toList());
			Member member = memberDao.getMemberById(memberId);
			if(member == null)
				return null;
			return new ReportMember(memberId,memberDao.getMemberById(memberId).getMember_Name(),booksIds);
		}).collect(Collectors.toList());
		return reportMembersList;
	}
	
	public List<Issue_Records> getAllIssuedRecordLogs(){
		return issueRecordDao.getAllIssuedRecordLogs();
	}

	@Override
	public List<Book> booksOfMember(int memberId) {
		List<Book> books = new ArrayList<Book>();
		List<Issue_Records> logs = issueRecordDao.getAllIssuedRecords();
		books = logs.stream()
			.filter((log)->{
				return log.getMemberId() == memberId;
			})
			.map((log)->{
				 return bookDao.getBookById(log.getBookId());
			})
			.collect(Collectors.toList());
		return books;
	}

	@Override
	public Issue_Records getRecordById(int IssueId) {
		Issue_Records issuedrecord=issueRecordDao.getRecordById(IssueId);
		return issuedrecord;
	}
	
	@Override
	public List<OverDueList> getOverDueBooks() {
		List<OverDueList> booksDueLists=issueRecordDao.getOverdueRecords().stream().filter(record->record.getOverDueDate().before(Date.valueOf(LocalDate.now()))).collect(Collectors.toList());
		return booksDueLists;
	}
}
