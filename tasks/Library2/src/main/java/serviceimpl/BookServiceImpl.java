package serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Exception.InvalidInputException;
import daoimpl.BookDAOImpl;
import model.Book;
import model.BookCategoryCount;
import service.BookService;

public class BookServiceImpl implements BookService {

	private final BookDAOImpl bookDAO = new BookDAOImpl();

	public void addBook(Book book) throws Exception {
		if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
			throw new InvalidInputException("Title is required");
		}
		if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
			throw new InvalidInputException("Author is required");
		}
		if (book.getStatus() != 'A' && book.getStatus() != 'I') {
			throw new InvalidInputException("Status must be 'A' or 'I'");
		}
		if (book.getAvailability() != 'A' && book.getAvailability() != 'I') {
			throw new InvalidInputException("Availability must be 'A' or 'I'");
		}

		bookDAO.addBook(book);
	}

	public List<Book> getAllBooks() throws Exception {
		return bookDAO.getAllBooks();
	}

	public void updateBook(Book book) throws Exception {
		if (book.getBookId() < 0)
			throw new InvalidInputException("Invalid Book ID");
		if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
			throw new InvalidInputException("Title is required");
		}
		if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
			throw new InvalidInputException("Author is required");
		}
		if (book.getStatus() != 'A' && book.getStatus() != 'I') {
			throw new InvalidInputException("Status must be 'A' or 'I'");
		}
		if (book.getAvailability() != 'A' && book.getAvailability() != 'I') {
			throw new InvalidInputException("Availability must be 'A' or 'I'");
		}
		bookDAO.updateBook(book);
	}

	public void updateBookAvailability(Integer bookId, Character availability) throws Exception {
		if (bookId == null) {
			throw new InvalidInputException("Please enter bookId");
		}
		if (bookId < 0) {
			throw new InvalidInputException("Invalid Book ID");
		}
		if (availability != 'A' && availability != 'I') {
			throw new InvalidInputException("Availability must be 'A' or 'I'");
		}
		bookDAO.updateBookAvailability(bookId, availability);
	}
	
	public List<BookCategoryCount> getBookCountPerCategory() throws Exception {
		List<Book> books = bookDAO.getAllBooks();
		Map<String, Integer> categoryCount = books.stream()
				.collect(Collectors.groupingBy(Book::getCategory, Collectors.summingInt(e -> 1)));
		return categoryCount.entrySet().stream().map(e -> {
			return new BookCategoryCount(e.getKey(), e.getValue());
		}).collect(Collectors.toList());
	}
}
