package com.LMS.LibMS.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.Member;

@Repository
public interface MemberRepository {

	List<Member> findMembers();

}
