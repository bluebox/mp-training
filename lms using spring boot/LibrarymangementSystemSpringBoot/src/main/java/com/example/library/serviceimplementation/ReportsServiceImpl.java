package com.example.library.serviceimplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.constants.BookCategory;
import com.example.library.constants.IssueRecordStatus;
import com.example.library.daoImpl.BookDaoImpl;
import com.example.library.daoImpl.IssueRecordDaoImpl;
import com.example.library.daoImpl.MemberDaoImpl;
import com.example.library.domain.Book;
import com.example.library.domain.IssueRecord;
import com.example.library.domain.Member;
import com.example.library.service.Reports;

@Service
public class ReportsServiceImpl implements Reports {
	@Autowired
	private BookDaoImpl bookDaoImpl;

	@Autowired
	private MemberDaoImpl memberDaoImpl;

	@Autowired
	private IssueRecordDaoImpl issueRecordDaoImpl;

	@Override
	public List<IssueRecord> getOverdueBooks(int days) {
		return issueRecordDaoImpl.getAllIssues().stream().filter(r -> r.getStatus() == IssueRecordStatus.ISSUED)
				.filter(r -> r.getIssueDate().isBefore(LocalDate.now().minusDays(days))).collect(Collectors.toList());
	}

	@Override
	public Map<BookCategory, Long> getBookCountPerCategory() {
		return bookDaoImpl.findAllBooks().stream()
				.collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
	}

	@Override
	public List<Member> getMembersWithActiveIssuedBooks() {
		List<IssueRecord> activeIssues = issueRecordDaoImpl.getAllIssues().stream()
				.filter(r -> r.getStatus() == IssueRecordStatus.ISSUED).collect(Collectors.toList());

		List<Integer> memberIds = activeIssues.stream().map(IssueRecord::getMemberId).distinct()
				.collect(Collectors.toList());

		return memberDaoImpl.findAllMembers().stream().filter(m -> memberIds.contains(m.getMemberId()))
				.collect(Collectors.toList());
	}

}