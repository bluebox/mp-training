package service;

import model.Member;
import model.MemberIssueDTO;
import java.util.List;

public interface MemberService {
    void addMember(Member member) throws Exception;
    void updateMember(Member member) throws Exception;
    List<Member> getAllMembers() throws Exception;
    List<MemberIssueDTO> getMembersWithActiveIssues() throws Exception;
}
