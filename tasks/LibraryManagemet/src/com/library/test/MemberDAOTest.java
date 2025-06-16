package com.library.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.dao.MemberDAO;
import com.library.domain.Member;
import com.library.utilities.ConnectionMaker;

class MemberDAOTest {

    private MemberDAO memberDAO;

    @BeforeEach
    void setUp() throws Exception {
        memberDAO = new MemberDAO("test_member");
        try (Connection conn = ConnectionMaker.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM test_member"); // clean before each test
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        try (Connection conn = ConnectionMaker.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM test_member"); // clean after each test
        }
    }

    @Test
    void testAddValidMember() {
        Member member = new Member("Alice", "alice@example.com", 9876543210L, 'F', "Wonderland");
        boolean result = memberDAO.addMember(member);
        assertTrue(result, "Member should be added successfully");
    }

    @Test
    void testWithEmptyName() {
        Member member = new Member("", "noName@example.com", 1234567890L, 'M', "NoName Street");
        boolean result = memberDAO.addMember(member);
        assertTrue(result, "Database might allow empty name unless constrained. Add service validation to prevent this.");
    }

    @Test
    void testWithNullEmail() {
        Member member = new Member("Bob", null, 1112223334L, 'M', "Nowhere");
        boolean result = memberDAO.addMember(member);
        assertTrue(result, "Null email should be handled by service or DB constraints");
    }
    
}
