package com.library.dao;

import com.library.DaoInterface.MemberDAOInterface;
import com.library.domain.Member;
import com.library.sqlQueryLoader.memberSqlQueryStore;
import com.library.sqlQueryLoader.sqlQueryStore;
import com.library.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends memberSqlQueryStore implements MemberDAOInterface{


	public boolean updateMember(Connection conn,Member member)throws Exception {
	    String sql = updateMember;
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, member.getName());
	            stmt.setString(2, member.getEmail());
	            stmt.setLong(3, member.getMobile());
	            stmt.setString(4,String.valueOf(member.getGender()));
	            stmt.setString(5, member.getAddress());
	            stmt.setInt(6, member.getMemberId());
	             return stmt.executeUpdate()>0;
	        }

	  } 

	
	public void insertIntoMemberLog(Connection conn,ResultSet rs) throws Exception
	{
		 if (rs.next()) {

             String insertLogSQL = insertIntoMemberLog;
             try (PreparedStatement logStmt = conn.prepareStatement(insertLogSQL)) {
                 logStmt.setInt(1, rs.getInt("MemberId"));
                 logStmt.setString(2, rs.getString("Name"));
                 logStmt.setString(3, rs.getString("Email"));
                 logStmt.setLong(4, rs.getLong("Mobile"));
                 logStmt.setString(5, rs.getString("Gender"));
                 logStmt.setString(6, rs.getString("Address"));
                 logStmt.executeUpdate();
             }
         } else {
             throw new IllegalArgumentException("Member not found.");
         }
     }

	
	
	public ResultSet getMemberById(Connection conn, int memberId) throws SQLException {
		String query = getMemberById;
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();
			return rs;
		}
	}


	public List<Member> fetchAllmembers() throws SQLException {
	        List<Member> members = new ArrayList<>();
		        try (Connection conn = DB.getConnection();	
		             PreparedStatement stmt = conn.prepareStatement(getAllMembers);
		             ResultSet rs = stmt.executeQuery())
		        {
		            while (rs.next()) {
								Member m = new Member(rs.getInt("memberId"),  rs.getString("name"),rs.getString("email"),
										rs.getLong("mobile"), rs.getString("gender").charAt(0), rs.getString("address"));
								members.add(m);
		            				}
		        }
	        return members;
    }


	@Override
	public boolean addMember(Connection conn, Member member) throws Exception {
		//pavan
		return false;
	}

}