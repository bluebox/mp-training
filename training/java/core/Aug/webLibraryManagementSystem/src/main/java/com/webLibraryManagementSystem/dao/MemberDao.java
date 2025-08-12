package com.webLibraryManagementSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystem.exceptions.InvalidException;

public interface MemberDao {

	int RegisterMember(Member member) throws InvalidException;

	int UpdateMember(Member newMember, Member oldMember) throws InvalidException;

	List<Member> getAllMembers() throws InvalidException;

	public void memberLog(Member member, Connection con, PreparedStatement stmt) throws InvalidException;

	int deleteMember(Member memberData) throws InvalidException;

	boolean getMemberByMobile(Long mobile) throws InvalidException;

	boolean getMemberByEmail(String email) throws InvalidException;

	Member getMemberId(int memberId) throws InvalidException;

}
