package com.webLibraryManagementSystem.dao;

import java.util.List;
import java.util.Map;

import com.webLibraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;

public interface ReportsDao {

	List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws DatabaseOperationException;

	List<CustomActiveIssuedBooks> getOverDueBooks() throws DatabaseOperationException;

	Map<String, Integer> getBookCountByCategory() throws DatabaseOperationException;
}
