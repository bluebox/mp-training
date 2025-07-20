package Controller;

import Model.Member;
import Service.MemberService;

import java.sql.SQLException;
import java.util.List;

public class MemberController {
    private MemberService memberService = new MemberService();

    public void addNewMember(String name, String joinDate, String membershipType) throws SQLException {
        Member member = new Member();
        member.setName(name);
        member.setJoinDate(joinDate);
        member.setMembershipType(membershipType);
        memberService.registerMember(member);
    }

    public void showAllMembers() {
        List<Member> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (Member m : members) {
                System.out.println(m.getId() + " | " + m.getName() + " | " + m.getJoinDate() + " | " + m.getMembershipType());
            }
        }
    }

    public void updateMember(int id, String name, String joinDate, String type) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setJoinDate(joinDate);
        member.setMembershipType(type);
        memberService.updateMember(member);
    }

    public void deleteMember(int id) {
        memberService.deleteMember(id);
    }
}
