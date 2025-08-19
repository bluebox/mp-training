package com.library.library_management_system.repository;

import java.util.List;

import com.library.library_management_system.domain.CustomCategoryCount;
import com.library.library_management_system.domain.CustomReportDetails;

public interface ReportRepository {

	public List<CustomCategoryCount> getBookCountByCategory();

	public List<CustomReportDetails> getActiveIssuedBooks();

	public List<CustomReportDetails> getOverDueBooks();
}
