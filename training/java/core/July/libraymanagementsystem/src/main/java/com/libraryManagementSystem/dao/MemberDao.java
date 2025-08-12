package com.libraryManagementSystem.dao;

import java.util.List;

import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;

public interface MemberDao {

	int RegisterMember(Member member)
			throws DatabaseConnectionException, StatementPreparationException, DatabaseOperationException;

	int UpdateMember(Member newMember, Member oldMember)
			throws DatabaseConnectionException, StatementPreparationException;

	List<Member> getAllMembers() throws DatabaseConnectionException, StatementPreparationException;

	public void memberLog(Member member) throws DatabaseConnectionException, StatementPreparationException;

	int deleteMember(Member memberData) throws DatabaseConnectionException, StatementPreparationException;

	boolean getMemberByMobile(Long mobile) throws DatabaseConnectionException, StatementPreparationException;

	boolean getMemberByEmail(String email) throws DatabaseConnectionException, StatementPreparationException;

}
