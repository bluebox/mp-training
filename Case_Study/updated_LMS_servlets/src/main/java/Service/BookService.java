package Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import DAO.Databasemanager;
import domain.Book;
import domain.checking_enum.Availability;

public class BookService implements BookServiceInterface {

    Databasemanager db = new Databasemanager();

    @Override
    public boolean addbooks(Book book) throws NumberFormatException,SQLIntegrityConstraintViolationException {
        try {
    	return db.addBooks(book);
        }
        catch(NumberFormatException e3) {
        	throw new NumberFormatException();
        	
        }
        
        catch(SQLIntegrityConstraintViolationException e) {
        	throw new SQLIntegrityConstraintViolationException();
        }
        
    }

    @Override
    public void updateavailability(Book book,Availability available) {
        db.Updatebookavailability(book, available);
    }

    @Override
    public boolean updatebookdetails(int id, Book book) {
       return db.updateBookDetails(id, book);
    }

    @Override
    public List<Book> viewallbooks() {
        return db.viewallbooks();
    }

    @Override
    public boolean getBookbyId(int id) {
        return db.getBookbyId(id);
    }

    @Override
    public Book getbookwithid(int id) {
        return db.getBookwithId(id);
    }
}
