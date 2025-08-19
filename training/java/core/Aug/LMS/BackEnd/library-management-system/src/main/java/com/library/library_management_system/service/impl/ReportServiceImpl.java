package com.library.library_management_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library_management_system.domain.CustomCategoryCount;
import com.library.library_management_system.domain.CustomReportDetails;
import com.library.library_management_system.repository.ReportRepository;
import com.library.library_management_system.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	private final ReportRepository reportRepository;

	public ReportServiceImpl(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	@Override
	public List<CustomCategoryCount> getBookCountByCategory() {

		return reportRepository.getBookCountByCategory();
	}

	@Override
	public List<CustomReportDetails> getActiveIssuedBooks() {

		return reportRepository.getActiveIssuedBooks();
	}

	@Override
	public List<CustomReportDetails> getOverDueBooks() {

		return reportRepository.getOverDueBooks();
	}
}
