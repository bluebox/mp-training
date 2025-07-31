package service;

import java.util.List;
import java.util.Map;

import domain.IssueRecord;
import domain.Member;
import exceptions.ManagementException;

public interface ReportServiceInterface {
	public List<IssueRecord> getOverdueBooks();

	public Map<String, Long> getBooksCountPerCategory() throws ManagementException;

	public List<Member> getMembersWithActiveIssuedBooks();
}
