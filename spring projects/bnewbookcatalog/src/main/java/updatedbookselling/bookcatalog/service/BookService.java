package updatedbookselling.bookcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import updatedbookselling.bookcatalog.daoimpl.BookRepository;
import updatedbookselling.bookcatalog.domain.Book;



@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public Book getBookById(int id) {
        return bookRepository.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public List<Book> searchBooksByName(String name) {
        return bookRepository.searchBooksByName(name);
    }
    
    public List<Book> getBooksByPage(int pageNumber) {
        return bookRepository.getBooksByPage(pageNumber, 5); 
    }
    
    public List<Book> getBooks(int page, String search, Boolean available, Boolean unavailable) {
        int pageSize = 5;
        int offset = page * pageSize;

        if (search != null && !search.isEmpty()) {
            return bookRepository.findByTitleContaining(search, offset, pageSize);
        }

        if (Boolean.TRUE.equals(available) ){
            return bookRepository.findAvailableBooks(offset, pageSize);
        }

        if (Boolean.TRUE.equals(unavailable)) {
            return bookRepository.findUnavailableBooks(offset, pageSize);
        }

        return bookRepository.findAllPaged(offset, pageSize);
    }
}

