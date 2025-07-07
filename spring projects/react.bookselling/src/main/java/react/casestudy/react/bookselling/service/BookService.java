package react.casestudy.react.bookselling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import react.casestudy.react.bookselling.dao.BookRepository;
import react.casestudy.react.bookselling.domain.Book;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public int addBook(Book book) {
        return bookRepository.addBook(book);
    }

    public boolean updateBook(int id, Book book) {
        if (bookRepository.existsById(id)) {
            bookRepository.updateBook(id, book);
            return true;
        }
        return false;
    }

    public Book getBookById(int id) {
        return bookRepository.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public List<Book> searchBooksByName(String name) {
        return bookRepository.searchBooksByName(name);
    }
}

