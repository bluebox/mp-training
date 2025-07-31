package com.library.service.interfaces;

import com.library.model.IssueRecord;
import java.util.List;

public interface IssueRecordService {
    boolean issueBook(IssueRecord record) throws Exception;
    boolean returnBook(int issueId) throws Exception;
    IssueRecord getActiveIssueByBookId(int bookId) throws Exception;
    List<IssueRecord> getAllIssues() throws Exception;
}
