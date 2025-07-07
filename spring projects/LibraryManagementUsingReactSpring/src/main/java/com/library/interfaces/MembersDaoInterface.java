package com.library.interfaces;


import java.util.List;
import com.library.domain.Member;

public interface MembersDaoInterface {

    public boolean addMember(Member member);

    public boolean updateMember(Member member);

    public List<Member> getAllMembers();

    public Member getMemberById(int id);

    public boolean existsById(int memberId);
}

