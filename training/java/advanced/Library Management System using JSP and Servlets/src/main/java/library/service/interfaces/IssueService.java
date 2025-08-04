package library.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import library.model.IssueRecord;
import library.model.Member;

public interface IssueService {
	void issueBook(int bookId, int memberId, LocalDateTime issueDate, String issuedBy);

	void returnBook(int bookId, String returnedBy);

	List<IssueRecord> getAllIssuedRecords();

	List<IssueRecord> getOverdueBooks(int dueDays);

	List<Member> getMembersWithActiveBooks();

}