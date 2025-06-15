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

    public boolean updateMember(Member member) {
        String query = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";

        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, String.valueOf(member.getGender()));
            stmt.setString(5, member.getAddress());
            stmt.setInt(6, member.getMemberId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error updating member: " + e.getMessage());
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}