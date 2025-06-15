package com.casestudyjunit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.casestudy.Gender;
import com.casestudy.Member;

public class MemberTest {

    private Member member;

    @Before
    public void setUp() {
        member = new Member(1, "John Doe", "john@example.com", 9876543210L, Gender.MALE, "123 Street");
    }

    @Test
    public void testConstructorWithAllFields() {
        assertEquals(1, member.getMemberId());
        assertEquals("John Doe", member.getName());
        assertEquals("john@example.com", member.getEmail());
        assertEquals(9876543210L, member.getMobile());
        assertEquals(Gender.MALE, member.getGender());
        assertEquals("123 Street", member.getAddress());
    }

    @Test
    public void testConstructorWithoutId() {
        Member newMember = new Member("Jane Doe", "jane@example.com", 1234567890L, Gender.FEMALE, "456 Lane");

        assertEquals("Jane Doe", newMember.getName());
        assertEquals("jane@example.com", newMember.getEmail());
        assertEquals(1234567890L, newMember.getMobile());
        assertEquals(Gender.FEMALE, newMember.getGender());
        assertEquals("456 Lane", newMember.getAddress());
    }

    @Test
    public void testSettersAndGetters() {
        member.setMemberId(2);
        member.setName("Alice");
        member.setEmail("alice@example.com");
        member.setMobile(9999999999L);
        member.setGender(Gender.FEMALE);
        member.setAddress("New Address");

        assertEquals(2, member.getMemberId());
        assertEquals("Alice", member.getName());
        assertEquals("alice@example.com", member.getEmail());
        assertEquals(9999999999L, member.getMobile());
        assertEquals(Gender.FEMALE, member.getGender());
        assertEquals("New Address", member.getAddress());
    }

    @Test
    public void testToString() {
        String output = member.toString();
        assertTrue(output.contains("memberId=1"));
        assertTrue(output.contains("name=John Doe"));
        assertTrue(output.contains("email=john@example.com"));
        assertTrue(output.contains("mobile=9876543210"));
        assertTrue(output.contains("gender=MALE"));
        assertTrue(output.contains("address=123 Street"));
    }
}
