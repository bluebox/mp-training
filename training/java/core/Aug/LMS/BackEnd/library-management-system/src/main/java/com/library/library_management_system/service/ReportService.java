package com.library.library_management_system.service;

import java.util.List;

import com.library.library_management_system.domain.CustomCategoryCount;
import com.library.library_management_system.domain.CustomReportDetails;

public interface ReportService {

	public List<CustomCategoryCount> getBookCountByCategory();

	public List<CustomReportDetails> getActiveIssuedBooks();

	public List<CustomReportDetails> getOverDueBooks();
}
