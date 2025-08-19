package com.lms.lms_backend.dao;

import java.util.List;

import com.lms.lms_backend.model.IssueRecord;

public interface IssueReturnDao {
    void issueBook(IssueRecord issue);
    void returnBook(IssueRecord issue);
    List<IssueRecord> getAllIssues();
	int logIssue(int issueId);
}
