package com.casestudy.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.casestudy.dao.MembersDao;
import com.casestudy.domain.Gender;
import com.casestudy.domain.Member;
import com.casestudy.util.DBUtil;

public class MembersDaoTest {

    private static MembersDao membersDao;
    private static Member testMember;

    @BeforeClass
    public static void setUpBeforeClass() {
        membersDao = new MembersDao();
        testMember = new Member("Test User", "testuser@example.com", 9999999999L, Gender.MALE, "Test Address");
        membersDao.addMember(testMember);
    }

    @Test
    public void testAddMember() {
        List<Member> members = membersDao.getAllMembers();
        boolean found = false;
        for (Member m : members) {
            if (m.getEmail().equals("testuser@example.com")) {
                found = true;
                break;
            }
        }
        assertTrue("Test member should be found after addition", found);
    }

    @Test
    public void testUpdateMember() {
        List<Member> members = membersDao.getAllMembers();
        Member memberToUpdate = null;

        for (Member m : members) {
            if (m.getEmail().equals("testuser@example.com")) {
                memberToUpdate = m;
                break;
            }
        }

        assertNotNull("Test member not found for update", memberToUpdate);

        memberToUpdate.setName("Updated User");
        memberToUpdate.setMobile(8888888888L);
        boolean updated = membersDao.updateMember(memberToUpdate);

        assertTrue("Update should succeed", updated);

        List<Member> updatedMembers = membersDao.getAllMembers();
        boolean nameUpdated = false;

        for (Member m : updatedMembers) {
            if (m.getEmail().equals("testuser@example.com") && m.getName().equals("Updated User")) {
                nameUpdated = true;
                break;
            }
        }

        assertTrue("Name should be updated", nameUpdated);
    }

    @Test
    public void testGetAllMembers() {
        List<Member> members = membersDao.getAllMembers();
        assertNotNull("Member list should not be null", members);
        assertTrue("There should be at least one member", members.size() > 0);
    }

    @Test
    public void testFindMember() {
        List<Member> members = membersDao.getAllMembers();
        assertFalse("Member list should not be empty", members.isEmpty());

        int idToFind = members.get(0).getMemberId();
        boolean exists = membersDao.findMember(idToFind);
        assertTrue("Member with valid ID should be found", exists);

        boolean notExists = membersDao.findMember(-12345); // invalid ID
        assertFalse("Member with invalid ID should not be found", notExists);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Member WHERE email = ?");
        ps.setString(1, "testuser@example.com");
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
