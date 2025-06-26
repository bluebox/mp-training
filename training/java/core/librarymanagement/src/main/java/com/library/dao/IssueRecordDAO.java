package com.library.dao;

import java.sql.SQLException;
import java.util.List;
import com.library.model.IssueRecord;
// IssueRecordDAO.java
public interface IssueRecordDAO {
    void addIssueRecord(IssueRecord issueRecord) throws SQLException;
    void updateIssueRecord(IssueRecord issueRecord) throws SQLException;
    IssueRecord getIssueRecordById(int issueId) throws SQLException;
    List<IssueRecord> getAllIssueRecords() throws SQLException;
    List<IssueRecord> getActiveIssuesByMember(int memberId) throws SQLException;
    List<IssueRecord> getOverdueBooks() throws SQLException;
}

