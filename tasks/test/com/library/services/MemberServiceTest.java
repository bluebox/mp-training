package test.com.library.services;

package test.com.library.services;


import main.com.library.domain.Member;
import main.com.library.services.MemberService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberServiceTest {

    private MemberService memberService;

    @Before
    public void setUp() {
        memberService = new MemberService();
    }

    @Test
    public void testUpdateMember_Success() {
        Member member = new Member(1,"Updated Name","updated@example.com",9876543210L,'M',"Updated Address");

        boolean result = memberService.updateMember(member);
        assertTrue("Member should be updated successfully", result);
    }

    @Test
    public void testUpdateMember_Failure() {
        Member member = new Member(9999,"Ghost","ghost@example.com",1234567890L,'F',"Nowhere");

        boolean result = memberService.updateMember(member);
        assertFalse("Updating non-existent member should fail", result);
    }
}