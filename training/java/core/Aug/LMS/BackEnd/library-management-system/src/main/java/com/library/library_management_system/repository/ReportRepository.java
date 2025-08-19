package com.library.library_management_system.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.domain.CustomCategoryCount;
import com.library.library_management_system.domain.CustomReportDetails;
import com.library.library_management_system.mapper.CustomReportDetailsRowMapper;
import com.library.library_management_system.utils.SQLQueries;

@Repository
public class ReportRepository {
	private final JdbcTemplate jdbcTemplate;

	public ReportRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<CustomCategoryCount> getBookCountByCategory() {

		return jdbcTemplate.query(SQLQueries.GET_BOOK_BY_CATEGORY,
				(rs, rowNum) -> new CustomCategoryCount(rs.getString("category"), rs.getInt("book_count")));

	}

	public List<CustomReportDetails> getActiveIssuedBooks() {

		return jdbcTemplate.query(SQLQueries.GET_ACTIVE_ISSUED_BOOKS, new CustomReportDetailsRowMapper());

	}

	public List<CustomReportDetails> getOverDueBooks() {

		return jdbcTemplate.query(SQLQueries.GET_OVER_DUE_BOOKS, new CustomReportDetailsRowMapper());

	}
}
