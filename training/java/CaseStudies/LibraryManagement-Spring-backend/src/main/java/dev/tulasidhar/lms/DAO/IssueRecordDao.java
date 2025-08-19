package dev.tulasidhar.lms.DAO;

import java.util.List;


import dev.tulasidhar.lms.model.Issue_Records;

public interface IssueRecordDao {
	int addIssueRecord(Issue_Records newRecord);
	public void updateIssueRecord(int issueId , boolean isReturned);
	List<Issue_Records> getAllIssuedRecords();
	public Issue_Records getRecordById(int issueId);
	
	public int addIssue_Records_Log(Issue_Records record);	
	List<Issue_Records> getAllIssuedRecordLogs();
	
}
