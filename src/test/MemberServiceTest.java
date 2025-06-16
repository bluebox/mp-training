package test;

import model.Member;
import service.MemberService;
import exception.InvalidInputException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MemberServiceTest {

    private MemberService memberService;

    @Before
    public void setup() {
        memberService = new MemberService();
    }

    @Test
    public void testAddAndFetchMembers() throws Exception {
        Member m = new Member();
        m.setName("Alice");
        m.setEmail("alice@example.com");
        m.setMobile(9876543210L);
        m.setGender('F');
        m.setAddress("Wonderland");

        memberService.addMember(m);
        List<Member> members = memberService.getAllMembers();

        assertTrue(
            members.stream().anyMatch(mem -> mem.getEmail().equals("alice@example.com"))
        );
    }

    @Test(expected = InvalidInputException.class)
    public void testAddMemberWithEmptyName() throws Exception {
        Member m = new Member();
        m.setName("");
        m.setEmail("bob@example.com");
        m.setMobile(1234567890L);
        m.setGender('M');
        m.setAddress("Nowhere");

        memberService.addMember(m);
    }
}
