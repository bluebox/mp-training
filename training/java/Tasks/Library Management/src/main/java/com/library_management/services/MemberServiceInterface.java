package com.library_management.services;

import java.sql.SQLException;
import java.util.List;

import com.library_management.domain.Book;
import com.library_management.domain.Member;
import com.library_management.exceptions.DatabaseException;
import com.library_management.exceptions.InvalidDetailsException;

public interface MemberServiceInterface {
	public void addMember(Member member) throws SQLException, DatabaseException, InvalidDetailsException;
	public boolean isValidEmail(String email)throws SQLException;
	List<Member> validateViewAllMembers() throws DatabaseException,SQLException;
	

}
