package main.com.library.services;

import main.com.library.dao.MemberDAO;
import main.com.library.domain.Member;


public class MemberService {
    MemberDAO memberDAO = new MemberDAO();
    public boolean updateMember(Member member) {
        return memberDAO.updateMember(member);
    }
}