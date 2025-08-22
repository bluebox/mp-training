package com.example.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.daoImpl.IssueRecordDaoImpl;
import com.example.library.domain.Book;
import com.example.library.domain.IssueRecord;
import com.example.library.serviceimplementation.IssueRecordServiceImpl;

@RestController
@RequestMapping("/issues")
public class IssueRecordController {

	@Autowired
	private IssueRecordServiceImpl issueRecordService;

	@Autowired
	IssueRecordDaoImpl issueRecordDao;

	@PostMapping("/issue")
	public String issueBook(@RequestBody IssueRecord issueRecord) {
		int memberId = issueRecord.getMemberId();
		int bookId = issueRecord.getBookId();
		return issueRecordService.issueBook(bookId, memberId);
	}

	@PostMapping("/return")
	public String returnBook(@RequestBody IssueRecord issueRecord) {
		int memberId = issueRecord.getMemberId();
		int bookId = issueRecord.getBookId();
		return issueRecordService.returnBook(memberId, bookId);
	}

	@GetMapping
	public List<IssueRecord> getAllIssues() {
		return issueRecordService.getAllIssues();
	}

	@GetMapping("/issue/{memberId}")
	public List<Book> getIssuedBooksForMember(@PathVariable int memberId) {
		return issueRecordDao.getIssuedBooksForMember(memberId);
	}
}
