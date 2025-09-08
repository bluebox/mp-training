package com.medplus.lms.service;

import java.util.List;
import java.util.Map;

import com.medplus.lms.domain.IssueRecordDto;
import com.medplus.lms.domain.MemberIssuedBookDto;

public interface ReportServiceInterface {
	public List<IssueRecordDto> getOverdueBooks();
	public Map<String, Long> getBooksCountPerCategory();
	public List<MemberIssuedBookDto> getMembersWithActiveIssuedBooks();
}
