package com.lms.serviceImpl;

import com.lms.daoImpl.MemberDao;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;
import com.lms.service.MemberInf;
import com.lms.util.Validator;

import java.util.List;

public class MemberService implements MemberInf {

    @Override
    public void validate(String name, String email, String mobile, String address, String gender)
            throws InvalidInputException {
        Validator.serviceValidateName(name);
        Validator.serviceValidateEmail(email);
        Validator.serviceValidateMobile(mobile);
        Validator.serviceValidateAddress(address);
        Validator.serviceValidateGender(gender);
    }

    @Override
    public List<Member> getAllMembers() {
        return MemberDao.getAllMembers();
    }

    @Override
    public Boolean addMember(Member member) throws InvalidInputException {
        Member existingEmail = MemberDao.getMemberByEmail(member.getEmail());
        if (existingEmail != null) {
            throw new InvalidInputException("Email already exists in the system.");
        }

        Member existingMobile = MemberDao.getMemberByMobile(member.getMobile());
        if (existingMobile != null) {
            throw new InvalidInputException("Mobile number already exists in the system.");
        }

        return MemberDao.addMember(member);
    }

    @Override
    public Boolean updateMember(Member member) throws InvalidInputException {
        Member existingMember = MemberDao.getMemberById(member.getMemberId());

        boolean isSameEmail = existingMember.getEmail().equals(member.getEmail());
        boolean isSameMobile = existingMember.getMobile().equals(member.getMobile());

        if (isSameEmail && isSameMobile) {
            return MemberDao.updateMember(member);
        }

        if (!isSameEmail) {
            Member emailExists = MemberDao.getMemberByEmail(member.getEmail());
            if (emailExists != null && emailExists.getMemberId() != member.getMemberId()) {
                throw new InvalidInputException("Email already exists in the system.");
            }
        }

        if (!isSameMobile) {
            Member mobileExists = MemberDao.getMemberByMobile(member.getMobile());
            if (mobileExists != null && mobileExists.getMemberId() != member.getMemberId()) {
                throw new InvalidInputException("Mobile number already exists in the system.");
            }
        }

        return MemberDao.updateMember(member);
    }

    @Override
    public Member getMemberByMobile(String mobile) throws InvalidInputException {
        return MemberDao.getMemberByMobile(mobile);
    }
}
