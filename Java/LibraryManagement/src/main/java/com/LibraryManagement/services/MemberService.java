package com.LibraryManagement.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.LibraryManagement.utilites.pojos.Member;

public interface MemberService {
	public boolean addMemberService(Member member) throws Exception;
	public boolean updateMemberService(int memberId, String name,String email, Long mobile ) throws ClassNotFoundException, IOException, SQLException;
	public ArrayList<Member> viewAllMembersService() throws Exception; 
	public ArrayList<Member> viewAllMembersLogService() throws Exception; 

}