package com.lms.junittesting;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.Member;
import com.lms.serviceImpl.MemberService;
import com.lms.util.DBUtil;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberServiceImplJunit {

    private static MemberService memberService;
    private static Member testMember;

    @BeforeAll
    static void setup() {
        memberService = new MemberService();
    }

    @Test
    @Order(1)
    void testValidate_ValidInput() {
        assertDoesNotThrow(() -> memberService.validate(
                "Saketh Kumar", "saketh.lms@test.com", "9876543210", "Hyderabad", "Male"));
    }

    @Test
    @Order(2)
    void testAddMember() throws InvalidInputException {
        testMember = new Member();
        testMember.setName("Saketh Kumar");
        testMember.setEmail("saketh.lms@test.com");
        testMember.setMobile("9876543210");
        testMember.setGender("Male");
        testMember.setAddress("Hyderabad");

        boolean result = memberService.addMember(testMember);
        assertTrue(result);
    }

    @Test
    @Order(3)
    void testValidate_DuplicateEmail() {
        InvalidInputException ex = assertThrows(InvalidInputException.class, () ->
                memberService.validate("Test Name", "saketh.lms@test.com", "1234567891", "City", "Male"));
        assertTrue(ex.getMessage().toLowerCase().contains("email already exists"));
    }

    @Test
    @Order(4)
    void testValidate_DuplicateMobile() {
        InvalidInputException ex = assertThrows(InvalidInputException.class, () ->
                memberService.validate("Another", "unique.email@test.com", "9876543210", "Hyd", "Male"));
        assertTrue(ex.getMessage().toLowerCase().contains("mobile number already exists"));
    }

    @Test
    @Order(5)
    void testGetAllMembers() {
        List<Member> members = memberService.getAllMembers();
        assertFalse(members.isEmpty());
    }

    @Test
    @Order(6)
    void testGetMemberByMobile() throws InvalidInputException {
        Member member = memberService.getMemberByMobile("9876543210");
        assertNotNull(member);
        assertEquals("Saketh Kumar", member.getName());
    }

    @Test
    @Order(7)
    void testUpdateMember() throws InvalidInputException {
        Member member = memberService.getAllMembers().stream()
                .filter(m -> m.getMobile().equals("9876543210"))
                .findFirst().orElse(null);

        assertNotNull(member);
        member.setAddress("Updated Hyderabad");

        boolean result = memberService.updateMember(member);
        assertTrue(result);

        Member updated = memberService.getMemberByMobile("9876543210");
        assertEquals("Updated Hyderabad", updated.getAddress());
    }
    @AfterAll
    static void cleanup() {
        Member m = memberService.getAllMembers().stream()
            .filter(mem -> "9876543210".equals(mem.getMobile()))
            .findFirst().orElse(null);
        if (m != null) {
            try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM members WHERE member_id = " + m.getMemberId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}