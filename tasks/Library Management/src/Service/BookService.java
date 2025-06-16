package Service;

import casestudy.Book; 
import casestudy.LibraryException;

import java.util.List; import java.util.Map; 
import java.util.stream.Collectors;

import DAO.BookDAO;

public class BookService { 
	private final BookDAO bookDAO = new BookDAO();

	public void addBook(Book book) throws LibraryException {
		if (book.getTitle() == null || book.getTitle().isEmpty()) {
			throw new LibraryException("Book title cannot be empty");
		}
		if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
			throw new LibraryException("Book author cannot be empty");
		}
		bookDAO.addBook(book);
	}

	public void updateBook(Book book) throws LibraryException {
		if (book.getBookId() <= 0) {
			throw new LibraryException("Invalid book ID");
		}
		bookDAO.updateBook(book);
	}

	public void updateBookStatus(int bookId, char status) throws LibraryException {
		if (bookId <= 0) {
			throw new LibraryException("Invalid book ID");
		}
		if (status != 'A' && status != 'I') {
			throw new LibraryException("Invalid status. Must be 'A' (Active) or 'I' (Inactive)");
		}
		bookDAO.updateBookStatus(bookId, status);
	}

	public boolean isBookActive(int bookId) throws LibraryException {
		Book book = bookDAO.getBookById(bookId);
		if (book == null) {
			throw new LibraryException("Book not found with ID: " + bookId);
		}
		return book.getStatus() == 'A';
	}

	public List<Book> getAllBooks() throws LibraryException {
		return bookDAO.getAllBooks();
	}

	public Map<String, Long> getBooksCountByCategory() throws LibraryException {
		return getAllBooks().stream()
				.collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
	}
}