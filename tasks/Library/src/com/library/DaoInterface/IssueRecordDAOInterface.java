package com.library.DaoInterface;
import com.library.domain.IssueRecord;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IssueRecordDAOInterface {

    public abstract List<IssueRecord> getAllRecords(Connection conn);

    public abstract void insertIssueRecord(Connection conn, int bookId, int memberId) throws SQLException;

    public abstract ResultSet getIssueRecord(Connection conn, int bookId) throws Exception;

    public abstract void updateReturn(Connection conn, int bookId) throws SQLException;
}
