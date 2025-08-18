package com.lms.LMS_Springboot.Service;



import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lms.LMS_Springboot.DAO.BookDAO;
import com.lms.LMS_Springboot.Model.Book;

@Service
@Component
public class BookService  {

	@Autowired
	private BookDAO bookdao;
  public int addbook(Book book) {
	  
	  return bookdao.addBook(book);
	  
  }
  public List<Book> viewbooks() {
	  return bookdao.viewallBooks();
	  
  }
  public int updatebook(Book book) {
	  return bookdao.updateBook(book);
  }
  public Book bookwithid(int id) {
	  return bookdao.getBookwithId(id);
  }
  public List<Book> viewjoinbooks(){
	  return bookdao.viewjoinBooks();
  }
 
}
