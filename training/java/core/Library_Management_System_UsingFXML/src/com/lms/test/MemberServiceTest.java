package com.lms.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.lms.model.Member;
import com.lms.service.MemberService;
import com.lms.service.impl.MemberServiceImpl;

import java.util.List;

public class MemberServiceTest {
    
    private MemberService memberService;
    private Member testMember;
    
    @Before
    public void setUp() {
        memberService = new MemberServiceImpl();
        
        testMember = new Member("Test User", "test@email.com", "1234563890", 'M', "Test Address");
    }
    
    @Test
    public void testAddNewMember_Success() {
        // Add new member
        memberService.addNewMember(testMember);
        
        // Verify member was added by checking ID is set
        assertTrue(testMember.getMember_Id() != 0);
        
        // Verify member exists in database
        Member retrievedMember = memberService.getMemberById(testMember.getMember_Id());
        assertNotNull(retrievedMember);
        assertEquals(testMember.getMember_Name(), retrievedMember.getMember_Name());
        assertEquals(testMember.getEmail(), retrievedMember.getEmail());
        assertEquals(testMember.getMobile_No(), retrievedMember.getMobile_No());
        assertEquals(testMember.getGender(), retrievedMember.getGender());
        assertEquals(testMember.getAddress(), retrievedMember.getAddress());
    }
    
    @Test
    public void testGetAllMembers_Success() {
    	testMember = new Member("Get all members Test User", "test@email.com", "1232363892", 'M', "Test Address");
        // Add a test member first
        memberService.addNewMember(testMember);
        
        // Get all members
        List<Member> members = memberService.getAllMembers();
        
        // Verify list is not null and contains members
        assertNotNull(members);
        assertFalse(members.isEmpty());
        
        // Verify our test member is in the list
        boolean found = members.stream()
            .anyMatch(m -> m.getEmail().equals(testMember.getEmail()));
        assertTrue(found);
    }
    
    @Test
    public void testUpdateMember_Success() {
        // First add a member
    	testMember = new Member("Update Test User", "test@email.com", "1234567892", 'M', "Test Address");
        memberService.addNewMember(testMember);
        int memberId = testMember.getMember_Id();
        
        // Update member details
        testMember.setMember_Name("Updated test Name");
        testMember.setEmail("updated@email.com");
        testMember.setMobile_No("9876513210");
        testMember.setAddress("Updated Address");
        
        memberService.updateMember(testMember);
        
        // Verify updates
        Member updatedMember = memberService.getMemberById(memberId);
        assertNotNull(updatedMember);
        assertEquals("Updated test Name", updatedMember.getMember_Name());
        assertEquals("updated@email.com", updatedMember.getEmail());
        assertEquals("9876513210", updatedMember.getMobile_No());
        assertEquals("Updated Address", updatedMember.getAddress());
    }
    
    
}
