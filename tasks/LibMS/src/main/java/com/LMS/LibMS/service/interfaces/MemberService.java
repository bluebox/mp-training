package com.LMS.LibMS.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.LibMS.model.Member;

@Service
public interface MemberService {

	List<Member> findMembers();

}
