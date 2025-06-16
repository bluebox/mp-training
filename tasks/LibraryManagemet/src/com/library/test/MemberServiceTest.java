package com.library.test;

import com.library.dao.TestMemberDAO;
import com.library.domain.Member;
import com.library.service.MemberService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
    private MemberService service;

    @BeforeEach
    public void setup() {
        service = new MemberService(new TestMemberDAO());
    }

    @Test
    public void testValidMemberAddition() {
        Member m = new Member("John", "john@example.com", 9876543210L, 'M', "New York");
        assertTrue(service.addMember(m));
    }

    @Test
    public void testAddMemberWithInvalidEmail() {
        Member m = new Member("John", "invalid-email", 9876543210L, 'M', "New York");
        assertThrows(IllegalArgumentException.class, () -> service.addMember(m));
    }

    @Test
    public void testAddMemberWithEmptyName() {
        Member m = new Member("", "john@example.com", 9876543210L, 'M', "New York");
        assertThrows(IllegalArgumentException.class, () -> service.addMember(m));
    }

    @Test
    public void testAddMemberWithInvalidMobile() {
        Member m = new Member("John", "john@example.com", 123L, 'M', "New York");
        assertThrows(IllegalArgumentException.class, () -> service.addMember(m));
    }

    @Test
    public void testAddMemberWithInvalidGender() {
        Member m = new Member("John", "john@example.com", 9876543210L, 'X', "New York");
        assertThrows(IllegalArgumentException.class, () -> service.addMember(m));
    }

    @Test
    public void testAddNullMember() {
        assertThrows(IllegalArgumentException.class, () -> service.addMember(null));
    }
}
