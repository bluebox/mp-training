package test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Member;
import service.MemberService;

class MemberServiceTest {
    MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService();
    }

    @Test
    void testNotYetImplemented() {
        fail("Not yet implemented");
    }

    @Test
    void testAddMember_InvalidName() {
        Member member = new Member(0, "", "email@example.com", 1234567890L,'M',"Hyderabad");
        assertThrows(Exception.class, () -> memberService.addMember(member));
    }

    @Test
    void testAddMember_ValidMember() throws Exception {
        Member member = new Member(0, "Alice", "alice@example.com", 1234567890L,'M',"Hyderabad");
        memberService.addMember(member);
        assertTrue(true);
    }
} 