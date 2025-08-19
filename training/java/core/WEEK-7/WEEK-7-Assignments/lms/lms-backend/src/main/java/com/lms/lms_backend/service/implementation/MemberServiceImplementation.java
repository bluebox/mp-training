package com.lms.lms_backend.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.lms_backend.dao.MemberDao;
import com.lms.lms_backend.exception.DatabaseException;
import com.lms.lms_backend.exception.InvalidOperationException;
import com.lms.lms_backend.exception.ResourceNotFoundException;
import com.lms.lms_backend.model.Member;
import com.lms.lms_backend.service.MemberService;

@Service
public class MemberServiceImplementation implements MemberService {

    private final MemberDao memberDao;

    public MemberServiceImplementation(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    @Transactional
    public void addMember(Member member) {
        List<Member> existing = memberDao.selectAllMembers();
        boolean duplicate = existing.stream()
                .anyMatch(m -> m.getEmail().equalsIgnoreCase(member.getEmail())
                        || m.getMobile().equals(member.getMobile()));
        if (duplicate) {
            throw new InvalidOperationException("Member with same email or mobile already exists");
        }

        int inserted = memberDao.addMember(member);
        if (inserted != 1) {
            throw new DatabaseException("Member insertion failed");
        }
    }

    @Override
    public List<Member> getAllMembers() {
        return memberDao.selectAllMembers();
    }

    @Override
    public Member getMemberById(int id) {
        Member member = memberDao.selectMemberById(id);
        if (member == null) {
            throw new ResourceNotFoundException("Member not found with ID " + id);
        }
        return member;
    }

    @Override
    public List<String> getAllGenders() {
        return memberDao.selectAllGenders();
    }

    @Override
    @Transactional
    public void updateMemberDetails(int id, Member newMember) {
        Member oldMember = memberDao.selectMemberById(id);
        System.out.println(oldMember);
        System.out.println(newMember);
        if (oldMember == null) {
            throw new ResourceNotFoundException("Member not found with ID " + id);
        }

        if (oldMember.equals(newMember)) {
            throw new InvalidOperationException("At least one detail should be updated");
        }

        int logInserted = memberDao.insertMemberLog(oldMember);
        if (logInserted != 1) {
            throw new DatabaseException("Failed to insert log");
        }

        int updated = memberDao.updateMemberDetails(oldMember, newMember);
        if (updated != 1) {
            throw new DatabaseException("Member update failed");
        }
    }

    @Override
    @Transactional
    public void deleteMember(int id) {
        Member member = memberDao.selectMemberById(id);
        if (member == null) {
            throw new ResourceNotFoundException("Member not found with ID " + id);
        }

        int logInserted = memberDao.insertMemberLog(member);
        if (logInserted != 1) {
            throw new DatabaseException("Failed to insert log");
        }

        int deleted = memberDao.deleteMember(member);
        if (deleted != 1) {
            throw new DatabaseException("Member delete failed");
        }
    }
}
