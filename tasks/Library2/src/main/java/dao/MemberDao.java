package dao;

import model.Member;
import model.MemberIssueDTO;
import Exception.DatabaseException;
import java.util.List;

public interface MemberDao {
    void addMember(Member member) throws DatabaseException;
    void updateMember(Member member) throws Exception;
    List<Member> getAllMembers() throws Exception;
    Member getMemberById(int memberId) throws Exception;
    List<MemberIssueDTO> getMembersWithActiveIssueBooks() throws Exception;
}
