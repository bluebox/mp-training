package com.example.library.serviceimplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.constants.IssueRecordStatus;
import com.example.library.daoImpl.BookDaoImpl;
import com.example.library.daoImpl.IssueRecordDaoImpl;
import com.example.library.domain.IssueRecord;
import com.example.library.service.IssueRecordService;

@Service
public class IssueRecordServiceImpl implements IssueRecordService {

	@Autowired
	private IssueRecordDaoImpl issueRepo;
	
	@Autowired
	private BookDaoImpl bookDao;

	@Override
	public String issueBook(int bookId, int memberId) {
		IssueRecord issue = new IssueRecord();
		issue.setBookId(bookId);
		issue.setMemberId(memberId);
		issue.setStatus(IssueRecordStatus.ISSUED);
		issue.setIssueDate(LocalDate.now());
		issue.setReturnDate(null);

		issueRepo.issueBook(issue);
		return "Book issued Successfully";

	}

	@Override
	public String returnBook(int memberId, int bookId) {

		IssueRecord issue = issueRepo.findActiveIssue(memberId, bookId);
		if (issue == null) {
			return "no Active books";
		}

		issueRepo.returnBook(issue.getIssueId(), LocalDate.now());
		bookDao.updateAvailability(bookId,"A");
		return "Book returned successfully";

	}

	@Override
	public List<IssueRecord> getAllIssues() {
		return issueRepo.getAllIssues();
	}
}
