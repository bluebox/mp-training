package com.lms.junittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lms.model.Member;
import com.lms.serviceImpl.MemberService;
import com.lms.util.DBUtil;

class MemberServiceImplJunit {

    private MemberService memberService;
    private Member testMember;
    private String testEmail;
    private String testMobile;

    @BeforeEach
    void setup() {
        memberService = new MemberService();
        testEmail = "test" + System.currentTimeMillis() + "@lms.com";
        testMobile = "9" + System.currentTimeMillis() % 1000000000;
        testMember = new Member();
        testMember.setName("Test User");
        testMember.setEmail(testEmail);
        testMember.setMobile(testMobile);
        testMember.setGender("Male");
        testMember.setAddress("Test Address");
    }

    @Test
    void testAddMember() throws Exception {
        boolean result = memberService.addMember(testMember);
        assertTrue(result);
    }

    @Test
    void testGetAllMembers() throws Exception {
        memberService.addMember(testMember);
        List<Member> members = memberService.getAllMembers();
        assertFalse(members.isEmpty());
    }

    @Test
    void testGetMemberByMobile() throws Exception {
        memberService.addMember(testMember);
        Member member = memberService.getMemberByMobile(testMobile);
        assertNotNull(member);
        assertEquals("Test User", member.getName());
    }

    @Test
    void testUpdateMember() throws Exception {
        memberService.addMember(testMember);
        Member member = memberService.getMemberByMobile(testMobile);
        assertNotNull(member);
        member.setAddress("Updated Address");
        boolean result = memberService.updateMember(member);
        assertTrue(result);
        Member updated = memberService.getMemberByMobile(testMobile);
        assertEquals("Updated Address", updated.getAddress());
    }

    @AfterEach
    void cleanup() {
        try {
            Member m = memberService.getMemberByMobile(testMobile);
            if (m != null) {
                try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("DELETE FROM members WHERE member_id = " + m.getMemberId());
                }
            }
        } catch (Exception ignored) {}
    }
}