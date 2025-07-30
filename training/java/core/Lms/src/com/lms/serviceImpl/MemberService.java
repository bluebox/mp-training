package com.lms.serviceImpl;

import com.lms.daoImpl.MemberDao;
import com.lms.exceptions.DAOException;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;
import com.lms.service.MemberInf;
import com.lms.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class MemberService implements MemberInf {

    @Override
    public List<Member> getAllMembers() throws DAOException {
        try {
            return MemberDao.getAllMembers();
        } catch (DAOException e) {
            throw e;
        }
    }

    @Override
    public Boolean addMember(Member member) throws InvalidInputException, DAOException {
        try {
            Validator.validateMember(member);

            Member existingEmail = MemberDao.getMemberByEmail(member.getEmail());
            if (existingEmail != null) {
                throw new InvalidInputException("Email already exists in the system.");
            }

            Member existingMobile = MemberDao.getMemberByMobile(member.getMobile());
            if (existingMobile != null) {
                throw new InvalidInputException("Mobile number already exists in the system.");
            }

            boolean inserted = MemberDao.addMember(member);
            if (!inserted) {
                throw new DAOException("Failed to insert member into database");
            }

            return true;
        } catch (DAOException e) {
            throw new DAOException("Service failed to add member", e);
        }
    }

    @Override
    public Boolean updateMember(Member member) throws InvalidInputException, DAOException {
        try {
            Validator.validateMember(member);

            Member existing = MemberDao.getMemberById(member.getMemberId());
            if (existing == null) {
                throw new InvalidInputException("Member not found.");
            }

            boolean sameEmail = existing.getEmail().equalsIgnoreCase(member.getEmail());
            boolean sameMobile = existing.getMobile().equals(member.getMobile());

            if (!sameEmail) {
                Member emailUser = MemberDao.getMemberByEmail(member.getEmail());
                if (emailUser != null && emailUser.getMemberId() != member.getMemberId()) {
                    throw new InvalidInputException("Email already exists in the system.");
                }
            }

            if (!sameMobile) {
                Member mobileUser = MemberDao.getMemberByMobile(member.getMobile());
                if (mobileUser != null && mobileUser.getMemberId() != member.getMemberId()) {
                    throw new InvalidInputException("Mobile number already exists in the system.");
                }
            }

            boolean updated = MemberDao.updateMember(member);
            if (!updated) {
                throw new DAOException("Failed to update member in database");
            }

            return true;
        } catch (DAOException e) {
            throw new DAOException("Service failed to update member", e);
        }
    }

    @Override
    public Member getMemberByMobile(String mobile) throws InvalidInputException {
        try {
            return MemberDao.getMemberByMobile(mobile);
        } catch (DAOException e) {
            throw new InvalidInputException("Error fetching member by mobile: " + e.getMessage());
        }
    }

}