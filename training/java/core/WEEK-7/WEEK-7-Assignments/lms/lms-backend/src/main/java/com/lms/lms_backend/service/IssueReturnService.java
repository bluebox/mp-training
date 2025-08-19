package com.lms.lms_backend.service;

import java.util.List;

import com.lms.lms_backend.exception.DatabaseException;
import com.lms.lms_backend.exception.InvalidOperationException;
import com.lms.lms_backend.exception.ResourceNotFoundException;
import com.lms.lms_backend.model.IssueRecord;

public interface IssueReturnService {
    void issueBook(IssueRecord issue) throws DatabaseException, InvalidOperationException, ResourceNotFoundException;
    void returnBook(IssueRecord issue) throws DatabaseException, InvalidOperationException, ResourceNotFoundException;
    List<IssueRecord> getAllIssues() throws DatabaseException;
}
