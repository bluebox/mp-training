package Library.src.main.java.com.LibraryManagement.dao;

import java.sql.SQLException;
import java.util.List;
import Library.src.main.java.com.LibraryManagement.model.*;
// IssueRecordDAO.java
public interface IssueRecordDAO {
    void addIssueRecord(IssueRecord issueRecord) throws SQLException;
    void updateIssueRecord(IssueRecord issueRecord) throws SQLException;
    IssueRecord getIssueRecordById(int issueId) throws SQLException;
    List<IssueRecord> getAllIssueRecords() throws SQLException;
    List<IssueRecord> getActiveIssuesByMember(int memberId) throws SQLException;
    List<IssueRecord> getOverdueBooks() throws SQLException;
}

