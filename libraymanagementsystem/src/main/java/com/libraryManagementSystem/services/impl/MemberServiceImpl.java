package com.libraryManagementSystem.services.impl;

import java.util.List;

import com.libraryManagementSystem.dao.MemberDao;
import com.libraryManagementSystem.dao.impl.MemberDaoImpl;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.MemberService;
import com.libraryManagementSystem.utilities.Validations;

public class MemberServiceImpl implements MemberService {

	private final MemberDao memberDao = new MemberDaoImpl();

	@Override
	public int registerMember(Member member) throws InvalidException {

		if (!Validations.isValidString(member.getName())) {
			throw new InvalidException("Name must be at least 3 characters long");
		}

		if (!Validations.isValidEmail(member.getEmail())) {
			throw new InvalidException("Invalid email format");
		}

		if (!Validations.isValidMobile(member.getMobile())) {
			throw new InvalidException("Mobile number must be 10 digits");
		}

		if (member.getGender() == null) {
			throw new InvalidException("Gender must not be null");
		}

		if (!Validations.isValidAdress(member.getAddress())) {
			throw new InvalidException("Address must not be empty");
		}

		if (memberDao.getMemberByMobile(member.getMobile())) {

			throw new InvalidException("Mobile Number Already Exist");
		}

		if (memberDao.getMemberByEmail(member.getEmail())) {

			throw new InvalidException("Email Already Exist");
		}

		return memberDao.RegisterMember(member);

	}

	@Override
	public List<Member> getMembers() throws InvalidException {

		return memberDao.getAllMembers();
	}

	@Override
	public int deleteMember(Member memberData) throws InvalidException {
		return memberDao.deleteMember(memberData);

	}

	@Override
	public int updateMember(Member newMember, Member oldMember) throws InvalidException {

		if (!Validations.isValidString(newMember.getName())) {
			throw new InvalidException("Name must be at least 3 characters long");
		}

		if (!Validations.isValidEmail(newMember.getEmail())) {
			throw new InvalidException("Invalid email format");
		}

		if (!Validations.isValidMobile(newMember.getMobile())) {
			throw new InvalidException("Mobile number must be 10 digits");
		}

		if (newMember.getGender() == null) {
			throw new InvalidException("Gender must not be null");
		}

		if (!Validations.isValidAdress(newMember.getAddress())) {
			throw new InvalidException("please enter valid adress");
		}

		return memberDao.UpdateMember(newMember, oldMember);

	}

}
