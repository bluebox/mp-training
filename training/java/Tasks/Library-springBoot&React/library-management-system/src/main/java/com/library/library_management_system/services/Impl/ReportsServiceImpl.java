package com.library.library_management_system.services.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library_management_system.dao.ReportsDaoInterface;
import com.library.library_management_system.domain.IssueBookDetails;
import com.library.library_management_system.exceptions.ApiException;
import com.library.library_management_system.services.ReportsServiceInterface;

@Service
public class ReportsServiceImpl implements ReportsServiceInterface {

	@Autowired
	private ReportsDaoInterface reportsDao;

	@Override
	public Map<Object, Long> countOfBooksPerCategory() {
		List<String> categories = reportsDao.getAllBookCategories();
		if (categories == null || categories.isEmpty()) {
			throw new ApiException("No book categories found.");
		}
		return categories.stream().collect(Collectors.groupingBy(c -> c, Collectors.counting()));
	}

	@Override
	public List<IssueBookDetails> getOverdueBooks() {
		List<IssueBookDetails> allIssues = reportsDao.getAllIssueBookDetails();
		if (allIssues == null || allIssues.isEmpty()) {
			throw new ApiException("No issue records found.");
		}
		return allIssues.stream().filter(issue -> issue.getReturnDate() == null).filter(
				issue -> issue.getIssueDate() != null && issue.getIssueDate().isBefore(LocalDate.now().minusDays(17)))
				.collect(Collectors.toList());
	}

	@Override
	public List<IssueBookDetails> getActiveIssuedBooks() {
		List<IssueBookDetails> allIssues = reportsDao.getAllIssueBookDetails();
		if (allIssues == null || allIssues.isEmpty()) {
			throw new ApiException("No issue records found.");
		}
		return allIssues.stream().filter(issue -> "A".equals(issue.getBookStatus()))
				.filter(issue -> "I".equals(issue.getIssueStatus())).collect(Collectors.toList());
	}

}
