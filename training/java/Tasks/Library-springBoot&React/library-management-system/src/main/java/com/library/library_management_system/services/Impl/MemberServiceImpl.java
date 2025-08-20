package com.library.library_management_system.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library_management_system.dao.MemberDaoInterface;
import com.library.library_management_system.domain.Member;
import com.library.library_management_system.exceptions.ApiException;
import com.library.library_management_system.services.MemberServiceInterface;

@Service
public class MemberServiceImpl implements MemberServiceInterface {

	@Autowired
	private MemberDaoInterface memberDao;

	@Override
	public Member addMember(Member member) {
		try {
//			return memberDao.insertMember(member);
			Member newMember = memberDao.insertMember(member);
			if (newMember == null) {
				throw new ApiException("Failed to update member");
			}
			return newMember;
		} catch (Exception e) {
			throw new ApiException("Error adding member: " + e.getMessage());
		}
	}

	@Override
	public Member updateMember(int id, Member member) {
		Member existingMember = memberDao.getMemberById(id);
		if (existingMember == null) {
			throw new ApiException("Member not found with ID: " + id);
		}
		checkAtLeastOneFieldChanged(existingMember, member);
		try {
//			return memberDao.updateMember(member);
			member.setMemberId(id);
			Member updatedMember = memberDao.updateMember(member);
			if (updatedMember == null) {
				throw new ApiException("Failed to update member");
			}
			return updatedMember;
		} catch (Exception e) {
			throw new ApiException("Error updating member: " + e.getMessage());
		}
	}

	@Override
	public List<Member> getAllMembers() {
		List<Member> members = memberDao.getAllMembers();
		if (members == null || members.isEmpty()) {
			throw new ApiException("No members found in the library");
		}
		return members;
	}

	@Override
	public Member getMemberById(int id) {
		Member member = memberDao.getMemberById(id);
		if (member == null) {
			throw new ApiException("Member not found with ID: " + id);
		}
		return member;
	}

	@Override
	public void deleteMember(int id) {
		Member member = memberDao.getMemberById(id);
		if (member == null) {
			throw new ApiException("Member not found with ID: " + id);
		}
		try {
			int rows = memberDao.deleteMember(id);
			if (rows == 0) {
				throw new ApiException("Failed to delete member");
			}
		} catch (Exception e) {
			throw new ApiException("Error deleting member: " + e.getMessage());
		}
	}

	private void checkAtLeastOneFieldChanged(Member existing, Member updated) {
		if (existing.getMemberName().equals(updated.getMemberName())
				&& existing.getMemberMail().equals(updated.getMemberMail())
				&& existing.getMobileNo().equals(updated.getMobileNo())
				&& existing.getGender().equals(updated.getGender())
				&& existing.getMemberAddress().equals(updated.getMemberAddress())) {
			throw new ApiException("At least one field must be updated");
		}
	}

}
