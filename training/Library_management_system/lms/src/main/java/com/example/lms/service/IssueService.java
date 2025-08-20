package com.example.lms.service;

import com.example.lms.model.IssueRecord;

import java.util.List;

public interface IssueService {
    String issueBook(Long bookId, Long memberId);
    String returnBook(Long issueId);
    IssueRecord getIssueById(Long id);
    List<IssueRecord> getAllIssues();
}
