package DAO;

import casestudy.LibraryException;
import casestudy.Member;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MemberDAOTest {

    private MemberDAO memberDAO;

    @Before
    public void setUp() {
        memberDAO = new MemberDAO();
    }

    @Test
    public void testAddAndGetAllMembers() throws LibraryException {
        Member member = new Member("A1", "A1" + System.currentTimeMillis() + "@test.com",
                System.currentTimeMillis() % 10000000000L, 'F', "Hyderabad");
        memberDAO.addMember(member);

        List<Member> members = memberDAO.getAllMembers();
        boolean found = false;
        for (Member m : members) {
            if (m.getEmail().equals(member.getEmail())) {
                found = true;
                break;
            }
        }
        assertTrue("Newly added member not found in the list", found);
    }

    @Test
    public void testUpdateMember() throws LibraryException {
        List<Member> members = memberDAO.getAllMembers();
        assertFalse("No members available to test update", members.isEmpty());

        Member member = members.get(members.size() - 1);
        member.setName("Updated Name");
        memberDAO.updateMember(member);

        List<Member> updatedMembers = memberDAO.getAllMembers();
        Member updated = null;
        for (Member m : updatedMembers) {
            if (m.getMemberId() == member.getMemberId()) {
                updated = m;
                break;
            }
        }

        assertNotNull("Updated member not found", updated);
        assertEquals("Updated Name", updated.getName());
    }
}
