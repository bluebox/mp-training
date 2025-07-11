package updatedbookselling.bookcatalog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import updatedbookselling.bookcatalog.domain.Book;
import updatedbookselling.bookcatalog.service.BookService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id) {
    	Map<String , String> response = new HashMap<>();
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
        	e.printStackTrace();
        	response.put("status", "404");
	        response.put("message", "Book not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByName(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchBooksByName(title));
    }
    
    @GetMapping
    public ResponseEntity<List<Book>> getBooks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(required = false) String search,
        @RequestParam(required = false) Boolean available,
        @RequestParam(required = false) Boolean unavailable
    ) {
        List<Book> books = bookService.getBooks(page, search, available, unavailable);
        return ResponseEntity.ok(books);
    }
    
    //pagination controller
    
    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<List<Book>> getBooksByPage(@PathVariable int pageNumber) {
        List<Book> books = bookService.getBooksByPage(pageNumber);
        return ResponseEntity.ok(books);
    }
}

