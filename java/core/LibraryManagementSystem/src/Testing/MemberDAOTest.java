package Testing;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import Pojo.*;
import Dao.*;
public class MemberDAOTest {
	private MemberDAO memberDAO;
    public MemberDAOTest() throws SQLException {
	 memberDAO = new MemberDAO();
    }
    @Test
    public void testAddMember() throws Exception {
        Member member = new Member();
        member.setName("Test Member");
        member.setEmail("test.member@example.com");
        member.setMobile(9876543210L);
        member.setGender('M');
        member.setAddress("Test Address");
        memberDAO.addMember(member);
    }

    @Test
    public void testUpdateMember() throws Exception {
        Member member = memberDAO.getMemberById(1);
        member.setAddress("Updated Address");
        memberDAO.updateMember(member);
    }

    @Test
    public void testViewAllMembers() throws SQLException {
        List<Member> members = memberDAO.getAllMembers();
        assertNotNull(members);
    }
}

