package com.lms.lms_backend.service;

import java.util.List;

import com.lms.lms_backend.model.CategoryCount;
import com.lms.lms_backend.model.ReportDetails;

public interface ReportsService {

	public List<CategoryCount> getBookCountByCategory();

	public List<ReportDetails> getActiveIssuedBooks();

	public List<ReportDetails> getOverdueBooks();
}
