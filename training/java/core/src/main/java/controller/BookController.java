package controller;

import jakarta.validation.Valid;
import model.Book;
import model.BookAvailability;
import model.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.BookRepo;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepo.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id) {
        Book book = bookRepo.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with ID " + id + " not found");
        }
    }

    // Add a new book
    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book) {
        int rows = bookRepo.addBook(book);
        if (rows > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to add book");
        }
    }

    // Update book
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @Valid @RequestBody Book book) {
        book.setBookId(id);
        int rows = bookRepo.updateBook(book);
        if (rows > 0) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with ID " + id + " not found or update failed");
        }
    }

    // Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        int rows = bookRepo.deleteBook(id);
        if (rows > 0) {
            return ResponseEntity.ok("Book deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with ID " + id + " not found");
        }
    }

    //  Update availability
    @PatchMapping("/{id}/availablity") 
    public ResponseEntity<?> updateAvailability(@PathVariable int id,
                                                @RequestParam String availablity) {
        try {
            BookAvailability bookAvailability = BookAvailability.getAvailability(availablity.toUpperCase().trim());
            if (bookAvailability == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid availability value. Use A or I");
            }
            int rows = bookRepo.updateAvailability(id, bookAvailability);
            if (rows > 0) {
                return ResponseEntity.ok("Availability updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Book with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error parsing availability");
        }
    }

    // Update status
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable int id,
                                          @RequestParam String status) {
        try {
            BookStatus bookStatus = BookStatus.getStatus(status.toUpperCase().trim());
            if (bookStatus == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Invalid status value. Use A or I");
            }
            int rows = bookRepo.updateStatus(id, bookStatus);
            if (rows > 0) {
                return ResponseEntity.ok("Status updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Book with ID " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error parsing status");
        }
    }
}
