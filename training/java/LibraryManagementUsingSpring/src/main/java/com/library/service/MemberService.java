package com.library.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dao.MembersDao;
import com.library.domain.Member;

@Service
public class MemberService {

    @Autowired
    private MembersDao membersDao;

    public boolean addMember(Member member) {
        return membersDao.addMember(member);
    }

    public boolean updateMember(Member member) {
        return membersDao.updateMember(member);
    }

    public List<Member> getAllMembers() {
        return membersDao.getAllMembers();
    }

    public Member getMemberById(int id) {
        return membersDao.getMemberById(id);
    }
}

