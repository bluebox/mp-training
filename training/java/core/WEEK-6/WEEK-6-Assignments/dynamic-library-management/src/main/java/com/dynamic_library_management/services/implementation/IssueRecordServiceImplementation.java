package com.dynamic_library_management.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.dynamic_library_management.dao.IssueRecordDaoInterface;
import com.dynamic_library_management.dao.implementation.IssueRecordDaoImplementation;
import com.dynamic_library_management.domain.IssueRecord;
import com.dynamic_library_management.services.IssueRecordServiceInterface;

public class IssueRecordServiceImplementation implements IssueRecordServiceInterface {

	IssueRecordDaoInterface dao = new IssueRecordDaoImplementation();

	

	@Override
	public List<IssueRecord> getAllIssues() {
		return dao.getAllIssues();
	}

	@Override
	public List<IssueRecord> getOverdueBooks() {
		//return dao.getOverdueBooks();
		List<IssueRecord> overdue = getAllIssues().stream()
				.filter(b -> b.getReturnDate() != null) 
				.filter(b -> b.getReturnDate().isBefore(LocalDate.now().minusDays(17)))
				.collect(Collectors.toList());
		return overdue;
	}

	@Override
	public List<List<String>> getActiveIssuedBooks() {
		List<List<String>> data =  dao.getStatusTable().stream()
				.filter(row -> row.get(4).equals("A") && row.get(3).equals("I"))
				.collect(Collectors.toList());
		return data;
	}

}
