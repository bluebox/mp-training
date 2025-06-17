package Library.src.test.java.com.LibraryManagement.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Library.src.main.java.com.LibraryManagement.dao.MemberDAO;
import Library.src.main.java.com.LibraryManagement.model.Member;
import Library.src.main.java.com.LibraryManagement.service.MemberServiceImpl;

public class MemberServiceImplTest {

    private MemberDAO memberDAO;
    private MemberServiceImpl memberService;

    @Before
    public void setUp() {
        memberDAO = mock(MemberDAO.class);
        memberService = new MemberServiceImpl(memberDAO);
    }
//int memberId, String name, String email, String mobile, char gender, String address
    @Test
    public void testAddMember_Valid() throws SQLException {
        Member member = new Member(1, "John", "john@example.com","7337584295",'M',"kokapet");
        memberService.addMember(member);
        verify(memberDAO).addMember(member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMember_Invalid() throws SQLException {
        memberService.addMember(new Member(0, null, null,"123",'F',"koti"));
    }

    @Test
    public void testUpdateMember_Valid() throws SQLException {
    	 Member member = new Member(1, "John", "john@example.com","7337584295",'M',"kokapet");
        memberService.updateMember(member);
        verify(memberDAO).updateMember(member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMember_Invalid() throws SQLException {
        memberService.updateMember(new Member(0, "Jane", "jane@example.com","6304328860",'F',"gachibowli"));
    }

    @Test
    public void testGetMemberById_Valid() throws SQLException {
        Member member = new Member(1, "John", "john@example.com","7337584295",'M',"kokapet");
        when(memberDAO.getMemberById(1)).thenReturn(member);
        assertEquals("John", memberService.getMemberById(1).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMemberById_InvalidId() throws SQLException {
        memberService.getMemberById(0);
    }

    @Test
    public void testGetAllMembers() throws SQLException {
        List<Member> members = Arrays.asList(
            new Member(1, "John", "john@example.com","7337584295",'M',"kokapet"),
            new Member(1, "John", "john@example.com","7337584295",'M',"kokapet")
        );
        when(memberDAO.getAllMembers()).thenReturn(members);
        assertEquals(2, memberService.getAllMembers().size());
    }
}
