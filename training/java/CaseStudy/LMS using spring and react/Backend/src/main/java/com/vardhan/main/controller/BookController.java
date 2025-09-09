package com.vardhan.main.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vardhan.main.model.Book;
import com.vardhan.main.model.BookCategory;
import com.vardhan.main.service.BookService;
import com.vardhan.main.util.ValidationUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final BookService bookService;
    private final ValidationUtil validationUtil;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        log.info("Fetching all books");
        
        List<Book> books = bookService.getAllBooks();
        log.info("Fetched {} books", books.size());
        
        return ResponseEntity.ok(ApiResponse.success(books, "Books retrieved successfully"));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Book>> getBookByBookId(@PathVariable String bookId) {
        log.info("Fetching book with ID: {}", bookId);
        
        validationUtil.validateBookId(bookId);
        
        return bookService.findBookByBookId(bookId)
                .map(book -> ResponseEntity.ok(ApiResponse.success(book, "Book found")))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> addBook(@Valid @RequestBody Book book) {
        log.info("Adding new book: {}", book.getTitle());
        
        validationUtil.assertUniqueBookTitle(book.getTitle());
        validationUtil.validateBookStatus(book.getStatus());
        validationUtil.validateBookAvailability(book.getAvailability());
        
        Book savedBook = bookService.saveBook(book);
        log.info("Successfully added book with ID: {}", savedBook.getBookId());
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(savedBook, "Book added successfully"));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Book>> updateBook(
            @PathVariable String bookId, 
            @Valid @RequestBody Book book) {
        log.info("Updating book with ID: {}", bookId);
        
        validationUtil.validateBookId(bookId);
        book.setBookId(bookId);
        
        validationUtil.assertUniqueBookTitleForUpdate(book.getTitle(), bookId);
        validationUtil.validateBookStatus(book.getStatus());
        validationUtil.validateBookAvailability(book.getAvailability());
        
        Book updatedBook = bookService.updateBook(book);
        log.info("Successfully updated book with ID: {}", updatedBook.getBookId());
        
        return ResponseEntity.ok(ApiResponse.success(updatedBook, "Book updated successfully"));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable String bookId) {
        log.info("Deleting book with ID: {}", bookId);
        
        validationUtil.validateBookId(bookId);
        
        boolean deleted = bookService.deleteBook(bookId);
        if (deleted) {
            log.info("Successfully deleted book with ID: {}", bookId);
            return ResponseEntity.ok(ApiResponse.success(null, "Book deleted successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Book>>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category) {
        log.info("Searching books with title: {}, author: {}, category: {}", title, author, category);
        
        List<Book> books;
        
        if (title != null && !title.trim().isEmpty()) {
            books = bookService.searchBooksByTitle(title);
        } else if (author != null && !author.trim().isEmpty()) {
            books = bookService.findBooksByAuthor(author);
        } else if (category != null && !category.trim().isEmpty()) {
            books = bookService.findBooksByCategory(category);
        } else {
            books = bookService.getAllBooks();
        }
        
        return ResponseEntity.ok(ApiResponse.success(books, "Books retrieved successfully"));
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse<List<Book>>> getAvailableBooks(
            @RequestParam(required = false) String category) {
        log.info("Fetching available books for category: {}", category);
        
        List<Book> books;
        if (category != null && !category.trim().isEmpty()) {
            books = bookService.getAvailableBooksByCategory(category);
        } else {
            books = bookService.getAvailableBooks();
        }
        
        return ResponseEntity.ok(ApiResponse.success(books, "Available books retrieved"));
    }
    
    @GetMapping("/categories/all")
    public ResponseEntity<ApiResponse<List<String>>> getAllBookCategoryEnums() {
        log.info("Fetching all book category enums");
        
        List<String> categories = Arrays.stream(BookCategory.values())
                .map(Enum::name)
                .sorted()
                .toList();
        
        return ResponseEntity.ok(ApiResponse.success(categories, "All book categories retrieved"));
    }


    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<String>>> getBookCategories() {
        log.info("Fetching book categories");
        
        List<Book> allBooks = bookService.getAllBooks();
        List<String> categories = allBooks.stream()
                .map(Book::getCategory)
                .distinct()
                .sorted()
                .toList();
        
        return ResponseEntity.ok(ApiResponse.success(categories, "Book categories retrieved"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Map<String, Long>>> getBooksCount() {
        log.info("Fetching books count");
        
        long totalBooks = bookService.getTotalBooksCount();
        long availableBooks = bookService.getAvailableBooks().size();
        
        Map<String, Long> response = Map.of(
                "totalBooks", totalBooks,
                "availableBooks", availableBooks,
                "issuedBooks", totalBooks - availableBooks
        );
        
        return ResponseEntity.ok(ApiResponse.success(response, "Books count retrieved"));
    }
}
