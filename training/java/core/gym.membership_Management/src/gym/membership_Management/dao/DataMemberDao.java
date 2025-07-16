package gym.membership_Management.dao;

import gym.membership_Management.model.Member;
import gym.membership_Management.model.MemberStatus;
import java.util.ArrayList;
import java.util.List;

public class DataMemberDao implements MemberDao {
    private List<Member> members = new ArrayList<>();

    @Override
    public Member addMember(Member member) {
        members.add(member);
        return member;
    }

    @Override
    public Member getMemberById(int id) {
        return members.stream()
                      .filter(member -> member.getMembershipId() == id)
                      .findFirst()
                      .orElse(null);
    }

    @Override
    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public boolean updateMemberStatus(int id, MemberStatus newStatus, String reason) {
        Member memberToUpdate = getMemberById(id);
        if (memberToUpdate != null) {
            memberToUpdate.setStatus(newStatus);
            memberToUpdate.setRemovalReason(reason);
            return true;
        }
        return false;
    }
}