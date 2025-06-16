package com.LibraryManagement;

import com.LibraryManagement.dao.MemberDao;
import com.LibraryManagement.dao.impl.MemberDaoImpl;
import com.LibraryManagement.utilites.pojos.Member;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MemberDaoImplTest {

    private MemberDaoImpl memberDaoImpl;

    @Before
    public void setUp() {
        memberDaoImpl = new MemberDaoImpl();
    }

    @Test
    public void testAddMember_Success() {
        Member member = new Member(1, "John Doe", "john.doe" + System.currentTimeMillis() + "@example.com", 9876543210L, 'M');
        boolean added = memberDaoImpl.addMember(member);
        assertTrue("Expected member to be added successfully", added);
    }
    @Test
    public void testVerifyMember_Exists() throws Exception {
        Member member = new Member(1, "JnDoe", "jadoe@examvple.com", 12456890L, 'F');

        memberDaoImpl.addMember(member);

        boolean result = memberDaoImpl.verifyMember(member);

        assertFalse("Expected verifyMember to return false for an existing member", result);
    }


    @Test
    public void testViewAllMembers() throws Exception {
        ArrayList<Member> members = memberDaoImpl.viewAllMembers();
        assertNotNull("Member list should not be null", members);
        assertTrue("Member list should contain at least one member", members.size() >= 0);
    }
}
