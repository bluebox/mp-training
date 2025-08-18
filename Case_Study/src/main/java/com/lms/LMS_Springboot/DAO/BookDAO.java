package com.lms.LMS_Springboot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Status;
import com.lms.LMS_Springboot.Service.BookService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDAO {
	@Autowired 
	private BookRowMapper bookrowmapper;
	@Autowired 
	private JdbcTemplate jdbctemplate;
	
	
	public int addBook(Book book) {
		String query="insert into books(title,author,category,status,availability) values (?,?,?,?,?)";
		int res=0;
		if(book.getStatus()==Status.INACTIVE) {
			 res=jdbctemplate.update(query, book.getTitle(), book.getAuthor(),book.getCategory(),book.getStatus().getType(),null);

		}
		else {
		
			 res=jdbctemplate.update(query, book.getTitle(), book.getAuthor(),book.getCategory(),book.getStatus().getType(),book.getAvailability().getType());
		}
		return res;
	}
	
	public int updateBook(Book book) {
		System.out.println(book);
		String querylog="insert into library_management_system.books_log(bookid,title,author,category,status,availability) values (?,?,?,?,?,?)";
		String query="update books set category=?,status=? where bookid=?";
//		int bookid=getBookidwithbook(book.getTitle());
		Book oldbook=getBookwithId(book.getBookid());
		int b=jdbctemplate.update(querylog,oldbook.getBookid(), oldbook.getTitle(), oldbook.getAuthor(),oldbook.getCategory(),oldbook.getStatus().getType(),oldbook.getAvailability().getType());
		
		int res=jdbctemplate.update(query,book.getCategory(),book.getStatus().getType(),book.getBookid());
	return res;
	
	}
	public List<Book> viewallBooks() {
		String sql="select bookid,title,author,category,status,availability from books";
		
	    List<Book> books = jdbctemplate.query(sql,bookrowmapper);
	    return books;
	}
	public Book getBookwithId(int bookid) {
		String query="select bookid,title,author,category,status,availability from books where bookid=?";
		Book book=jdbctemplate.queryForObject(query,bookrowmapper ,bookid);
		return book;
		
	}
public List<Book> viewjoinBooks(){
	 String query=" SELECT books.bookid,books.title,books.author,books.category,books.status,books.availability   FROM issue_records INNER JOIN books ON books.bookid = issue_records.bookid where ( DATEDIFF(ReturnDate,IssueDate)>10) or (ReturnDate Is Null and DATEDIFF(CURRENT_DATE(),IssueDate)>10)";
	    List<Book> books = jdbctemplate.query(query,bookrowmapper);
	    return books;
    }


	

}


@Component
@Scope("prototype")
class BookRowMapper implements RowMapper<Book> {
	

	@Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Book book=new Book();
        book.setBookid(rs.getInt("bookid"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setCategory(rs.getString("category"));
        book.setStatus(Status.getstatus(rs.getString("status")));
        book.setAvailability(Availability.getstatus(rs.getString("availability")));
        return book;
    	
}
}