package com.library.library_management_system.services;

import java.util.List;
import java.util.Map;

import com.library.library_management_system.domain.IssueBookDetails;

public interface ReportsServiceInterface {

	Map<Object, Long> countOfBooksPerCategory();

	List<IssueBookDetails> getOverdueBooks();

	List<IssueBookDetails> getActiveIssuedBooks();
}
