package test;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import DAO.BookImplementation;
import DAO.IssueRecordImplementation;
import DAO.MemberDAOImpl;
import Domain.Book;
import Domain.Member;
import Service.ServiceLayer;

public class ReportsTest {
	private static final String url="jdbc:mysql://127.0.0.1:3306/";
	private static  final String username="root";
	private static final String password="root";

	private IssueRecordImplementation Issuedao;
	private BookImplementation Bookdao;
	private MemberDAOImpl Memberdao;
	private ServiceLayer serviceobj;
	
	@Before
    public void setUp() {
        Issuedao = new IssueRecordImplementation();
        Bookdao = new BookImplementation();
        Memberdao = new MemberDAOImpl();
        serviceobj=new ServiceLayer();
    }
	
	
	@Test
	public void testgetOverdueBooks() throws Exception {
	List<Book> bookslist=serviceobj.getOverdueBooks();
	assertNotNull(bookslist);
	}
	
	@Test
	public void testgetBookCountByCategory() throws Exception {
		Map<String, Long> bookcategorymap=serviceobj.getBookCountByCategory();
		assertNotNull(bookcategorymap);
	}
	
	@Test
	public void testgetMembersWithActiveIssuedBooks() throws Exception {
		List<Member> ActiveMembers =serviceobj.getMembersWithActiveIssuedBooks();
		assertNotNull(ActiveMembers);
	}
	
}
