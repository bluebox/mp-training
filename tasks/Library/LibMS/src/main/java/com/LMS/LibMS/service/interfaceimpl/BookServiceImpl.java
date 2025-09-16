package com.LMS.LibMS.service.interfaceimpl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LMS.LibMS.model.Book;
import com.LMS.LibMS.model.IssueRecord;
import com.LMS.LibMS.repository.interfaces.BookRepository;
import com.LMS.LibMS.repository.interfaces.IssueRecordRepository;
import com.LMS.LibMS.service.interfaces.BookService;

import jakarta.validation.Valid;

@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	
	private final IssueRecordRepository issueRecordRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository,IssueRecordRepository issueRecordRepository) {
		this.bookRepository = bookRepository;
		this.issueRecordRepository = issueRecordRepository;
	}
	
	@Override
	public List<Book> getAllBooks(){
		return bookRepository.getAllBooks();
	}

	@Override
	public void addBook(@Valid Book book) throws Exception {
		book.setCreatedAt(LocalDateTime.now());
		book.setCreatedBy("SYSTEM");
		try {
			bookRepository.addBook(book);			
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				String[] messageString = e.getMessage().split(";");
				String[] causeString = messageString[messageString.length-1].split("books.");
				if(causeString[causeString.length-1].equals("UQ_Title_Author_Category'"))
					throw new Exception("Book with same Title, Author and Category already exists.");
				else if(causeString[causeString.length-1].equals("UQ_Title_Author'")){
					throw new Exception("Book with same Title, Author already exists.");
				}
				throw new Exception("Failed to add book");
			}
		}
	}

	@Override
	public Book findBookById(Integer bookId) {
		List<Book> books = bookRepository.findBookById(List.of(bookId));
        if (books == null) {
        	return null;
        }
        
        return books.get(0);
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBooksById(List<Integer> bookIds) throws Exception {
    	List<Book> booksToDelete = bookRepository.findBookById(bookIds);
    	
        boolean loged= bookRepository.logBook(booksToDelete);
        
        if(!loged) {
        	throw new Exception("Book Logging Failed");
        }

        try {
            int rows = bookRepository.deleteBooksById(bookIds);
            if (rows == 0) {
            	throw new Exception("No books deleted");            	
            }
        } catch (Exception e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new Exception("Book is issued, cannot be deleted");
            }
        	throw new Exception("Failed to delete");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAvailabilitiesById(List<Integer> bookIds) throws Exception {
    	 List<Book> books = bookRepository.findBookById(bookIds);
    	 List<IssueRecord> issueRecords = issueRecordRepository.getIssuedRecordsWithBookIds(bookIds);
    	 
    	 if(books.size()>1) {
    		 List<Book> AvailFilerBooks = books.stream().filter(b -> b.getAvailability().getCode()=="A").collect(Collectors.toList());
    		 List<Book> issueFileterBooks = books.stream().filter(b -> b.getAvailability().getCode()=="I").collect(Collectors.toList());    		 
    		 if(AvailFilerBooks.size()!=0 || issueFileterBooks.size()!=0) {
    			 throw new Exception("Please Select Books with same Availability");
    		 }
    	 }
//    	 System.out.println(issueRecords);
//    	 if(issueRecords.size()!=0) {
//    		 List<IssueRecord> filteredIssueRecords = issueRecords.stream().filter(b -> b.getStatus().getCode()=="I").collect(Collectors.toList());
//    		 
//    		 if(filteredIssueRecords.size()>0) {
//    			 throw new Exception("The Book is Issued so the Availability cannot be changed");
//    		 }    		 
//    	 }
    	 

    	 
        boolean loged = bookRepository.logBook(books);
        
        if(!loged) {
        	throw new Exception("Book Logging Failed");
        }

         int rows = bookRepository.updateAvailabilitiesById(bookIds, "ADMIN");
         if (rows == 0) {
        	 throw new Exception("No availabilities updated");        	 
         }
         return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBook(@Valid Book book) throws Exception {
        Book existing = bookRepository.findBookById(List.of(book.getBookId())).get(0);
        if (existing == null) {
            throw new Exception("Book not found");
        }
        
        if (existing.equals(book)) {
            throw new Exception("No changes found");
        }
        
        
	      boolean loged =  bookRepository.logBook(List.of(existing));
	      
	      if(!loged) {
	      	throw new Exception("Book Logging Failed");
	      }
        
//        if (book.getBookId() == 1) {
//        	throw new Exception("TEST_ROLLBACK_ERROR");
//        }

        book.setUpdatedAt(LocalDateTime.now());
        book.setUpdatedBy("ADMIN");

        int rows = bookRepository.updateBook(book);
        if (rows == 0) {
        	throw new Exception("Update failed");        	
        }

        return true;
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	public boolean makeBookInactiveById(Integer bookId) throws Exception {
				
		Book book = bookRepository.findBookById(List.of(bookId)).get(0);
 
        boolean loged = bookRepository.logBook(List.of(book));
        
        if(!loged) {
        	throw new Exception("Book Logging Failed");
        }
        
        int rows = bookRepository.makeBookInactiveById(bookId);
        if (rows == 0) 
        	throw new Exception("Book is issued, cannot be inactivated");

        return true;
	}
    
}