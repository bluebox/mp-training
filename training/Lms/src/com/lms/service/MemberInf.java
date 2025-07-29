package com.lms.service;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;

import java.util.List;

public interface MemberInf {

    void validate(String name, String email, String mobile, String address, String gender)
            throws InvalidInputException;

    Boolean addMember(Member member)throws InvalidInputException;

    Boolean updateMember(Member member)throws InvalidInputException;

    List<Member> getAllMembers();

    Member getMemberByMobile(String mobile) throws InvalidInputException;
}
