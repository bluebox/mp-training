package com.lms.LMS_Springboot.Service;

import com.lms.LMS_Springboot.DAO.MemberDAO;
import com.lms.LMS_Springboot.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    public boolean addMember(Member member) {
        return memberDAO.addMember(member);
    }

    public boolean updateMember(Member member, int memberId) throws SQLException {
        return memberDAO.updateMember(member, memberId);
    }

    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    public Member getMemberById(int memberId) {
        return memberDAO.getById(memberId);
    }
}
