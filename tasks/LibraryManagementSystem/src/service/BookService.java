package service;

import dao.BookDAO;
import model.Book;

public class BookService {

    private final BookDAO bookDAO = new BookDAO();

    public void addBook(Book book) throws Exception {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Author is required");
        }
        if (book.getStatus() != 'A' && book.getStatus() != 'I') {
            throw new IllegalArgumentException("Status must be 'A' or 'I'");
        }
        if (book.getAvailability() != 'A' && book.getAvailability() != 'I') {
            throw new IllegalArgumentException("Availability must be 'A' or 'I'");
        }

        bookDAO.addBook(book);
    }
}
