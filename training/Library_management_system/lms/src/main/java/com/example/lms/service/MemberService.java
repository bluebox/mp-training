package com.example.lms.service;

import com.example.lms.model.Member;

import java.util.List;

public interface MemberService {
    int addMember(Member member);
    int updateMember(Member member);
    int deleteMember(Long id);
    Member getMemberById(Long id);
    List<Member> getAllMembers();
}
