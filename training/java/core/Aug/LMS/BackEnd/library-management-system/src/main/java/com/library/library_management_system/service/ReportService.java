package com.library.library_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library_management_system.domain.CustomCategoryCount;
import com.library.library_management_system.domain.CustomReportDetails;
import com.library.library_management_system.repository.ReportRepository;

@Service
public class ReportService {

	private final ReportRepository reportRepository;

	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	public List<CustomCategoryCount> getBookCountByCategory() {

		return reportRepository.getBookCountByCategory();
	}

	public List<CustomReportDetails> getActiveIssuedBooks() {

		return reportRepository.getActiveIssuedBooks();
	}

	public List<CustomReportDetails> getOverDueBooks() {

		return reportRepository.getOverDueBooks();
	}
}
