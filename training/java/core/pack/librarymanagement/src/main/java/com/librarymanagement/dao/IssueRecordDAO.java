package com.librarymanagement.dao;
import com.librarymanagement.model.*;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;
// IssueRecordDAO.java
@Component
public interface IssueRecordDAO {
    void addIssueRecord(IssueRecord issueRecord) throws SQLException;
    void updateIssueRecord(IssueRecord issueRecord) throws SQLException;
    IssueRecord getIssueRecordById(int issueId) throws SQLException;
    List<IssueRecord> getAllIssueRecords() throws SQLException;
    List<IssueRecord> getActiveIssuesByMember(int memberId) throws SQLException;
    List<IssueRecord> getOverdueBooks() throws SQLException;
}

