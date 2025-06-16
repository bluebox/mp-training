package com.library.services;

import com.library.domain.Member;
import com.library.serviceInterface.MemberServiceInterface;
import com.library.util.DB;
import com.library.dao.MemberDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberService implements MemberServiceInterface {
	
	
	public List<Member> fetchmembers() throws SQLException
	{
		MemberDAO memebersDao= new MemberDAO ();
		return (ArrayList<Member>) memebersDao.fetchAllmembers();
	}
	public void addMember(Member member)
	{
		//pavan
	}

    public void updateMember(Member member) throws Exception {
    	MemberDAO memberDAO =new MemberDAO();
    	Connection conn= DB.getConnection();
    	try {
			conn.setAutoCommit(false);
    		ResultSet rs=memberDAO.getMemberById(conn, member.getMemberId());
    		memberDAO.insertIntoMemberLog(conn,rs);
        	memberDAO.updateMember(conn,member);
        	conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw new Exception("user not updated");
		}
    	
    	}
}