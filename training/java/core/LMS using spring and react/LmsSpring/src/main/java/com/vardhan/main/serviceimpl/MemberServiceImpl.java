package com.vardhan.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vardhan.main.dao.MemberDao;
import com.vardhan.main.model.Member;
import com.vardhan.main.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member saveMember(Member member) throws DataAccessException {
        log.info("Attempting to save member with mobile: {}", member.getMobile());
        
        if (member.getStatus() == null || member.getStatus().trim().isEmpty()) {
            member.setStatus("ACTIVE");
        }
        
        if (memberDao.existsByEmail(member.getEmail())) {
            throw new IllegalArgumentException("Email already exists in the system: " + member.getEmail());
        }
        
        if (memberDao.existsByMobile(member.getMobile())) {
            throw new IllegalArgumentException("Mobile number already exists in the system: " + member.getMobile());
        }
        
        Member savedMember = memberDao.save(member);
        log.info("Successfully saved member with ID: {}", savedMember.getMemberId());
        return savedMember;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findMemberById(Integer memberId) throws DataAccessException {
        log.debug("Finding member by ID: {}", memberId);
        return memberDao.findById(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findMemberByIdIncludingDeleted(Integer memberId) throws DataAccessException {
        log.debug("Finding member by ID including deleted: {}", memberId);
        return memberDao.findByIdIncludingDeleted(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findMemberByMobile(String mobile) throws DataAccessException {
        log.debug("Finding member by mobile: {}", mobile);
        validateMobileNumber(mobile);
        return memberDao.findByMobile(mobile);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findMemberByEmail(String email) throws DataAccessException {
        log.debug("Finding member by email: {}", email);
        return memberDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getAllMembers() throws DataAccessException {
        log.debug("Fetching all members (excluding deleted)");
        return memberDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getActiveMembers() throws DataAccessException {
        log.debug("Fetching active members only");
        return memberDao.findAllActive();
    }

    @Override
    public Member updateMember(Member member) throws DataAccessException {
        log.info("Attempting to update member with ID: {}", member.getMemberId());
        
        Optional<Member> existingMemberOpt = memberDao.findById(member.getMemberId());
        if (existingMemberOpt.isEmpty()) {
            throw new IllegalArgumentException("Member not found with ID: " + member.getMemberId());
        }
        
        Member existing = existingMemberOpt.get();
        
        if (!existing.getEmail().equalsIgnoreCase(member.getEmail())) {
            Optional<Member> emailUser = memberDao.findByEmail(member.getEmail());
            if (emailUser.isPresent() && !emailUser.get().getMemberId().equals(member.getMemberId())) {
                throw new IllegalArgumentException("Email already exists in the system: " + member.getEmail());
            }
        }
        
        if (!existing.getMobile().equals(member.getMobile())) {
            Optional<Member> mobileUser = memberDao.findByMobile(member.getMobile());
            if (mobileUser.isPresent() && !mobileUser.get().getMemberId().equals(member.getMemberId())) {
                throw new IllegalArgumentException("Mobile number already exists in the system: " + member.getMobile());
            }
        }
        
        if (member.getStatus() == null) {
            member.setStatus(existing.getStatus());
        }
        
        Member updatedMember = memberDao.update(member);
        log.info("Successfully updated member with ID: {}", updatedMember.getMemberId());
        return updatedMember;
    }

    @Override
    public boolean deleteMember(Integer memberId) throws DataAccessException {
        return deactivateMember(memberId);
    }

    @Override
    public boolean deactivateMember(Integer memberId) throws DataAccessException {
        log.info("Attempting to deactivate member with ID: {}", memberId);
        
        Optional<Member> memberOpt = memberDao.findById(memberId);
        if (memberOpt.isEmpty()) {
            throw new IllegalArgumentException("Member not found with ID: " + memberId);
        }
        
        Member member = memberOpt.get();
        if (member.isDeleted()) {
            throw new IllegalArgumentException("Member is already deactivated: " + memberId);
        }
        
        boolean deactivated = memberDao.softDeleteById(memberId);
        if (deactivated) {
            log.info("Successfully deactivated member with ID: {}", memberId);
        }
        return deactivated;
    }

    @Override
    public boolean reactivateMember(Integer memberId) throws DataAccessException {
        log.info("Attempting to reactivate member with ID: {}", memberId);
        
        Optional<Member> memberOpt = memberDao.findByIdIncludingDeleted(memberId);
        if (memberOpt.isEmpty()) {
            throw new IllegalArgumentException("Member not found with ID: " + memberId);
        }
        
        Member member = memberOpt.get();
        if (member.isActive()) {
            throw new IllegalArgumentException("Member is already active: " + memberId);
        }
        
        boolean reactivated = memberDao.reactivateById(memberId);
        if (reactivated) {
            log.info("Successfully reactivated member with ID: {}", memberId);
        }
        return reactivated;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isMobileExists(String mobile) throws DataAccessException {
        return memberDao.existsByMobile(mobile);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) throws DataAccessException {
        return memberDao.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isMobileExistsActive(String mobile) throws DataAccessException {
        return memberDao.existsByMobileActive(mobile);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExistsActive(String email) throws DataAccessException {
        return memberDao.existsByEmailActive(email);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalMembersCount() throws DataAccessException {
        return memberDao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long getActiveMembersCount() throws DataAccessException {
        return memberDao.countActive();
    }

    private void validateMobileNumber(String mobile) {
        if (mobile == null || !mobile.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Invalid mobile number format: " + mobile);
        }
    }
}
