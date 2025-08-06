package com.lms.service;

import com.lms.exceptions.DAOException;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;

import java.util.List;

public interface MemberInf {

   
    Boolean addMember(Member member) throws InvalidInputException, DAOException;

    Boolean updateMember(Member member) throws InvalidInputException, DAOException;

    List<Member> getAllMembers() throws DAOException;

    Member getMemberByMobile(String mobile) throws InvalidInputException;
}