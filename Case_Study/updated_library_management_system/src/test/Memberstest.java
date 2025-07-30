package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DAO.Memberdao;
import domain.Member;
import domain.checking_enum.Gender;

public class Memberstest {
 	
	private Memberdao memberdao;
	private static int memberid;
	
	@Test
	public void testaddmember() {
		Memberdao memberdao=new Memberdao();
		Member member=new Member("avatar","tharun@gamil.com",12345678L,"oajfoa",Gender.MALE);
		boolean res=false;
		try {
			res = memberdao.addMember(member);
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		assertEquals(true, res);


		
		
		
	}
	@Test
	public void testaddmemberfail() {
		Memberdao memberdao=new Memberdao();
		Member member=new Member("avatar","tharun@gamil.com",12345678L,"oajfoa",Gender.MALE);
		boolean res=false;
		try {
			res = memberdao.addMember(member);
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		assertEquals(false, res);


		
		
		
	}
	
	@Test
	public void testupdatemember() {
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
	
	@Test
	public void testupdatememberfail() {
		Memberdao memberdao=new Memberdao();
		
		Member updatemember=new Member("titanic","titanic@gmail.com",8765678L,"ojoij",Gender.MALE);
		boolean res=memberdao.updateMember(updatemember, memberid);
		 assertEquals(false, res);
		
	}

}
