package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DBConnection;
import model.Member;

public class MemberDao {
	
	public void insertMember(Member member) throws SQLException
	{
		String query="insert into members values (?,?,?)";
		Connection con=DBConnection.getConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,member.getMemberId());
		ps.setString(2, member.getName());
		ps.setInt(3, member.getAge());
		ps.execute();	
	}
	public List<Member> getAllMembers() throws SQLException
	{
		List<Member> members=new ArrayList<>();
		String query="select * from members";
		Connection con=DBConnection.getConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			members.add(new Member(rs.getString("member_id"),rs.getString("name"),rs.getInt("age")));
		}
		return members;
	}
	
	public boolean memberExists(String memberId) throws SQLException
	{
		String query="Select * from members where member_id=?";
		Connection con=DBConnection.getConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,memberId);
		ResultSet rs=ps.executeQuery();
		return rs.next();
	}

}
