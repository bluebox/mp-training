package com.library_management.dao;

import java.sql.SQLException;
import java.util.List;

import com.library_management.domain.Book;
import com.library_management.domain.Member;
import com.library_management.exceptions.DatabaseException;

public interface MemberDaoInterface {
   public int insertMember(Member member) throws SQLException, DatabaseException;
   List<Member> getAllMembers() throws SQLException;

void updateMember(Member oldMember , Member newMember) throws DatabaseException;
Member selectMemberById(int id) throws DatabaseException;
boolean deleteMember(int memberId) throws DatabaseException;
   
}
