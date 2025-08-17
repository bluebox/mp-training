package com.lms.springbootlms.service;
import java.util.List;

import com.lms.springbootlms.exception.InvalidInputException;
import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.Member;

public interface MemberService {

    List<Member> getAllMembers() throws ServiceException;

    Boolean addMember(Member member) throws InvalidInputException, ServiceException;

    Boolean updateMember(Member member) throws InvalidInputException, ServiceException;

    Member getMemberByMobile(String mobile) throws InvalidInputException, ServiceException;
}
