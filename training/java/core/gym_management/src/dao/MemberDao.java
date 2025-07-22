package dao;

import java.util.List;

import model.Member;
import model.MemberStatus;

public interface MemberDao {
    Member addMember(Member member);
    Member getMemberById(int id);
    List<Member> getAllMembers();
    boolean updateMemberStatus(int id, MemberStatus newStatus, String reason);
}