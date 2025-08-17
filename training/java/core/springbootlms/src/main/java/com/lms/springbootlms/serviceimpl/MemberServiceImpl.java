package com.lms.springbootlms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.springbootlms.dao.MemberDao;
import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.exception.InvalidInputException;
import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.Member;
import com.lms.springbootlms.service.MemberService;
import com.lms.springbootlms.util.Validator;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public List<Member> getAllMembers() throws ServiceException {
        try {
        	List<Member> members = memberDao.getAllMembers();
            return members;
        } catch (DaoException  e) {
            throw new ServiceException("Service failed to fetch members", e);
        }
    }

    @Override
    public Boolean addMember(Member member) throws InvalidInputException, ServiceException {
        try {
            Validator.validateMember(member);

            Member existingEmail = memberDao.getMemberByEmail(member.getEmail());
            if (existingEmail != null) {
                throw new InvalidInputException("Email already exists in the system.");
            }

            Member existingMobile = memberDao.getMemberByMobile(member.getMobile());
            if (existingMobile != null) {
                throw new InvalidInputException("Mobile number already exists in the system.");
            }

            boolean inserted = memberDao.addMember(member);
            if (!inserted) {
                throw new ServiceException("Failed to insert member into database");
            }

            return true;
        } catch (DaoException e) {
            throw new ServiceException("Service failed to add member", e);
        }
    }

    @Override
    public Boolean updateMember(Member member) throws InvalidInputException, ServiceException {
        try {
            Validator.validateMember(member);

            Member existing = memberDao.getMemberById(member.getMemberId());
            if (existing == null) {
                throw new InvalidInputException("Member not found.");
            }

            boolean sameEmail = existing.getEmail().equalsIgnoreCase(member.getEmail());
            boolean sameMobile = existing.getMobile().equals(member.getMobile());

            if (!sameEmail) {
                Member emailUser = memberDao.getMemberByEmail(member.getEmail());
                if (emailUser != null && emailUser.getMemberId() != member.getMemberId()) {
                    throw new InvalidInputException("Email already exists in the system.");
                }
            }

            if (!sameMobile) {
                Member mobileUser = memberDao.getMemberByMobile(member.getMobile());
                if (mobileUser != null && mobileUser.getMemberId() != member.getMemberId()) {
                    throw new InvalidInputException("Mobile number already exists in the system.");
                }
            }

            boolean updated = memberDao.updateMember(member);
            if (!updated) {
                throw new ServiceException("Failed to update member in database");
            }

            return true;
        } catch (DaoException e) {
            throw new ServiceException("Service failed to update member", e);
        }
    }

    @Override
    public Member getMemberByMobile(String mobile) throws InvalidInputException, ServiceException {
        try {
            return memberDao.getMemberByMobile(mobile);
        } catch (DaoException e) {
            throw new ServiceException("Error fetching member by mobile", e);
        }
    }
}
