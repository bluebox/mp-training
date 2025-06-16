package test.com.library;

import com.library.domain.Member;
import com.library.services.MemberService;
import com.library.util.DB;
import org.junit.*;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class MemberServiceTest {

    private static Connection conn;
    private static MemberService service;
    private static final int TEST_MEMBER_ID = 9999;
    
    private MemberService memberService;

    @Before
    public void setUp() {
        memberService = new MemberService();
    }

    @Test
    public void testUpdateMember_Success() {

        Member member = new Member(1,"Updated Name","updated@example.com",9876543210L,'M',"Updated Address");

        boolean result;
		try {
			result = memberService.updateMember(member);
			assertTrue("Member should be updated successfully", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testUpdateMember_Failure() {
        Member member = new Member(9999,"Ghost","ghost@example.com",1234567890L,'F',"Nowhere");

        boolean result;
		try {
			result = memberService.updateMember(member);
			assertFalse("Updating non-existent member should fail", result);
		} catch (Exception e) {

			e.printStackTrace();
		}
    }

    @BeforeClass
    public static void setup() throws Exception {
        conn = DB.getConnection();
        conn.setAutoCommit(false);
        service = new MemberService();

        
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM members WHERE MemberId = ?");
             PreparedStatement psLog = conn.prepareStatement("DELETE FROM members_log WHERE MemberId = ?")) {
            ps.setInt(1, TEST_MEMBER_ID);
            psLog.setInt(1, TEST_MEMBER_ID);
            ps.executeUpdate();
            psLog.executeUpdate();
        }

        
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO members (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setInt(1, TEST_MEMBER_ID);
            ps.setString(2, "Test User");
            ps.setString(3, "test@example.com");
            ps.setLong(4, 9876543210L);
            ps.setString(5, "M");
            ps.setString(6, "Old Address");
            ps.executeUpdate();
        }
    }
    

    @Test
    public void testFetchMembers_success() throws Exception {
        List<Member> members = service.fetchmembers();
        assertNotNull(members);
        assertTrue(members.stream().anyMatch(m -> m.getMemberId() == TEST_MEMBER_ID));
    }

    @Test
    public void testAddMember_doesNotThrowException() {
        
        Member dummy = new Member(TEST_MEMBER_ID + 1, "Dummy", "dummy@abc.com", 9999999999L, 'F', "Dummy Addr");
        service.addMember(dummy); 
    }

    @Test
    public void testUpdateMember_validData_successfullyUpdatesAndLogs() throws Exception {
        Member updated = new Member(TEST_MEMBER_ID, "Updated User", "updated@example.com", 9999999991L, 'F', "New Address");
        service.updateMember(updated);

        
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM members WHERE MemberId = ?")) {
            ps.setInt(1, TEST_MEMBER_ID);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals("Updated User", rs.getString("Name"));
            assertEquals("F", rs.getString("Gender"));
        }

        
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM members_log WHERE MemberId = ?")) {
            ps.setInt(1, TEST_MEMBER_ID);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals("Test User", rs.getString("Name")); // original value
            assertEquals("M", rs.getString("Gender"));
        }
    }

    @Test(expected = Exception.class)
    public void testUpdateMember_invalidEmail_throwsException() throws Exception {
        Member bad = new Member(TEST_MEMBER_ID, "Valid", "bad-email", 9999999990L, 'M', "Addr");
        service.updateMember(bad);
    }

    @Test(expected = Exception.class)
    public void testUpdateMember_emptyName_throwsException() throws Exception {
        Member bad = new Member(TEST_MEMBER_ID, "", "test@abc.com", 9999999990L, 'M', "Addr");
        service.updateMember(bad);
    }

    @Test(expected = Exception.class)
    public void testUpdateMember_invalidGender_throwsException() throws Exception {
        Member bad = new Member(TEST_MEMBER_ID, "Test", "test@abc.com", 9999999990L, 'X', "Addr");
        service.updateMember(bad);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        conn.rollback();
        conn.close();
    }
}
