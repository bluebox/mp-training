package com.LMS.LibMS.repository.interfaces;

import java.util.List;

import com.LMS.LibMS.model.IssueRecord;

public interface IssueRecordRepository {
	void addIssueRecord(IssueRecord issueRecord);

	boolean updateIssueRecord(IssueRecord issueRecord) throws Exception;

	List<IssueRecord> getAllIssuedRecords();

	IssueRecord getActiveIssueRecordByBookId(int bookId);

	boolean logIssueRecord(Integer bookId);

	List<IssueRecord> getIssuedRecordsWithBookIds(List<Integer> bookIds);
}