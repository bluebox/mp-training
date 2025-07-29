package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import DAO.MemberDAOImpl;
import Domain.Member;
import Domain.Gender;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Memberstest {
	
 	private MemberDAOImpl memberdao=new MemberDAOImpl();
	
	@Test
	public void test1sucessaddmember() throws Exception {
		MemberDAOImpl memberdao=new MemberDAOImpl();
		Member member=new Member(4,"r454","tnerq34@l.com","12348043",Gender.MALE,"o");
		int res=memberdao.addMember(member);
		assertEquals(1, res);	
	}

	@Test
	public void test2failaddmember() throws Exception {
		MemberDAOImpl memberdao=new MemberDAOImpl();
		Member member=new Member(4,"r454","tnerq34@l.com","12348043",Gender.MALE,"o");
		int res=memberdao.addMember(member);
		System.out.println(res);
		assertEquals(0, res);	
		
	}
	
	
	
	@Test
	public void test3successupdatemember() throws Exception {	
		Member updatemember=new Member(1,"tiic","tonic@gl.com","8765678",Gender.MALE,"ojoij");		
		Member res=memberdao.updateMember(updatemember);
		assertNotNull(res);
		
	}
	


	@Test
	public void testgetallmember() throws Exception {
			List<Member> members=memberdao.getAllMembers();
			System.out.println(members.size()+" memberssize");
	       assertNotNull(members);
	}
	
	

}
