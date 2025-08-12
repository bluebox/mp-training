package com.libraryManagementSystem.services.impl;

import java.util.List;

import com.libraryManagementSystem.dao.MemberDao;
import com.libraryManagementSystem.dao.impl.MemberDaoImpl;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidMemberDataException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;
import com.libraryManagementSystem.services.MemberService;
import com.libraryManagementSystem.utilities.Validations;

public class MemberServiceImpl implements MemberService {

	private final MemberDao memberDao = new MemberDaoImpl();

	@Override
	public int registerMember(Member member) throws DatabaseConnectionException, StatementPreparationException,
			InvalidMemberDataException, DatabaseOperationException {

		if (!Validations.isValidString(member.getName())) {
			throw new InvalidMemberDataException("Name must be at least 3 characters long");
		}

		if (!Validations.isValidEmail(member.getEmail())) {
			throw new InvalidMemberDataException("Invalid email format");
		}

		if (!Validations.isValidMobile(member.getMobile())) {
			throw new InvalidMemberDataException("Mobile number must be 10 digits");
		}

		if (member.getGender() == null) {
			throw new InvalidMemberDataException("Gender must not be null");
		}

		if (!Validations.isValidAdress(member.getAddress())) {
			throw new InvalidMemberDataException("Address must not be empty");
		}

		if (memberDao.getMemberByMobile(member.getMobile())) {

			throw new InvalidMemberDataException("Mobile Number Already Exist");
		}

		if (memberDao.getMemberByEmail(member.getEmail())) {

			throw new InvalidMemberDataException("Email Already Exist");
		}

		return memberDao.RegisterMember(member);

	}

	@Override
	public List<Member> getMembers() throws DatabaseConnectionException, StatementPreparationException {

		return memberDao.getAllMembers();
	}

	@Override
	public int deleteMember(Member memberData) throws DatabaseConnectionException, StatementPreparationException {
		return memberDao.deleteMember(memberData);

	}

	@Override
	public int updateMember(Member newMember, Member oldMember)
			throws DatabaseConnectionException, StatementPreparationException, InvalidMemberDataException {

		if (!Validations.isValidString(newMember.getName())) {
			throw new InvalidMemberDataException("Name must be at least 3 characters long");
		}

		if (!Validations.isValidEmail(newMember.getEmail())) {
			throw new InvalidMemberDataException("Invalid email format");
		}

		if (!Validations.isValidMobile(newMember.getMobile())) {
			throw new InvalidMemberDataException("Mobile number must be 10 digits");
		}

		if (newMember.getGender() == null) {
			throw new InvalidMemberDataException("Gender must not be null");
		}

		if (!Validations.isValidAdress(newMember.getAddress())) {
			throw new InvalidMemberDataException("please enter valid adress");
		}

		return memberDao.UpdateMember(newMember, oldMember);

	}

}
