package test.com.library;

import com.library.services.IssueService;
import com.library.util.DB;
import org.junit.*;

import java.sql.*;

import static org.junit.Assert.*;

public class IssueServiceTest {

    private static Connection conn;
    private static final int TEST_BOOK_ID = 7001;
    private static final int TEST_MEMBER_ID = 8001;
    private IssueService issueService;

    @BeforeClass
    public static void setup() throws Exception {
        conn = DB.getConnection();
        conn.setAutoCommit(false);

        
        try (PreparedStatement ps1 = conn.prepareStatement("DELETE FROM books WHERE BookId = ?");
             PreparedStatement ps2 = conn.prepareStatement("DELETE FROM members WHERE MemberId = ?");
             PreparedStatement ps3 = conn.prepareStatement("DELETE FROM issue_records WHERE BookId = ?")) {
            ps1.setInt(1, TEST_BOOK_ID);
            ps2.setInt(1, TEST_MEMBER_ID);
            ps3.setInt(1, TEST_BOOK_ID);
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
        }

       
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO books (BookId, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setInt(1, TEST_BOOK_ID);
            ps.setString(2, "Test Book");
            ps.setString(3, "Author");
            ps.setString(4, "Test");
            ps.setString(5, "A");
            ps.setString(6, "A");
            ps.executeUpdate();
        }

       
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO members (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setInt(1, TEST_MEMBER_ID);
            ps.setString(2, "Member");
            ps.setString(3, "member@example.com");
            ps.setLong(4, 9999999999L);
            ps.setString(5, "M");
            ps.setString(6, "Test Address");
            ps.executeUpdate();
        }
    }

    @Before
    public void init() {
        issueService = new IssueService();
    }

    @Test
    public void testIssueBook_success() throws Exception {
        issueService.issueBook(TEST_BOOK_ID, TEST_MEMBER_ID);

       
        try (PreparedStatement ps = conn.prepareStatement("SELECT Availability FROM books WHERE BookId = ?")) {
            ps.setInt(1, TEST_BOOK_ID);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals("I", rs.getString("Availability"));
        }

        
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM issue_records WHERE BookId = ? AND MemberId = ?")) {
            ps.setInt(1, TEST_BOOK_ID);
            ps.setInt(2, TEST_MEMBER_ID);
            ResultSet rs = ps.executeQuery();
            assertTrue("Issue record not inserted", rs.next());
            assertEquals("I", rs.getString("Status"));
        }
    }

    @Test(expected = Exception.class)
    public void testIssueBook_whenBookNotAvailable() throws Exception {
        
        try (PreparedStatement ps = conn.prepareStatement("UPDATE books SET Availability = 'I' WHERE BookId = ?")) {
            ps.setInt(1, TEST_BOOK_ID);
            ps.executeUpdate();
        }

        issueService.issueBook(TEST_BOOK_ID, TEST_MEMBER_ID);
    }

    @Test(expected = Exception.class)
    public void testIssueBook_whenMemberDoesNotExist_shouldThrow() throws Exception {
       
        try (PreparedStatement ps = conn.prepareStatement("UPDATE books SET Availability = 'A' WHERE BookId = ?")) {
            ps.setInt(1, TEST_BOOK_ID);
            ps.executeUpdate();
        }

       
        issueService.issueBook(TEST_BOOK_ID, 999999);
    }

    @Test
    public void testReturnBook_shouldNotThrow() throws Exception {
        
        issueService.returnBook(TEST_BOOK_ID); 
    }

    @AfterClass
    public static void tearDown() throws Exception {
        conn.rollback(); 
        conn.close();
    }
}
