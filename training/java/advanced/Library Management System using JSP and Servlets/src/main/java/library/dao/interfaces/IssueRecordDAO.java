package library.dao.interfaces;

import java.util.List;

import library.exception.LibraryException;
import library.model.IssueRecord;

public interface IssueRecordDAO {
	void addIssueRecord(IssueRecord issueRecord) throws LibraryException;

	boolean updateIssueRecord(IssueRecord issueRecord) throws LibraryException;

	List<IssueRecord> getAllIssuedRecords() throws LibraryException;

	IssueRecord getIssuedRecordByIds(int bookId, int memberId) throws LibraryException;

    IssueRecord getActiveIssueRecordByBookId(int bookId) throws LibraryException;
}