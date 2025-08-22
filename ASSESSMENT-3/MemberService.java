package Service;

import DataAccessObject.MemberDAO;
import Model.Member;

import java.util.List;

public class MemberService {
    private MemberDAO memberDAO = new MemberDAO();

    public void registerMember(Member member) { memberDAO.addMember(member); }

    public List<Member> getAllMembers() { return memberDAO.getAllMembers(); }

    public void updateMember(Member member) { memberDAO.updateMember(member); }

    public void deleteMember(int id) { memberDAO.deleteMember(id); }
}
