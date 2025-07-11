package com.loanmanagement.service;

import java.util.List;

import com.loanmanagement.model.Loan;
import com.loanmanagement.model.LoanRequestDTO;
import com.loanmanagement.model.Member;

public interface MemberService {
	
		 public void addMember(Member member) throws Exception;
		 public Member getMemberById(int id)throws Exception;
		 public List<Member> getAllMembers() throws Exception;
		
		
}
