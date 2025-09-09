package com.lms.serviceImpl;

import com.lms.daoImpl.MemberDao;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;
import com.lms.service.MemberInf;
import com.lms.util.Validator;

import java.util.List;

public class MemberService implements MemberInf {

    @Override
    public void validate(String name, String email, String mobile, String address,String gender)
            throws InvalidInputException {
            Validator.serviceValidateName(name);
            Validator.serviceValidateEmail(email);
            Validator.serviceValidateMobile(mobile);
            Validator.serviceValidateAddress(address);
            Validator.serviceValidateGender(gender);
            Validator.isUniqueMobileNumber(mobile);
				
    }
    

    @Override
    public List<Member> getAllMembers() {
        return MemberDao.getAllMembers();
    }
    public Boolean addMember(Member member) {
		return MemberDao.addMember(member);
	}
    public Boolean updateMember(Member member) {
		return MemberDao.updateMember(member);
	}

	public Member getMemberByMobile(String mobile) throws InvalidInputException {
		return MemberDao.getMemberByMobile(mobile);
	}
}
