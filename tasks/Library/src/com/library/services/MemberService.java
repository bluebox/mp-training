package com.library.services;

import com.library.domain.Member;
import com.library.serviceInterface.MemberServiceInterface;
import com.library.util.DB;
import com.library.util.MemberValidator;
import com.library.util.ValidationException;
import com.library.dao.MemberDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberService implements MemberServiceInterface {
	private static MemberDAO memberDao= new MemberDAO ();
	
	public List<Member> fetchmembers() throws SQLException
	{
		
		return (ArrayList<Member>) memberDao.fetchAllmembers();
	}
	public void addMember(Member member) throws Exception
	{
		MemberValidator membervalidator= new MemberValidator();
		try {
				membervalidator.validator(member);
				Connection conn= DB.getConnection();
			try {
				conn.setAutoCommit(false);
				memberDao.addMember(conn,member);
				conn.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				conn.rollback();
			}
		}
		catch(ValidationException e)
		{
			throw new ValidationException(e.getMessage());
		}
		
		
	}

    public boolean updateMember(Member member) throws Exception {
    	MemberDAO memberDAO =new MemberDAO();
    	Connection conn= DB.getConnection();
    	try {
			conn.setAutoCommit(false);
    		ResultSet rs=memberDAO.getMemberById(conn, member.getMemberId());
    		memberDAO.insertIntoMemberLog(conn,rs);
        	boolean flag= memberDAO.updateMember(conn,member);
        	conn.commit();
        	return flag;

		} catch (Exception e) {
			conn.rollback();
			throw new Exception("user not updated");
		}
    	
    	}
}