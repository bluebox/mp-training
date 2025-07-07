package com.library.interfaces;

import java.util.List;
import com.library.domain.IssueRecord;

public interface IssueRecordDaoInterface {

    public boolean issueBook(IssueRecord issueRecord);

    public boolean returnBook(IssueRecord issueRecord);

    public List<IssueRecord> getIssuedBooksOverOneMonthOld();

    public List<IssueRecord> getAllIssuedRecords();

    public List<IssueRecord> getActiveIssuedBooks();

    public boolean alreadyIssued(int bookId, int memberId);
}
