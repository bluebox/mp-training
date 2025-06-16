package service;

import dao.MemberDAO;
import model.Member;

public class MemberService {
    private MemberDAO memberDAO = new MemberDAO();

    public boolean registerMember(Member member) throws Exception {
        return memberDAO.insertMemberWithTransaction(member);
    }
}
