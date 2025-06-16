package com.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.Service.MemberService;
import com.Exception.*;
import com.Models.Member;
class MemberServiceTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	private MemberService memberService;

    @Before
    public void setUp() {
        memberService = new MemberService();
    }

    @org.junit.Test(expected = InvalidInputException.class)
    public void testAddMember_InvalidEmail() throws Exception {
        Member member = new Member(0, "Alice", "", "1234567890", 'F', "Some Address");
        memberService.addMember(member);
    }
}
