package com.lms.springbootlms.dao;

import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.model.Member;
import java.util.List;

public interface MemberDao {

    boolean addMember(Member member) throws DaoException;

    boolean updateMember(Member member) throws DaoException;

    Member getMemberById(int id) throws DaoException;

    List<Member> getAllMembers() throws DaoException;

    int generateNewMemberId() throws DaoException;

    Member getMemberByMobile(String mobile) throws DaoException;

    Member getMemberByEmail(String email) throws DaoException;
}
