package com.library.library_management_system.dao;

import java.util.List;

import com.library.library_management_system.domain.IssueBookDetails;

public interface ReportsDaoInterface {

	List<String> getAllBookCategories();

	List<IssueBookDetails> getAllIssueBookDetails();
}
