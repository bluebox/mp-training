package com.example.library.service;

import java.util.List;

import com.example.library.model.Member;
import com.example.library.model.MemberIssueDTO;

public interface MemberService {
    void addMember(Member member) throws Exception;
    void updateMember(Member member) throws Exception;
    List<Member> getAllMembers() throws Exception;
    List<MemberIssueDTO> getMembersWithActiveIssues() throws Exception;
}
