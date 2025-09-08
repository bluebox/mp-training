package com.medplus.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.lms.dao.MemberRepository;
import com.medplus.lms.dao.MemberRepositoryInterface;
import com.medplus.lms.domain.Member;
import com.medplus.lms.domain.Status;
import com.medplus.lms.exceptions.ManagementException;

@Service
public class MemberService implements MemberServiceInterface{

    private final MemberRepositoryInterface memberDao;

    public MemberService(MemberRepository repo) {
        this.memberDao = repo;
    }
    @Transactional
    public String registerMember(Member member) throws ManagementException {
    	Member existingMember=memberDao.getMemberByEmailOrMobile(member.getEmail(), member.getMobile());
        if (existingMember !=null) {
        	if(existingMember.getStatus()==Status.INACTIVE && 
        			existingMember.getEmail().equals(member.getEmail()) &&
        			existingMember.getMobile().equals(member.getMobile())) {
        		existingMember.setStatus(Status.ACTIVE);
        		existingMember.setUpdatedBy("Admin");
        		memberDao.updateMemberStatus(existingMember);
        		return "Reactivated member successful";
        	}
            throw new ManagementException("Email or Mobile already exists!");
        }
        if (member.getCreatedBy() == null || member.getCreatedBy().trim().isEmpty()) {
            member.setCreatedBy("SYSTEM");
        }
        member.setStatus(Status.ACTIVE);
        memberDao.addMember(member);
        return "Member registered successfully!";
    }

    public void updateMember(Member member) throws ManagementException {
        Member existingMember = memberDao.findMemberById(member.getMemberId());
        if (existingMember == null) {
            throw new ManagementException("Member not found with ID: " + member.getMemberId());
        }
        Member duplicateMember = memberDao.getMemberByEmailOrMobile(member.getEmail(), member.getMobile());
        if (duplicateMember != null && duplicateMember.getMemberId() != member.getMemberId()) {
            throw new ManagementException("Email or Mobile already exists!");
        }
        if (existingMember.equalsForUpdate(member)) {
            throw new ManagementException("Please update at least one field to update.");
        }
        if (member.getUpdatedBy() == null || member.getUpdatedBy().trim().isEmpty()) {
            member.setUpdatedBy("SYSTEM");
        }
        memberDao.updateMember(member);
    }


    public List<Member> getAllMembers() {
        return memberDao.getAllMembers();
    }
    @Transactional(rollbackFor = Exception.class)
    public void deleteMember(int memberId) throws ManagementException {
        Member member = memberDao.findMemberById(memberId);
        if (member == null) {
            throw new ManagementException("Member not found with ID: " + memberId);
        }

        if (memberDao.hasActiveIssuedBooks(memberId)) {
            throw new ManagementException("Member has active issued books. Status cannot be made inactive.");
        }
        member.setUpdatedBy("Admin");
        member.setStatus(Status.INACTIVE);
        memberDao.updateMemberStatus(member);
    }
    public Member getMemberById(int memberId) {
        Member member = memberDao.findMemberById(memberId);
        if (member == null) {
            throw new RuntimeException("Member not found with id " + memberId);
        }
        return member;
    }
}
