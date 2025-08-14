package library.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.dao.interfaceimpl.BookDAOImpl;
import library.dao.interfaces.BookDAO;
import library.exception.LibraryException;
import library.model.Book;
import library.model.enums.BookAvailability;
import library.service.interfaces.BookService;
import library.validation.BookValidator;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    public BookServiceImpl() {
        this.bookDAO = new BookDAOImpl();
    }


    @Override
    public void addBook(Book book, String createdBy){
        BookValidator.validateBookTitle(book.getTitle());
        BookValidator.validateBookAuthor(book.getAuthor());
        BookValidator.validateBookCategory(book.getCategory().getDisplayName());
        BookValidator.validateBookStatus(book.getStatus());
        BookValidator.validateBookAvailability(book.getAvailability());
        BookValidator.validateUser(createdBy, "Created By User");

        try {
            bookDAO.addBook(book, createdBy);
        } catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new LibraryException("A book with the same title and category already exists.", e.getCause());
            }
            throw new LibraryException("An error occurred while adding a book: " + e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LibraryException("An  error occurred while adding a book.", e);
        }
    }

    @Override
    public List<Book> findBooks(Map<String, Object> criteria) {
        try {
            List<Book> books = bookDAO.findBooks(criteria);
            return books;
        } catch (LibraryException e) {
            throw new LibraryException("An error occurred while finding book: " + e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LibraryException("An  error occurred while finding book.", e);
        }
    }


    @Override
    public boolean updateBook(Book book, String updatedBy) {
        if (book == null) { 
        	throw new LibraryException("Book object for update cannot be null."); 
        }
        BookValidator.validateNumericId(book.getBookId(), "Book ID");
        BookValidator.validateBookTitle(book.getTitle());
        BookValidator.validateBookAuthor(book.getAuthor());
        BookValidator.validateBookCategory(book.getCategory().getDisplayName());
        BookValidator.validateBookStatus(book.getStatus());
        BookValidator.validateUser(updatedBy, "Updated By User");

        try {
            boolean success = bookDAO.updateBook(book, updatedBy);
            return success;
        } catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new LibraryException("A book with the same title and category already exists.", e.getCause());
            }
            throw new LibraryException("An error occurred while updating book: " + e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LibraryException("An  error occurred while updating book.", e);
        }
    }

    
    @Override
    public boolean deleteBooks(List<Integer> bookIds) {
        if (bookIds == null || bookIds.isEmpty()) {
            throw new LibraryException("Please select some books to delete.");
        }
        for (Integer bookId : bookIds) {
            BookValidator.validateNumericId(bookId, "Book ID");
        }
        try {
            boolean results = bookDAO.deleteBooks(bookIds);
            return results;
        } catch (LibraryException e) {
            if(e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new LibraryException("The Issued Books cannot be deleted. ", e);
            }else {
            	throw new LibraryException("An error occurred while deleting book: " + e.getMessage(), e);            	            	
            }
        } catch (Exception e) {
            throw new LibraryException("An  error occurred while deleting book.", e);
        }
    }


	@Override
	public boolean updateBookAvailability(List<Integer> bookIds,String updatedBy) {
	      if (bookIds == null || bookIds.isEmpty()) {
	            throw new LibraryException("Please Select books to update availability.");
	        }
	        for (Integer bookId : bookIds) {
	            BookValidator.validateNumericId(bookId, "Book ID in batch");
	        }
	        
	    	int Avail = 0;
			int Issue = 0;
			for(int Bid : bookIds) {
				Map<String, Object> criteria = new HashMap<>();
		        criteria.put("bookId", Bid);
		        List<Book> books = bookDAO.findBooks(criteria);
		        Book existingBook = books.isEmpty() ? null : books.get(0);

		        if(BookAvailability.fromCode(existingBook.getAvailability().getCode()) == BookAvailability.AVAILABLE) {
					Avail++;
				}
				else {
					Issue++;
				}
			}
			
			if(Avail!=bookIds.size() && Issue!=bookIds.size()) {
	            throw new LibraryException("Please Select the books with same availability status.");
			}
			
	        try {
	            boolean results = bookDAO.updateBookAvailability(bookIds,updatedBy);
	            return results;
	        }  catch (Exception e) {
	            e.printStackTrace();
	            throw new LibraryException("An  error occurred while updating book availability .", e);
	        }
		
	}

    }