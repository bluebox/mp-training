package dev.kaushik.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kaushik.library.dao.MemberDAO;
import dev.kaushik.library.exception.LibraryException;
import dev.kaushik.library.model.Member;
import dev.kaushik.library.service.IssueService;
import dev.kaushik.library.service.MemberService;
import dev.kaushik.library.validator.MemberValidator;

@Service
public class MemberServiceImpl implements MemberService {
	private final MemberDAO memberDAO;
	private final IssueService issueService;

	@Autowired
	public MemberServiceImpl(MemberDAO memberDAO, IssueService issueService) {
		this.memberDAO = memberDAO;
		this.issueService = issueService;
	}

	@Override
	public Integer addMember(Member member) {
		MemberValidator.validate(member);
		if (member.getMemberID() != null) {
			throw new LibraryException("Member ID can not be specified, it will be auto generated");
		}
		List<Member> allMembers = memberDAO.findMembers(null);
		if (allMembers.stream().filter(m -> m.getEmail().equals(member.getEmail())).count() != 0) {
			throw new LibraryException("Member already exists with Email: " + member.getEmail());
		}
		if (allMembers.stream().filter(m -> m.getPhoneNumber() == member.getPhoneNumber()).count() != 0) {
			throw new LibraryException("Member already exists with Phone Number: " + member.getPhoneNumber());
		}
		return memberDAO.addMember(member);
	}

	@Override
	public boolean updateMember(Member member) {
		MemberValidator.validate(member);
		if (member.getMemberID() == null || member.getMemberID() <= 0) {
			throw new LibraryException("Member ID must be provided and be a positive number for update.");
		}
		List<Member> allMembers = memberDAO.findMembers(null);
		Member existingMember = allMembers.stream().filter(m -> m.getMemberID() == member.getMemberID()).findFirst()
				.orElse(null);
		if (existingMember == null) {
			throw new LibraryException("Member not found with Member ID: " + member.getMemberID());
		}

		if (existingMember.equals(member)) {
			throw new LibraryException("Same details were found, no changes made");
		}
		if (allMembers.stream().filter(m -> m.getMemberID() != member.getMemberID())
				.filter(m -> m.getEmail().equals(member.getEmail())).count() != 0) {
			throw new LibraryException("Member already exists with Email: " + member.getEmail());
		}
		if (allMembers.stream().filter(m -> m.getMemberID() != member.getMemberID())
				.filter(m -> m.getPhoneNumber() == member.getPhoneNumber()).count() != 0) {
			throw new LibraryException("Member already exists with Phone Number: " + member.getPhoneNumber());
		}

		return memberDAO.updateMember(member);
	}

	@Override
	public boolean deleteMember(Integer memberId) {
		if (memberId == null || memberId <= 0) {
			throw new LibraryException("Member id cant be null or negative or zero");
		}
		Member existingMember = memberDAO.findMembers(null).stream().filter(m -> m.getMemberID() == memberId)
				.findFirst().orElse(null);
		if (existingMember == null) {
			throw new LibraryException("Member not found with Member Id: " + memberId);
		}
		List<Member> membersWithActiveIssues = issueService.getMembersWithActiveBooks();
		List<Member> matched = membersWithActiveIssues.stream().filter(member -> member.getMemberID() == memberId)
				.toList();
		if (matched.size() > 0) {
			throw new LibraryException("cant delete member");
		}
		return memberDAO.deleteMember(memberId);
	}

	@Override
	public int deleteMembers(List<Integer> memberIds) {
		if (memberIds == null) {
			throw new LibraryException("member ID's cant be null");
		}
		memberIds.forEach(memberId -> {
			if (memberId == null || memberId <= 0) {
				throw new LibraryException("Member id cant be null or negative or zero");
			}
		});
		List<Member> membersWithActiveIssues = issueService.getMembersWithActiveBooks();
		List<String> matched = membersWithActiveIssues.stream()
				.filter(member -> memberIds.contains(member.getMemberID())).map(m -> m.getName()).toList();
		if (matched.size() > 0) {
			throw new LibraryException("can't delete, these members have issued books: " + matched);
		}

		Integer[] memberIdArray = memberIds.toArray(new Integer[0]);
		return memberDAO.deleteMembersInBatch(memberIdArray);
	}

	@Override
	public List<Member> findMembers(Member criteria) {
		return memberDAO.findMembers(criteria);
	}
}