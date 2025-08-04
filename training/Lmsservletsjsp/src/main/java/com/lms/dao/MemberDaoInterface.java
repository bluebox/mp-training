package com.lms.dao;


import java.util.List;

import com.lms.model.Member;

public interface MemberDaoInterface {

     boolean addMember(Member member);

      boolean updateMember(Member member);

       Member getMemberById(int id);
       List<Member> getAllMembers();

      int generateNewMemberId();
}