package dev.kaushik.library.validator;

import java.util.regex.Pattern;

import dev.kaushik.library.exception.LibraryException;
import dev.kaushik.library.model.Book;

public class BookValidator {
	public static final Pattern nameRegEx = Pattern.compile("^[A-Za-z .]+$");

	public static void validate(Book book) {
		if (book == null) {
			throw new LibraryException("book cant be null");
		}
		if (book.getTitle() == null) {
			throw new LibraryException("book title cant be null");
		}
		int titleLength = book.getTitle().trim().length();
		if (titleLength < 5 || titleLength > 100) {
			throw new LibraryException("title must be between 5 to 100 characters");
		}
		if (!nameRegEx.matcher(book.getTitle()).matches()) {
			throw new LibraryException("Title can only contain alphabets, spaces and dots");
		}
		if (book.getAuthor() == null) {
			throw new LibraryException("book author cant be null");
		}
		int authorLength = book.getAuthor().trim().length();
		if (authorLength < 5 || authorLength > 100) {
			throw new LibraryException("author name must be between 5 to 100 characters");
		}
		if (!nameRegEx.matcher(book.getAuthor()).matches()) {
			throw new LibraryException("Author can only contain alphabets, spaces and dots");
		}
		if (book.getCategory() == null) {
			throw new LibraryException("Book category cant be null");
		}

		if (book.getStatus() == null) {
			throw new LibraryException("book status cant be null");
		}

		if (book.getAvailability() == null) {
			throw new LibraryException("book availability cant be null");
		}
	}
}
