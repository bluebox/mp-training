package com.example.web.dao;

import java.util.List;
import com.example.web.model.Issue_Records;
import com.example.web.model.OverDueList;

public interface IssueRecordDao {
	int addIssueRecord(Issue_Records newRecord);
	public void updateIssueRecord(int issueId , boolean isReturned);
	List<Issue_Records> getAllIssuedRecords();
	public int addIssue_Records_Log(Issue_Records record);
	public Issue_Records getRecordById(int issueId);
	public List<OverDueList> getOverdueRecords();
	List<Issue_Records> getAllIssuedRecordLogs();
}
