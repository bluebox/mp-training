package com.library.dao;

import java.sql.Connection;
import java.util.List;

import com.library.domain.Member;

public interface MemberDAO {
	public boolean isMemberExists(int memberId,Connection conn);
	public List<Member> getAllMembers(Connection conn);
	
}