package dev.tulasidhar.lms.service.impl;

import dev.tulasidhar.lms.DAO.IssueRecordDao;
import dev.tulasidhar.lms.DAO.impl.DataBookDao;
import dev.tulasidhar.lms.DAO.impl.IssueRecordDaoImpl;
import dev.tulasidhar.lms.DAO.impl.MemberDaoImpl;

import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tulasidhar.lms.Utils.ValidatorsUtil;
import dev.tulasidhar.lms.DAO.*;
import dev.tulasidhar.lms.Exceptions.IdNotExistException;
import dev.tulasidhar.lms.model.Book;
import dev.tulasidhar.lms.model.*;

import dev.tulasidhar.lms.service.IssueLogService;


@Service
public class IssueLogServiceImpl implements IssueLogService{
	
	@Autowired
	private BookDao bookDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private IssueRecordDao issueRecordDao;
	
	@Autowired
	private ValidatorsUtil validator;
	

	public IssueLogServiceImpl() {
		this.bookDao = new DataBookDao();
		this.memberDao = new MemberDaoImpl();
		this.issueRecordDao = new IssueRecordDaoImpl();
	}
	
	@Override
	public void addIssueRecord(Issue_Records newRecord) throws IdNotExistException{
		try {
			validator.validateIssueRecord(newRecord);
			
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
				try {
				 return bookDao.getBookById(log.getBookId());
				}catch(RuntimeException e) {
					return null;
				}
			})
			.filter(e->e!=null)
			.collect(Collectors.toList());
		
		
		return books;
	}

	
	public List<OverDueList> getOverDueBooks(){
		List<OverDueList> overDueList = issueRecordDao.getAllIssuedRecords().stream()
											.filter(record->{
												return record.getStatus()=='I' && record.getIssueDate().toLocalDate().plusDays(30).isBefore(LocalDate.now()) ;
											})
											.map(record->{
												try {
													OverDueList listItem = new OverDueList();
													listItem.setBookId(record.getBookId());
													listItem.setIssueId(record.getIssueId());
													listItem.setMemberName(memberDao.getMemberById(record.getMemberId()).getMember_Name());
													listItem.setTitle(bookDao.getBookById(record.getBookId()).getBook_Title());
													listItem.setOverDueDate(record.getIssueDate().toLocalDate().plusDays(30));
													return listItem;
												}catch(RuntimeException e){
													return null;
												}
											}).filter(r->r!=null)
											.toList();
		return overDueList;
	}

}
