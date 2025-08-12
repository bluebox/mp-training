package com.libraryManagementSystem.dao;

import java.util.List;

import com.libraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.libraryManagementSystem.domain.CustomCategoryCount;
import com.libraryManagementSystem.domain.CustomOverDueBooks;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;

public interface ReportsDao {

	List<CustomCategoryCount> getBookCountByCategory() throws DatabaseOperationException;

	List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws DatabaseOperationException;

	List<CustomOverDueBooks> getOverDueBooks() throws DatabaseOperationException;
}
