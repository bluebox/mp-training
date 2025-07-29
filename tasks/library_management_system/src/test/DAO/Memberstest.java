package test.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DAO.Memberdao;
import domain.Member;
import domain.checking_enum.Gender;

public class Memberstest {
 	
	private Memberdao memberdao=new Memberdao() ;
	private static int memberid;
	
	@Test
	public void testaddmember() {
		Memberdao memberdao=new Memberdao();
		Member member=new Member("avatar","tharun@gamil.com",12345678L,"oajfoa",Gender.MALE);
		boolean res=memberdao.addMember(member);
		 
		assertEquals(true, res);


		
		
		
	}
	
	@Test
	public void testupdatemember() throws SQLException {
		Memberdao memberdao=new Memberdao();
		
		Member updatemember=new Member("titanic","titanic@gmail.com",8765678L,"ojoij",Gender.MALE);
		boolean res=memberdao.updateMember(updatemember, memberid);
		 assertEquals(true, res);
		
	}
	@Test
	public void testgetallmember() {
		Memberdao memberdao=new Memberdao();
		List<Member> members=memberdao.getAllMembers();
		boolean res=false;
		if(members.size()>0)
			res=true;
		assertEquals(true, res);
	}
	
	

}
