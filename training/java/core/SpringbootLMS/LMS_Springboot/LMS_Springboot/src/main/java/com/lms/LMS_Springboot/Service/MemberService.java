package com.lms.LMS_Springboot.Service;

import com.lms.LMS_Springboot.DAO.MemberDAO;
import com.lms.LMS_Springboot.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    public Member getMemberById(int id) {
        return memberDAO.getMemberById(id);
    }

    public int addMember(Member member) {
        return memberDAO.addMember(member);
    }

    public int updateMember(int id, Member member) {
        return memberDAO.updateMember(id, member);
    }
}
