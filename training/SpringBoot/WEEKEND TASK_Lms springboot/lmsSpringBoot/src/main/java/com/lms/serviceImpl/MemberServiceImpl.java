package com.lms.serviceImpl;

import com.lms.daoImpl.MemberRepositoryImpl;
import com.lms.exceptions.DAOException;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;
import com.lms.util.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MemberServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    private final MemberRepositoryImpl memberRepository;

    public List<Member> getAllMembers() throws DAOException {
        logger.info("Fetching all members");
        try {
            return memberRepository.getAllMembers();
        } catch (Exception e) {
            logger.error("Failed to fetch members", e);
            throw new DAOException("Failed to fetch members", e);
        }
    }

    @Transactional
    public void addMember(Member member) throws InvalidInputException, DAOException {
        logger.info("Adding new member: {}", member.getEmail());

        Validator.validateMember(member);

        if (memberRepository.getMemberByEmail(member.getEmail()) != null) {
            logger.warn("Email already exists: {}", member.getEmail());
            throw new InvalidInputException("Email already exists");
        }

        if (memberRepository.getMemberByMobile(member.getMobile()) != null) {
            logger.warn("Mobile number already exists: {}", member.getMobile());
            throw new InvalidInputException("Mobile already exists");
        }

        try {
            boolean inserted = memberRepository.addMember(member);
            if (!inserted) {
                logger.error("Failed to insert member: {}", member.getEmail());
                throw new DAOException("Failed to insert member");
            }
            logger.info("Member added successfully: {}", member.getEmail());
        } catch (Exception e) {
            logger.error("Database error while inserting member: {}", member.getEmail(), e);
            throw new DAOException("Database error while inserting member", e);
        }
    }

    @Transactional
    public void updateMember(Member member) throws InvalidInputException, DAOException {
        logger.info("Updating member with ID: {}", member.getMemberId());

        Validator.validateMember(member);

        Member existing = memberRepository.getMemberById(member.getMemberId());
        if (existing == null) {
            logger.warn("Member not found with ID: {}", member.getMemberId());
            throw new InvalidInputException("Member not found.");
        }

        if (!existing.getEmail().equalsIgnoreCase(member.getEmail())) {
            Member emailUser = memberRepository.getMemberByEmail(member.getEmail());
            if (emailUser != null && emailUser.getMemberId() != member.getMemberId()) {
                logger.warn("Email already exists: {}", member.getEmail());
                throw new InvalidInputException("Email already exists.");
            }
        }

        if (!existing.getMobile().equals(member.getMobile())) {
            Member mobileUser = memberRepository.getMemberByMobile(member.getMobile());
            if (mobileUser != null && mobileUser.getMemberId() != member.getMemberId()) {
                logger.warn("Mobile number already exists: {}", member.getMobile());
                throw new InvalidInputException("Mobile number already exists.");
            }
        }

        try {
            boolean updated = memberRepository.updateMember(member);
            if (!updated) {
                logger.error("Failed to update member in database: ID {}", member.getMemberId());
                throw new DAOException("Failed to update member in database.");
            }
            logger.info("Member updated successfully: ID {}", member.getMemberId());
        } catch (Exception e) {
            logger.error("Database error while updating member: ID {}", member.getMemberId(), e);
            throw new DAOException("Database error while updating member.", e);
        }
    }

    public Member getMemberByMobile(String mobile) throws DAOException {
        logger.info("Fetching member by mobile: {}", mobile);
        try {
            return memberRepository.getMemberByMobile(mobile);
        } catch (Exception e) {
            logger.error("Database error while fetching member by mobile: {}", mobile, e);
            throw new DAOException("Database error while fetching member by mobile", e);
        }
    }
}
