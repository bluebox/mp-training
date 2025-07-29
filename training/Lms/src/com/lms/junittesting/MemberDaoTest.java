package com.lms.junittesting;

import com.lms.daoImpl.MemberDao;
import com.lms.model.Member;
import com.lms.util.DBUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberDaoTest {

    private static final String TEST_MOBILE = "8888888888";
    private static final String TEST_EMAIL = "test@gmail.com";
    private static int testMemberId;

    @BeforeAll
    static void setup() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM issue_books WHERE member_id IN (SELECT member_id FROM members WHERE mobile = '" + TEST_MOBILE + "')");
            stmt.executeUpdate("DELETE FROM members WHERE mobile = '" + TEST_MOBILE + "' OR email = '" + TEST_EMAIL + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Member testMember = new Member(1, "JUnit Member", TEST_EMAIL, TEST_MOBILE, "Other", "JUnit Address");
        MemberDao.addMember(testMember);
        Member added = MemberDao.getMemberByMobile(TEST_MOBILE);
        testMemberId = added.getMemberId();
    }

    @Test
    @Order(1)
    void testAddMember() {
        Member member = MemberDao.getMemberById(testMemberId);
        assertNotNull(member);
        assertEquals("JUnit Member", member.getName());
    }

    @Test
    @Order(2)
    void testUpdateMember() {
        Member update = new Member(testMemberId, "Updated JUnit", TEST_EMAIL, TEST_MOBILE, "Other", "New Address");
        boolean result = MemberDao.updateMember(update);
        assertTrue(result);

        Member updated = MemberDao.getMemberById(testMemberId);
        assertEquals("Updated JUnit", updated.getName());
        assertEquals("New Address", updated.getAddress());
    }

    @Test
    @Order(3)
    void testGetMemberById() {
        Member member = MemberDao.getMemberById(testMemberId);
        assertNotNull(member);
        assertEquals(testMemberId, member.getMemberId());
    }

    @Test
    @Order(4)
    void testGetAllMembers() {
        List<Member> members = MemberDao.getAllMembers();
        assertNotNull(members);
        assertTrue(members.size() > 0);
    }

    @Test
    @Order(5)
    void testGetMemberByMobile() {
        Member member = MemberDao.getMemberByMobile(TEST_MOBILE);
        assertNotNull(member);
        assertEquals(TEST_MOBILE, member.getMobile());
    }

    @Test
    @Order(6)
    void testGetMemberByEmail() {
        Member member = MemberDao.getMemberByEmail(TEST_EMAIL);
        assertNotNull(member);
        assertEquals(TEST_EMAIL, member.getEmail());
    }

    @Test
    @Order(7)
    void testGenerateNewMemberId() {
        int newId = MemberDao.generateNewMemberId();
        assertTrue(newId > 0);
    }

    @AfterAll
    static void cleanup() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM issue_books WHERE member_id = " + testMemberId);
            stmt.executeUpdate("DELETE FROM members WHERE member_id = " + testMemberId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}