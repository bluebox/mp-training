package com.dynamic_library_management.dao;

import java.util.List;
import java.util.Map;

public interface ReportsDaoInterface {
	public Map<Object, Long> countOfBooksPerCategory();

	List<List<String>> getOverdueBooks();

	List<List<String>> getActiveIssuedBooks();
}
