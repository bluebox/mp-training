package com.lms.lms_backend.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.lms_backend.dao.ReportsDao;
import com.lms.lms_backend.model.CategoryCount;
import com.lms.lms_backend.model.ReportDetails;
import com.lms.lms_backend.service.ReportsService;

@Service
public class ReportsServiceImplementation implements ReportsService {
	private final ReportsDao reportsDao;

	@Autowired
	public ReportsServiceImplementation(ReportsDao reportsDao) {
		this.reportsDao = reportsDao;
	}

	@Override
	public List<CategoryCount> getBookCountByCategory() {

		return reportsDao.getBookCountByCategory();
	}

	@Override
	public List<ReportDetails> getActiveIssuedBooks() {

		return reportsDao.getActiveIssuedBooks();
	}

	@Override
	public List<ReportDetails> getOverdueBooks() {

		return reportsDao.getOverdueBooks();
	}
}
