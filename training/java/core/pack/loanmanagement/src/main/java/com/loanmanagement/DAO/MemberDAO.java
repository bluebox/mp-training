package com.loanmanagement.DAO;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.loanmanagement.model.Member;

public interface MemberDAO {
	 public void addMember(Member member) throws SQLException;
	 public Member getMemberById(int id)throws SQLException;
	 public List<Member> getAllMembers() throws SQLException;
	 public Member getMemberByEmail(String email) throws SQLException,DuplicateKeyException;
}
