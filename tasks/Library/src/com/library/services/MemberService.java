package com.library.services;

import com.library.dao.Member;
import com.library.dao.MemberDAO;
import com.library.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
	
	public List<Member> fetchmembers() throws SQLException
	{
		MemberDAO memebersDao= new MemberDAO ();
		return (ArrayList<Member>) memebersDao.fetchAllmembers();
	}
	public void addMember(Member member)
	{
		System.out.println("added");
	}

    public void updateMember(Member member) throws Exception {
    	MemberDAO memberDAO =new MemberDAO();
    	try {
			
        	memberDAO.updateMember(member);

		} catch (Exception e) {
			throw new Exception("user not updated");
		}
    	
    	}
}