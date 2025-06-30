package com.librarymanagement.service;
import com.librarymanagement.model.*;
import java.util.*;

import org.springframework.stereotype.Component;
@Component
public interface IssueRecordService {
    void issueBook(int bookId, int memberId) throws Exception;
    void returnBook(int bookId, int memberId) throws Exception;
    List<IssueRecord> getAllIssueRecords() throws Exception;
    List<IssueRecord> getActiveIssuesByMember(int memberId) throws Exception;
    List<IssueRecord> getOverdueBooks() throws Exception;
}
