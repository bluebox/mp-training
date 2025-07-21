package gym.membership_Management.dao;

import gym.membership_Management.model.Member;
import gym.membership_Management.model.MemberStatus;
import java.util.List;

public interface MemberDao {
    Member addMember(Member member);
    Member getMemberById(int id);
    List<Member> getAllMembers();
    boolean updateMemberStatus(int id, MemberStatus newStatus, String reason);
}