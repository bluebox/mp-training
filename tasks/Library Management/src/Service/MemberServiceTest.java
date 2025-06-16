package Service;

import casestudy.Member;
import casestudy.LibraryException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MemberServiceTest {

    private MemberService memberService;

    @Before
    public void setUp() {
        memberService = new MemberService();
    }

    @Test(expected = LibraryException.class)
    public void testAddMemberWithEmptyName() throws LibraryException {
        Member member = new Member("", "abc@gmail.com", 1234567890L, 'M', "address");
        memberService.addMember(member);
    }

    @Test(expected = LibraryException.class)
    public void testAddMemberWithInvalidEmail() throws LibraryException {
        Member member = new Member("John", "invalid-email", 1234567890L, 'M', "Hyderabad");
        memberService.addMember(member);
    }

    @Test(expected = LibraryException.class)
    public void testUpdateMemberWithInvalidId() throws LibraryException {
        Member member = new Member("M1", "m1@example.com", 9899988878L, 'F', "Chennai");
        member.setMemberId(0);
        memberService.updateMember(member);
    }

    @Test
    public void testGetAllMembers() throws LibraryException {
        List<Member> members = memberService.getAllMembers();
        assertNotNull(members);
    }
}
