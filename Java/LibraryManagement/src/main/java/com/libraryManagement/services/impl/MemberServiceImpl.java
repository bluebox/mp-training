package com.libraryManagement.services.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.libraryManagement.dao.impl.MemberDaoImpl;
import com.libraryManagement.services.MemberService;
import com.libraryManagement.utility.DBConnection;
import com.libraryManagement.utility.pojos.Member;

public class MemberServiceImpl implements MemberService {
	MemberDaoImpl memberDaoImpl = new MemberDaoImpl();

	@Override
	public boolean addMemberService(Member mem) {
		if (mem.getEmail() == null || mem.getGender() == null || mem.getMobile() == null || mem.getName() == null)
			return false;
		return memberDaoImpl.addMember(mem);
	}

	@Override

	public boolean updateMemberService(Member mem) throws ClassNotFoundException, IOException, SQLException {
		Connection conn = DBConnection.getConnection();
		try {
			conn.setAutoCommit(false);
			if (!memberDaoImpl.addMemberLog(mem))
				return false;
			if (!memberDaoImpl.updateMember(mem))
				return false;
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			conn.setAutoCommit(true);
		}
		return false;
	}
	@Override
	public List<Member>allMemberService(){
		return memberDaoImpl.allMember();
	}
}
