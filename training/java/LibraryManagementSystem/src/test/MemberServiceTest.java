package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Member;
import com.service.MemberService;

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
    void testAddMemberInvalidName() {
        Member member = new Member(0, "", "email@example.com", "1234567890");
        assertThrows(Exception.class, () -> memberService.addMember(member));
    }

    @Test
    void testAddMemberValidMember() throws Exception {
        Member member = new Member(0, "Alice", "alice@example.com", "1234567890");
        memberService.addMember(member);
        assertTrue(true); 
    }
} 