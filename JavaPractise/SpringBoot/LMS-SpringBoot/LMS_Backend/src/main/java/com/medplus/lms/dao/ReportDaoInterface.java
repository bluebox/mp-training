package com.medplus.lms.dao;

import java.util.List;
import java.util.Map;

import com.medplus.lms.domain.IssueRecordDto;
import com.medplus.lms.domain.MemberIssuedBookDto;

public interface ReportDaoInterface {
	public List<IssueRecordDto> getOverdueBooks();
	public Map<String, Long> getBooksCountPerCategory();
	public List<MemberIssuedBookDto> getMembersWithActiveIssuedBooks();
}
