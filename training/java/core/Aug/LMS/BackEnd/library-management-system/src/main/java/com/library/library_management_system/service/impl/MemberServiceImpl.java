package com.library.library_management_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library_management_system.domain.CustomReportDetails;
import com.library.library_management_system.domain.Member;
import com.library.library_management_system.exception.DatabaseOperationException;
import com.library.library_management_system.exception.DuplicateMemberException;
import com.library.library_management_system.exception.InvalidDataException;
import com.library.library_management_system.repository.MemberRepository;
import com.library.library_management_system.repository.ReportRepository;
import com.library.library_management_system.service.MemberService;
import com.library.library_management_system.utils.MemberStatus;

import jakarta.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final ReportRepository reportRepository;

	public MemberServiceImpl(MemberRepository memberRepository, ReportRepository reportRepository) {
		this.memberRepository = memberRepository;
		this.reportRepository = reportRepository;
	}

	@Override
	public int addMember(Member member) {

		if (member == null) {
			throw new InvalidDataException("Member details are wrong");
		}

		if (memberRepository.getMemberByMobile(member.getMobile())) {

			throw new DuplicateMemberException("Member already exists with this Mobile Number");
		}

		if (memberRepository.getMemberByEmail(member.getEmail())) {

			throw new DuplicateMemberException("Member already exists with this Email");
		}

		int rowsInserted = memberRepository.addMember(member);

		if (rowsInserted <= 0) {
			throw new DatabaseOperationException("Member Not Added Please try again");
		}
		return rowsInserted;
	}

	@Override
	@Transactional
	public Member updateMember(Member newMember, Member oldMember) {

		if (newMember == null || oldMember == null) {
			throw new InvalidDataException("Member details are wrong");
		}
		if (memberRepository.getMemberByMobileExceptId(newMember.getMobile(), newMember.getId())) {

			throw new DuplicateMemberException("Member already exists with this Mobile Number");
		}

		if (memberRepository.getMemberByEmailExceptId(newMember.getEmail(), newMember.getId())) {

			throw new DuplicateMemberException("Member already exists with this Email");
		}

		if (newMember.equals(oldMember)) {
			throw new InvalidDataException("Please edit at least one field");
		}
		int rowsUpdated = memberRepository.UpdateMember(newMember);

		if (rowsUpdated <= 0) {
			throw new DatabaseOperationException("Member Not Updated Please try again");
		}

		memberRepository.memberLog(oldMember);

		return newMember;

	}

	@Override
	public Member getMemberById(int id) {

		return memberRepository.getMemberById(id);

	}

	@Override
	public List<Member> getMembers() {

		return memberRepository.getAllMembers();
	}

	@Override
	public int deleteMember(Member member) {

		if (member == null) {
			throw new InvalidDataException("Member details are wrong");
		}

		if (member.getStatus().equals(MemberStatus.INACTIVE)) {
			throw new InvalidDataException("Member Already deleted.Please try again");
		}

		List<CustomReportDetails> activeIssues = reportRepository.getActiveIssuedBooks().stream()
				.filter(issue -> issue.getMemberId() == member.getId()).toList();
		if (activeIssues.size() >= 1) {
			throw new InvalidDataException("Member have Books :" + activeIssues.size() + ". Please collect first.");
		}
		int rowsEffected = memberRepository.deleteMember(member);

		if (rowsEffected <= 0) {
			throw new DatabaseOperationException("Member Not Deleted Please try again");
		}

		memberRepository.memberLog(member);

		return 1;
	}

}
