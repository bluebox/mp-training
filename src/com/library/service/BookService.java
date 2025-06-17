package com.library.service;

import com.library.dao.BookDao;
import com.library.controller.Book;

import java.util.List;

public class BookService {
    private final BookDao bookDAO = new BookDao();

    public void addBook(Book book) throws Exception {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty() ){
            throw new Exception("Book Title cannot be empty");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new Exception("Book Author cannot be empty");
        }
        if (book.getCategory() == null || book.getCategory().trim().isEmpty()||!(book.getCategory() instanceof String)) {
            throw new Exception("Invalid Book Category");
        }
        
        bookDAO.addBook(book);
    }
    
    
    //data to BooksLog   
//    public void addToBooksLog(Book book) throws Exception {
//    	
//    	bookDAO.addToBooksLog(book);
//    }
    

    public void updateBook(Book book) throws Exception {
        if (book.getBookId() <= 0) {
            throw new Exception("Invalid book ID");
        }
        bookDAO.updateBook(book);
    }

    public void updateAvailability(int bookId, char availability) throws Exception {
        if (availability != 'A' && availability != 'I') {
            throw new Exception("Invalid availability");
        }
        bookDAO.updateAvailability(bookId, availability);
    }

    public List<Book> getAllBooks() throws Exception {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(int bookId) throws Exception {
        return bookDAO.getBookById(bookId);
    }
}