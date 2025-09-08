package dev.kaushik.library.service;

import java.util.List;

import dev.kaushik.library.model.IssueRecord;
import dev.kaushik.library.model.Member;

public interface IssueService {

	Integer issueBook(int bookId, int memberId, String issuedBy);

	boolean returnBook(int bookId, String returnedBy);

	List<IssueRecord> getAllIssuedRecords();

	List<IssueRecord> getOverdueBooks();

	List<Member> getMembersWithActiveBooks();
}