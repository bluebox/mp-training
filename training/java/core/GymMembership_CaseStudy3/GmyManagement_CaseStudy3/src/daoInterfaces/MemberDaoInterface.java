package daoInterfaces;

import java.sql.SQLException;
import java.util.List;

import model.Member;

public interface MemberDaoInterface {
	void insertMember(Member member) throws SQLException;
	List<Member> getAllMembers() throws SQLException;
	boolean memberExists(String memberId) throws SQLException;
	void deleteMember(String memberId) throws SQLException;

}
