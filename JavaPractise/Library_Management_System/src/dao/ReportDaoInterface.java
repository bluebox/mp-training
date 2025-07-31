package dao;

import java.util.List;
import java.util.Map;

import domain.IssueRecord;
import domain.Member;
import exceptions.ManagementException;

public interface ReportDaoInterface {
	public List<IssueRecord> getOverdueBooks();

	public Map<String, Long> getBooksCountPerCategory() throws ManagementException;

	public List<Member> getMembersWithActiveIssuedBooks();
}
