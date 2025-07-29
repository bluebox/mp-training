package test.DAO;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import DAO.Databasemanager;
import DAO.Issue_RecordDAO;
import domain.Book;
import domain.Member;
import Service.Reports;

public class ReportsTest {
	
	private Issue_RecordDAO Issuedao;
	private Databasemanager Bookdao;
	private DAO.Memberdao Memberdao;
	private Reports serviceobj;
	
	@Before
    public void setUp() {
        Issuedao = new Issue_RecordDAO();
        Bookdao = new Databasemanager();
        Memberdao = new DAO.Memberdao();
        serviceobj=new Reports();
    }
	
	
	@Test
	public void testgetOverdueBooks() throws Exception {
	List<Book> bookslist=serviceobj.overduebooks();
	assertNotNull(bookslist);
	}
	
	@Test
	public void testgetBookCountByCategory() throws Exception {
		Map<String, Long> bookcategorymap=serviceobj.count_of_books_percategory();
		assertNotNull(bookcategorymap);
	}
	
	@Test
	public void testgetMembersWithActiveIssuedBooks() throws Exception {
		List<Member> ActiveMembers =serviceobj.members_with_statusissue();
		assertNotNull(ActiveMembers);
	}
	
}