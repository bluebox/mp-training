package com.example.library.dao;

import java.util.List;

import com.example.library.exception.DatabaseException;
import com.example.library.model.Member;
import com.example.library.model.MemberIssueDTO;

public interface MemberDao {
    void addMember(Member member) throws DatabaseException;
    void updateMember(Member member) throws Exception;
    List<Member> getAllMembers() throws Exception;
    Member getMemberById(int memberId) throws Exception;
    List<MemberIssueDTO> getMembersWithActiveIssueBooks() throws Exception;
}
