package com.libraryManagement.services;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;
import com.libraryManagement.utility.pojos.Member;

public interface MemberService {

	boolean addMemberService(Member mem);

	boolean updateMemberService(Member mem) throws ClassNotFoundException, IOException, SQLException;

	List<Member> allMemberService();
}
