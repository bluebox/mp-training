package com.libraryManagementSystem.services;

import java.util.List;

import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidMemberDataException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;

public interface MemberService {

	int registerMember(Member member) throws DatabaseConnectionException, StatementPreparationException,
			InvalidMemberDataException, DatabaseOperationException;

	List<Member> getMembers() throws DatabaseConnectionException, StatementPreparationException;

	int updateMember(Member newMember, Member oldMember)
			throws DatabaseConnectionException, StatementPreparationException, InvalidMemberDataException;

	int deleteMember(Member memberData) throws DatabaseConnectionException, StatementPreparationException;
}
