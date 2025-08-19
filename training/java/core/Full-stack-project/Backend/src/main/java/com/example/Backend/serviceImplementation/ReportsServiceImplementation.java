package com.example.Backend.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.constants.BookCategory;
import com.example.Backend.constants.IssueRecordStatus;
import com.example.Backend.daoImplementation.BookDaoImpl;
import com.example.Backend.daoImplementation.IssueRecordDaoImpl;
import com.example.Backend.daoImplementation.MemberDaoImpl;
import com.example.Backend.domain.Book;
import com.example.Backend.domain.IssueRecord;
import com.example.Backend.domain.Member;
import com.example.Backend.service.Reports;

@Service
public class ReportsServiceImplementation implements Reports {
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
