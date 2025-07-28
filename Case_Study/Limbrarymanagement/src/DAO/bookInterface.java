package DAO;

import java.util.List;

import domain.Issue_records;
import domain.Member;

public interface bookInterface {
	boolean addMember(Member member) throws Exception;
	boolean updateMember(Member member) throws Exception;
	List<Member> getAllMembers() throws Exception;
	
//	boolean issueBook(int bookId, int memberId) throws Exception;
//	boolean returnBook(int bookId, int memberId) throws Exception;
//	List<Issue_records> printAllIssueRecords() throws Exception;
	
	

}
