//package com.LibraryManagement.Test;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.LibraryManagement.exception.MemberDAOException;
//import com.LibraryManagement.models.Member;
//import com.LibraryManagement.service.implementation.MemberServiceImplementation;
//
//public class MemberServiceImplementationTest {
//	
//
//	    private MemberServiceImplementation memberService;
//
//	    @Before
//	    public void setUp() {
//	        memberService = new MemberServiceImplementation();
//	    }
//
//
//	    @Test(expected = MemberDAOException.class)
//	    public void testRegisterMember_NullMember_ThrowsException() {
//	        memberService.registerMember(null);
//	    }
//	    
//	    @Test(expected = MemberDAOException.class)
//	    public void testUpdateMember_NullMember_ThrowsException() {
//	        memberService.updateMember(null);
//	    }
//	    
//
//	    @Test(expected = MemberDAOException.class)
//	    public void testUpdateMember_InvalidId_ThrowsException() {
//	        Member member = new Member("Bob", "bob@example.com", 9100135264L, "Male", "Mumbai");
////	        member.setMemberId(); // Invalid ID
//	        memberService.updateMember(member);
//	    }
//
//	    @Test
//	    public void testGetAllMembers_ReturnsList() {
//	        List<Member> members = memberService.getAllMembers();
//	        assertNotNull(members);
//	        assertTrue(members.size() >= 0); // Basic check
//	    }
//	
//
//	
//}